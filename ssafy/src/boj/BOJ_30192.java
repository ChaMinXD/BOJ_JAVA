package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_30192 {
	static int N,K;
	static char[] S;
	static class Range{
		int s;
		int e;
		Range(int s,int e){
			this.s=s;
			this.e=e;
		}
	}
	static ArrayList<Range> r=new ArrayList();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		S=new char[N];
		S=br.readLine().toCharArray();
		char last=S[0];
		int cnt=1;
		int ans=N;
		for(int i=1;i<N;i++) {
			if(S[i]==last)
				cnt++;
			else {
				if(cnt>K-1) {
					r.add(new Range(i-cnt,i-1));
				}
				last=S[i];
				cnt=1;
			}
		}
		if(cnt>K-1) {
			r.add(new Range(N-cnt,N-1));
		}
		// 범위 저장 완료
		
		if(r.size()==0) {
			System.out.println(ans);
		}
		else {
			ans=K-1;
			for(int i=r.get(0).s+K;i>K-1;i--) {
				boolean flag=true;
				for(int j=0;j<r.size();j++) {
					int mul=(r.get(j).s+1)/i;
					if(mul*i<r.get(j).s+1) mul++;
					int left=(mul*i-(r.get(j).s+1))+1;
					int right=r.get(j).e+1-mul*i;
					if(left>K-1||right>K-1) {
						flag=false;
						break;
					}
				}
				if(flag) {
					ans=i;
					break;
				}
			}
			System.out.println(ans);
		}
	}

}

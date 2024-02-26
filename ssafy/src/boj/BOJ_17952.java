package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17952{
	static int N;
	static class Work{
		int A;
		int T;
		Work(int A,int T){
			this.A=A;
			this.T=T;
		}
	}
	static Stack<Work> s=new Stack();
	static Work[] list;
	static int sum=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new Work[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int k=Integer.parseInt(st.nextToken());
			if(k==0)
				continue;
			else {
				int A=Integer.parseInt(st.nextToken());
				int T=Integer.parseInt(st.nextToken());
				list[i]=new Work(A,T);
			}
		}
		for(int i=0;i<N;i++) {
			if(list[i]==null) {
				if(!s.isEmpty()) {
					Work now=s.pop();
					int time=now.T-1;
					if(time==0)
						sum+=now.A;
					else
						s.add(new Work(now.A,time));
				}
			}
			else {
				int time=list[i].T-1;
				if(time==0)
					sum+=list[i].A;
				else {
					s.add(new Work(list[i].A,time));
				}
			
			}
		}
		System.out.println(sum);

	}

}

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6190 {
	static int T,N;
	static int[] list;
	static boolean[] visited;
	static int[] com= {-1,-1,-1};
	static int max=-1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
            max=-1;
			N=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer(br.readLine());
			list=new int[N];
			visited=new boolean[N];
			for(int i=0;i<N;i++) {
				list[i]=Integer.parseInt(st.nextToken());
			}
			combi(1);
			System.out.println("#"+(t+1)+" "+max);
		}
		
	}
	static void combi(int cnt) {
		if(cnt==3) {
			int a=list[com[1]]*list[com[2]];
			String s=Integer.toString(a);
			char[] c=s.toCharArray();
			int bef=0;
			for(int i=0;i<c.length;i++) {
				if(c[i]-'0'>=bef)
					bef=c[i]-'0';
				else
					return;
			}
			max=Math.max(max, a);
			return;
		}
		for(int i=0;i<N;i++) {
			if(com[cnt-1]>=i)
				continue;
			else{
				com[cnt]=i;
				combi(cnt+1);
			}
			
			
		}
	}

}
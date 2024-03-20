package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042_Seg {
	static int N,M,K;
	static long[] list;
	static long[] dp;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		list=new long[N+1];
		dp=new long[N+1];
		for(int i=1;i<N+1;i++) {
			list[i]=Long.parseLong(br.readLine());
			dp[i]=dp[i-1]+list[i];
			
		}
		for(int i=0;i<M+K;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());;
			long c=Long.parseLong(st.nextToken());;
			if(a==1) {
				list[b]=c;
				for(int j=b;j<N+1;j++) {
					dp[j]=dp[j-1]+list[j];
				}
			}
			else {
				System.out.println(dp[(int) c]-dp[b-1]);
			}
		}
	}
}


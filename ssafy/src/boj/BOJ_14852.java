package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14852 {
	static int N;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		dp=new int[N];
		dp[0]=2;
		if(N>1)
			dp[1]=7;
		if(N>2)
			dp[2]=22;
		for(int i=3;i<N;i++) {
			dp[i]=dp[i-2]*3+dp[i-1]*2+dp[i-3]*2;
		}
		System.out.println(dp[N-1]);
	}
}

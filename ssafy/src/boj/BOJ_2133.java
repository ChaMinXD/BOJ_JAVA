package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2133 {
	static int N;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		dp=new int[N+1];
		dp[0]=1;
		if(N>1)
			dp[2]=3;
		for(int i=3;i<N+1;i++) {
			if(i%2==1) continue;
			dp[i]=dp[i-2]*3;
			for(int j=4;j<=i;j+=2) {
				dp[i]+=dp[i-j]*2;
			}
		}
		System.out.println(dp[N]);
	}

}

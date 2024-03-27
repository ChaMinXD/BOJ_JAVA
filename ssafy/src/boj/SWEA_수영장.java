package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_수영장 {
	static int T;
	static int[] list;
	static int[] ticket;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int t=1;t<T+1;t++) {
			ticket=new int[4]; // 1일 1달 3달 1년
			list=new int[13];
			int[][] dp=new int[13][2];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {
				ticket[i]=Integer.parseInt(st.nextToken());
			}
			st=new StringTokenizer(br.readLine());
			for(int i=1;i<13;i++) {
				list[i]=Integer.parseInt(st.nextToken());
			}
			
			dp[1][0]=Math.min(list[1]*ticket[0],ticket[1]);
			dp[1][1]=ticket[2];
			dp[2][0]=dp[1][0]+Math.min(list[2]*ticket[0],ticket[1]);
			dp[2][1]=ticket[2];
			dp[3][0]=dp[2][0]+Math.min(list[3]*ticket[0],ticket[1]);
			dp[3][1]=ticket[2];
			
			
			for(int i=4;i<13;i++) {
				int now=Math.min(list[i]*ticket[0], ticket[1]);
				dp[i][0]=Math.min(dp[i-1][0]+now, dp[i-1][1]+now);
				dp[i][1]=Math.min(dp[i-3][0]+ticket[2],dp[i-3][1]+ticket[2]);
			}
			int ans=Math.min(dp[12][0],dp[12][1]);
			ans=Math.min(ans, ticket[3]);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

}

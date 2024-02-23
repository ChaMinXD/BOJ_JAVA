package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_9465 {
	static int T;
	static int N;
	static int[][] sticker;
	static int[][] DP;
	static int[] ans;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		ans=new int[T];
		for(int t=0;t<T;t++) {
			N=Integer.parseInt(br.readLine());
			sticker=new int[2][N];
			for(int i=0;i<2;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					sticker[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			DP=new int[2][N];
			DP[0][0]=sticker[0][0];
			DP[1][0]=sticker[1][0];
			if(N>1) {
			DP[0][1]=sticker[1][0]+sticker[0][1];
			DP[1][1]=sticker[0][0]+sticker[1][1];
			}
			for(int j=2; j<N; j++) {
				DP[0][j] = Math.max(DP[1][j-1], DP[1][j-2]) + sticker[0][j];
				DP[1][j] = Math.max(DP[0][j-1], DP[0][j-2]) + sticker[1][j];
			}
			ans[t]=Math.max(DP[0][N-1], DP[1][N-1]);
		
		}
		for(int i=0;i<T;i++) {
			System.out.println(ans[i]);
		}
		
	}

}

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11054 {
	static int N;
	static int[] list;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		list = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (list[j] < list[i]) {
					dp[i] = Integer.max(dp[i], dp[j] + 1);
				}
			}
			max = Integer.max(max, dp[i]);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (list[j] > list[i]) {
					dp[i] = Integer.max(dp[i], dp[j] + 1);
				}
			}
			max = Integer.max(max, dp[i]);
		}
		System.out.println(max + 1);
	}

}

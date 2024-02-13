package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12865 {
	static int N;
	static int K;
	static class bag implements Comparable<bag>{
		int W;
		int V;
		bag(int W,int V){
			this.W=W;
			this.V=V;
		}
		@Override
		public int compareTo(bag o) {
			return this.W-o.W;
		}
	}
	static int[][] DP;
	static bag[] list;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		list=new bag[N];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list[i]=new bag(a,b);
		}
		Arrays.sort(list);
		DP=new int[N+1][K+1];
		for(int i=0;i<N+1;i++) { // 물건
			for(int j=0;j<K+1;j++) { // 무게
				if(i==0||j==0) continue;
				if(list[i-1].W>j)
					DP[i][j]=DP[i-1][j];
				else {
					DP[i][j]=Integer.max(list[i-1].V+DP[i-1][j-list[i-1].W],DP[i-1][j]);
				}
			}
		}
		System.out.println(DP[N][K]);
	}

}

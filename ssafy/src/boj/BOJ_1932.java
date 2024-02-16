package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1932 {
	static int N;
	static int[][] tri;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		tri=new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<i+1;j++) {
				tri[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1;i<N;i++) {
			for(int j=0;j<i+1;j++) {
				if(j==0) {
					tri[i][j]=tri[i-1][j]+tri[i][j];
				}
				else if(j==i)
					tri[i][j]=tri[i-1][j-1]+tri[i][j];
				else {
					tri[i][j]=Math.max(tri[i-1][j-1]+tri[i][j],tri[i-1][j]+tri[i][j]);
				}
			}
		}
		
		int max=0;
		for(int i=0;i<N;i++) {
			max=Math.max(tri[N-1][i], max);
		}
		System.out.println(max);
	}

}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_11315 {
	static int T,N;
	static int[][] map;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
	static String ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		for(int t=1;t<T+1;t++) {
			ans="NO";
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] c=br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					if(c[j]=='o') {
						map[i][j]=1;
					}
				}
			}

			a:for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]==1) {
						for(int k=0;k<8;k++) {
							check(i,j,1,k);
						}
	
					}
				}
			}
			System.out.println(ans);
		}

	}
	static void check(int x,int y,int cnt,int dir) {
		if(cnt==5) {
			ans="YES";
			return ;
		}
		
			int nx=x+ways[dir][0];
			int ny=y+ways[dir][1];
			if(nx<0||nx>N-1||ny<0||ny>N-1) return;
			if(map[nx][ny]==1) {
				check(nx,ny,cnt+1,dir);
			}
		
	
		
	}

}

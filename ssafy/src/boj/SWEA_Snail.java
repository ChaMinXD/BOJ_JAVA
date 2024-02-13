package boj;

import java.util.Scanner;

public class SWEA_Snail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		for(int i=0;i<T;i++) {
			int N=sc.nextInt();
			int cnt=1;
			int x=0;
			int y=0;
			int[][] map=new int[N][N];
			int[][] dir= {{0,1},{1,0},{0,-1},{-1,0}};
			int now_dir=0;
			for(int j=1;j<=N*N;j++) {
				
	
				map[x][y]=j;
				
				int nx=x+dir[now_dir][0];
				int ny=y+dir[now_dir][1];
				
				if(nx<0||nx>(N-1)||ny<0||ny>(N-1)) {
					now_dir=(now_dir+1)%4;
					x=x+dir[now_dir][0];
					y=y+dir[now_dir][1];
					continue;
				}
				if(map[nx][ny]!=0) {
					now_dir=(now_dir+1)%4;
					x=x+dir[now_dir][0];
					y=y+dir[now_dir][1];
					continue;
				}
				
				x=nx;
				y=ny;
			}
			System.out.println("#"+(i+1));
			for(int a=0;a<N;a++) {
				for(int b=0;b<N;b++) {
					System.out.print(map[a][b]+" ");
				}
				System.out.println();
			}
		
			
		}
		
	}

}

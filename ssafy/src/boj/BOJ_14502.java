package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_14502 {
	static int N;
	static int M;
	static int[][] map;
	static int way=4;
	static int [][]ways= {{1,0},{-1,0},{0,1},{0,-1}};
	static int ans=-1;
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		N=scan.nextInt();
		M=scan.nextInt();
		map=new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j]=scan.nextInt();
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==2||map[i][j]==1) continue;
				DFS(map,i,j,0);
			}
		}
		System.out.println(ans);
		
	}
	
	static void DFS(int[][] mmap,int x,int y,int cnt) {
		int[][] c_map=new int[N][M]; // 깊은복사
		for(int i=0;i<mmap.length;i++) {
			c_map[i]=mmap[i].clone();
		}
		c_map[x][y]=1;
		if(cnt==2) {
			int num=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(c_map[i][j]==0||c_map[i][j]==1) continue;
					Queue<ArrayList<Integer>> q=new LinkedList<>();
					ArrayList<Integer> a=new ArrayList<>();
					a.add(i);
					a.add(j);
					q.add(a);
					while(!q.isEmpty()) {
						int xx=q.element().get(0);
						int yy=q.element().get(1);
						q.poll();
						for(int k=0;k<way;k++) {
							int nx=xx+ways[k][0];
							int ny=yy+ways[k][1];
							if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
							if(c_map[nx][ny]==2||c_map[nx][ny]==1) continue;
							ArrayList<Integer> b=new ArrayList<>();
							b.add(nx);
							b.add(ny);
							q.add(b);
							c_map[nx][ny]=2;
						}
					}		
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					
					if(c_map[i][j]==1||c_map[i][j]==2) continue;
					num++;
				}
			}
			if(num>ans) {
				ans=num;
				for(int i=0;i<N;i++) {
					for(int j=0;j<M;j++) {
						System.out.print(c_map[i][j]);
					}
					System.out.println();
					}
				System.out.println("-------------------");
			}
		}else {
		for(int i=x;i<N;i++) {
			for(int j=y;j<M;j++) {
				if(c_map[i][j]==2||c_map[i][j]==1) continue;
				DFS(c_map,i,j,cnt+1);
			}
		}	
	}
	}
}

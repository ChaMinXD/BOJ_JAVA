package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_10026 {
	static int N;
	static int[][] map;
	static int[][] map2;

	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static boolean[][] visited;

	static int ans=0;


	static int way=4;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		map2=new int[N][N];

		visited=new boolean[N][N];

		for(int i=0;i<N;i++) {
			char[] c=br.readLine().toCharArray();
			for(int j=0;j<N;j++) {
				if(c[j]=='R') {
					map[i][j]=1;
					map2[i][j]=1;

				}
				else if(c[j]=='G') {
					map[i][j]=2;
					map2[i][j]=1;

				}
				else {
					map[i][j]=3;
					map2[i][j]=2;

				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				BFS(map,i,j);
			}
		}
		System.out.print(ans+" ");
		ans=0;
		visited=new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				BFS(map2,i,j);
			}
		}
		System.out.println(ans);
	}
	static void BFS(int[][] map,int x,int y) {
		Queue<Pos> q=new ArrayDeque();
		int block=map[x][y];
		if(visited[x][y]) return;
		visited[x][y]=true;
		q.add(new Pos(x,y));
		while(!q.isEmpty()) {
			Pos n=q.poll();
			for(int i=0;i<way;i++) {
				int nx=n.x+ways[i][0];
				int ny=n.y+ways[i][1];
				if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==block) {
					visited[nx][ny]=true;
					q.add(new Pos(nx,ny));
				}
			}
		}
		ans++;
	}
	

}

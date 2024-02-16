package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600 {
	static int K;
	static int N;
	static int M;
	static int[][] map;
	static int[][][] visited;
	static int way=4;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}}; 
	static int monkey=8;
	static int[][] monkeys={{-2,-1},{-2,1},{-1,-2},{-1,2},{2,-1},{2,1},{1,-2},{1,2}};
	static Queue<Pos> q=new LinkedList();
	static class Pos{
		int x;
		int y;
		int k;
		Pos(int x,int y,int k){
			this.x=x;
			this.y=y;
			this.k=k;
		}
	}
 	public static void main(String[] args) throws NumberFormatException, IOException {
 		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
 		K=Integer.parseInt(br.readLine());
 		StringTokenizer st=new StringTokenizer(br.readLine());
 		M=Integer.parseInt(st.nextToken());
 		N=Integer.parseInt(st.nextToken());
 		map=new int[N][M];
 		for(int i=0;i<N;i++) {
 			st=new StringTokenizer(br.readLine());
 			for(int j=0;j<M;j++) {
 				map[i][j]=Integer.parseInt(st.nextToken());
 			}
 		}
 		BFS();
 		int min=Integer.MAX_VALUE/10;
 		for(int i=0;i<K+1;i++) {
 			if(visited[N-1][M-1][i]==0)
 				continue;
 			min=Math.min(min, visited[N-1][M-1][i]);
 		}
 		if(min==Integer.MAX_VALUE/10)
 			System.out.println("-1");
 		else
 			System.out.println(min-1);
 	}
 	static void BFS() {
 		visited=new int[N][M][K+1];
 		q.add(new Pos(0,0,K));
 		visited[0][0][K]=1;
 		while(!q.isEmpty()) {
 			Pos now=q.poll();
 			for(int i=0;i<way;i++) {
 				int nx=now.x+ways[i][0];
 				int ny=now.y+ways[i][1];
 				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
 				if(visited[nx][ny][now.k]!=0) continue;
 				if(map[nx][ny]==1) continue;
 				q.add(new Pos(nx,ny,now.k));
 				visited[nx][ny][now.k]=visited[now.x][now.y][now.k]+1;
 			}
 			if(now.k>0) {
 				for(int i=0;i<monkey;i++) {
	 				int nx=now.x+monkeys[i][0];
	 				int ny=now.y+monkeys[i][1];
	 				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
	 				if(visited[nx][ny][now.k-1]!=0) continue;
	 				if(map[nx][ny]==1) continue;
	 				q.add(new Pos(nx,ny,now.k-1));
	 				visited[nx][ny][now.k-1]=visited[now.x][now.y][now.k]+1;
 				}
 			}
 		}
 	}

}

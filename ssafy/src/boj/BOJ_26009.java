package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_26009 {
	static int N,M,K;
	static int[][] map;
	static boolean[][] visited;
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static int[][] plus= {{1,1},{1,-1},{-1,-1},{-1,1}};
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(br.readLine());
		map=new int[N][M];
		visited=new boolean[N][M];

		for(int i=0;i<K;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken());
			BFS(a,b,c);
		}
		Move();
		if(map[N-1][M-1]==0) {
			System.out.println("NO");
		}
		else {
			System.out.println("YES");
			System.out.println(map[N-1][M-1]);

		}
		
	}
	static void BFS(int x,int y,int cnt) {
		visited[x][y]=true;
		int val=cnt;
		while(val>=0) {
			int xp=val;
			int yp=cnt-val;
			for(int i=0;i<4;i++) {
				int nx=x+xp*plus[i][0];
				int ny=y+yp*plus[i][1];
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				visited[nx][ny]=true;
			}
			val--;

		}
	}
	static void Move() {
		Queue<Pos> q=new ArrayDeque();
		q.add(new Pos(0,0));
		while(!q.isEmpty()) {
			Pos now=q.poll();
			for(int i=0;i<4;i++) {
				int nx=now.x+ways[i][0];
				int ny=now.y+ways[i][1];
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny]=true;
				map[nx][ny]=map[now.x][now.y]+1;
				q.add(new Pos(nx,ny));
			}
		}
	}
}

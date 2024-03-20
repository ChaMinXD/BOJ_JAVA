package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236아기상어 {
	static int N;
	static int[][] map;
	static int[][] visited;
	static int way=4;
	static int[][] ways= {{-1,0},{0,-1},{0,1},{1,0}};
	static int size=2;
	static int cnt=0;
	static Pos shark;
	static int ans=0;
	static class Pos implements Comparable<Pos>{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
		@Override
		public int compareTo(Pos o) {
			if(this.x!=o.x) {
				return this.x-o.x;
			}
			else {
				return this.y-o.y;
			}
			
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9)
					shark=new Pos(i,j);
			}
		}


		while(check()!=0) {
				BFS();
				if(shark.x==-1)
					break;
		}
		System.out.println(ans);
	}
	
	static public void BFS() {
		visited=new int[N][N];
		Queue<Pos> q=new ArrayDeque();
		PriorityQueue<Pos> next=new PriorityQueue();
		q.add(shark);
		visited[shark.x][shark.y]=1;
		map[shark.x][shark.y]=0;
		int depth=-1;
		while(!q.isEmpty()) {
			Pos now=q.poll();
			if(visited[now.x][now.y]==depth)
				break;
			for(int i=0;i<way;i++) {
				int nx=now.x+ways[i][0];
				int ny=now.y+ways[i][1];
				if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
				if(visited[nx][ny]!=0) continue;
				if(map[nx][ny]>size) continue;
				else if(map[nx][ny]==size||map[nx][ny]==0) {
					visited[nx][ny]=visited[now.x][now.y]+1;
					q.add(new Pos(nx,ny));
				}
				else {
					next.add(new Pos(nx,ny));
					depth=visited[now.x][now.y]+1;

				}	
			}	
		}
		if(next.isEmpty()) {
			shark=new Pos(-1,-1);
		}
		else {
			shark=next.poll();
			map[shark.x][shark.y]=0;
			ans+=depth-1;
			cnt++;
		}
		
		if(size==cnt) {
			size++;
			cnt=0;
		}
	}
	static public int check() {
		int c=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==0||map[i][j]==9) continue;
				if(map[i][j]<size)
					c++;
			}
		}
		return c;
	}

}

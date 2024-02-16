package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
	static int N;
	static int M;
	static int[][] map;
	static int[][][] visited;
	static int way=4;
	static int [][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	static Queue<Pos> q=new LinkedList();
	static class Pos{
		int x;
		int y;
		int w;
		Pos(int x,int y,int w){
			this.x=x;
			this.y=y;
			this.w=w;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		visited=new int[N][M][2];
		for(int i=0;i<N;i++) {
			char[] a=br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				map[i][j]=a[j]-'0';
			}
		}
	
		BFS();
		int max=Integer.MAX_VALUE/10;
		for(int i=0;i<2;i++) {
			if(visited[N-1][M-1][i]==0) continue;
			max=Math.min(visited[N-1][M-1][i], max);
		}
		if(max==Integer.MAX_VALUE/10)
			System.out.println(-1);
		else
			System.out.println(max);
		
	}
	static void BFS() {
		q.add(new Pos(0,0,1));

		visited[0][0][1]=1;
		while(!q.isEmpty()) {
			Pos now=q.poll();
			for(int i=0;i<way;i++) {
				int nx=now.x+ways[i][0];
				int ny=now.y+ways[i][1];
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if(visited[nx][ny][now.w]!=0) continue;
				if(map[nx][ny]==1) continue;
				q.add(new Pos(nx,ny,now.w));
				visited[nx][ny][now.w]=visited[now.x][now.y][now.w]+1;
			}
			if(now.w>0) {
				
				for(int i=0;i<way;i++) {
					int nx=now.x+ways[i][0];
					int ny=now.y+ways[i][1];
					if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
					if(visited[nx][ny][now.w-1]!=0) continue;
					if(map[nx][ny]==1) {
						q.add(new Pos(nx,ny,0));
						visited[nx][ny][0]=visited[now.x][now.y][now.w]+1;
					}
					
				}
			}
			
		}
	}

}


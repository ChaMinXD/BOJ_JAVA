package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_수영대회 {
	static int N,T;
	static int[][] map;
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static Pos start;
	static Pos end;
	static Queue<Pos> q=new ArrayDeque();
	static int[][] visited;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();

		for(int t=1;t<T+1;t++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			StringTokenizer st;
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			st=new StringTokenizer(br.readLine());
			int ss=Integer.parseInt(st.nextToken());
			int se=Integer.parseInt(st.nextToken());
			start=new Pos(ss,se);
			st=new StringTokenizer(br.readLine());
			int es=Integer.parseInt(st.nextToken());
			int ee=Integer.parseInt(st.nextToken());
			end=new Pos(es,ee);
			q.add(start);
			visited=new int[N][N];
			visited[start.x][start.y]=1;
			BFS();
			sb.append("#").append(t).append(" ").append(visited[es][ee]-1).append("\n");
		}
		System.out.println(sb.toString());
	}

	
	static void BFS() {
		while(!q.isEmpty()) {
			Pos now=q.poll();
		
			for(int i=0;i<4;i++) {
				int nx=now.x+ways[i][0];
				int ny=now.y+ways[i][1];
				if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
				if(map[nx][ny]==1) continue;
				if(visited[nx][ny]!=0&&visited[nx][ny]<visited[now.x][now.y]+1) continue;
				if(map[nx][ny]==2) {
					visited[nx][ny]=visited[now.x][now.y]+1;
					while((visited[nx][ny]-1)%3!=0) {
						visited[nx][ny]++;
					}

				}else {
					visited[nx][ny]=visited[now.x][now.y]+1;
				}
				q.add(new Pos(nx,ny));
			}
		}
	}
	
}

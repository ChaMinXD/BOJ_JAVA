package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
	static int N,M;
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
	static Queue<Pos> ice=new ArrayDeque();
	static int way=4;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	static int ans=0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]!=0) {
					ice.add(new Pos(i,j));
				}
			}
		}
		while(!check()) {
			melt();
			ans++;
		}
		System.out.println(ans);
		
	}
	static void melt() {
		int cnt=ice.size();
		visited=new boolean[N][M];
		for(int i=0;i<cnt;i++) {
			Pos now=ice.poll();
			visited[now.x][now.y]=true;
			for(int j=0;j<way;j++) {
				int nx=now.x+ways[j][0];
				int ny=now.y+ways[j][1];
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if(map[now.x][now.y]==0) break;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==0)
					map[now.x][now.y]--;
			}
			if(map[now.x][now.y]>0)
				ice.add(new Pos(now.x,now.y));
		}
	}
	
	static boolean check() {
		int cnt=ice.size();
		Pos now=ice.peek();
		if(cnt==0) {
			ans=0;
			return true;
		}
		if(cnt==BFS(now))
			return false;

		return true;		
		
	}
	static int BFS(Pos p) {
		Queue<Pos> q=new ArrayDeque();
		visited=new boolean[N][M];
		q.add(p);
		int cnt=1;
		visited[p.x][p.y]=true;
		while(!q.isEmpty()) {
			Pos now=q.poll();
			for(int i=0;i<way;i++) {
				int nx=now.x+ways[i][0];
				int ny=now.y+ways[i][1];
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny]=true;
				if(map[nx][ny]!=0) {
					q.add(new Pos(nx,ny));
					cnt++;
				}
			}
		}
		return cnt;
		
		
	}

}

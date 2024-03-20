package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569토마토 {
	static int N,M,H;
	static int[][][] map;
	static boolean[][][] visited;
	static int way=6;
	static int[][] ways= {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
	static class Pos{
		int z;
		int x;
		int y;
		Pos(int z,int x,int y){
			this.z=z;
			this.x=x;
			this.y=y;
		}
	}
	static boolean flag=true;
	static Queue<Pos> q=new ArrayDeque();
	static int ans=0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		map=new int[H][N][M];
		visited=new boolean[H][N][M];
		for(int i=0;i<H;i++) {
			for(int j=0;j<N;j++) {
				st=new StringTokenizer(br.readLine());
				for(int k=0;k<M;k++) {
					map[i][j][k]=Integer.parseInt(st.nextToken());
					if(map[i][j][k]==1)
						q.add(new Pos(i,j,k));
					else if(map[i][j][k]==0)
						flag=false;
				}
			}
		}
		if(flag)
			System.out.println(ans);
		else {
			do {
				BFS();
				if(ans==-1)
					break;
			}while(!check());
			System.out.println(ans);
		}
	}
	static void BFS() {
		int cnt=q.size();
		if(q.isEmpty()) {
			ans=-1;
			return;
		}
		for(int i=0;i<cnt;i++) {
			Pos now=q.poll();
			if(visited[now.z][now.x][now.y]) continue;
			visited[now.z][now.x][now.y]=true;
			for(int j=0;j<way;j++) {
				int nz=now.z+ways[j][0];
				int nx=now.x+ways[j][1];
				int ny=now.y+ways[j][2];
				if(nz<0||nz>H-1||nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if(map[nz][nx][ny]==0) {
					map[nz][nx][ny]=1;
					q.add(new Pos(nz,nx,ny));
				}					
			}
		}
		ans++;		
	}
	static boolean check() {
		for(int i=0;i<H;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<M;k++) {
					if(map[i][j][k]==0)
						return false;
				}
			}
		}
		return true;
	}
}

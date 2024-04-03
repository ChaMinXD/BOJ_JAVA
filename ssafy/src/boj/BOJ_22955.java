package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_22955 {
	static int N,M;
	static char[][] map;
	static int[][] visited;
	static Queue<Pos> q=new ArrayDeque();
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static int[][] lr= {{0,-1},{0,1}}; //0 왼쪽 1 오른쪽
	static int[][] ud= {{-1,0},{1,0}}; //0 위 1 아래
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new char[N][M];
		visited=new int[N][M];
		for(int i=0;i<N;i++) {
			map[i]=br.readLine().toCharArray();
		}
		int sx=-1;
		int sy=-1;
		int ex=-1;
		int ey=-1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]=='C') {
					sx=i;
					sy=j;
					visited[i][j]=1;
					q.add(new Pos(i,j));
				}
				else if(map[i][j]=='E') {
					ex=i;
					ey=j;
				}
			}
		}
		BFS();

		if(visited[ex][ey]==0) {
			System.out.println("dodo sad");
		}else {
			System.out.println(visited[ex][ey]-1);
		}
		
	}
	static void BFS(){
		while(!q.isEmpty()) {
			Pos now=q.poll();
			boolean flag=true;
			if(map[now.x][now.y]=='E'||map[now.x][now.y]=='D') continue;
			if(map[now.x][now.y]=='L') {
				int up=now.x+ud[0][0];
				if(up>=0) {
					if(visited[up][now.y]==0||visited[up][now.y]>visited[now.x][now.y]+5) {
						q.add(new Pos(up,now.y));
						visited[up][now.y]=visited[now.x][now.y]+5;
					}
				}
			}
			int down=now.x+ud[1][0];
			if(down<N) {
				if(map[now.x][now.y]=='X') {
					while(map[down][now.y]=='X') {
						down=down+ud[1][0];
					}
					if(visited[down][now.y]==0||visited[down][now.y]>visited[now.x][now.y]+10) {
						q.add(new Pos(down,now.y));
						visited[down][now.y]=visited[now.x][now.y]+10;
					}
					flag=false;
				}
				else if(map[down][now.y]=='L') {
						if(visited[down][now.y]==0||visited[down][now.y]>visited[now.x][now.y]+5) {
							q.add(new Pos(down,now.y));
							visited[down][now.y]=visited[now.x][now.y]+5;
						}		
			}
		}
			// 위아래 검증 끝 이제 좌우 움직임 
			if(flag) {
				for(int i=0;i<2;i++) {
					int nx=now.x+lr[i][0];
					int ny=now.y+lr[i][1];
					if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
					if(visited[nx][ny]!=0&&visited[nx][ny]<visited[now.x][now.y]+1) continue;
					if(map[nx][ny]=='D') continue;
					q.add(new Pos(nx,ny));
					visited[nx][ny]=visited[now.x][now.y]+1;
					
				}
			}
			
		}
	}
}

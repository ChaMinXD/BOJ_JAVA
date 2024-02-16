package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135 {
	static int N;
	static int M;
	static int D;
	static int[][] map;
	static int[][] visited;
	static int[] combi=new int[3];
	static int way=3;
	static int[][] ways= {{0,-1},{-1,0},{0,1}};
	static ArrayList<Integer> ans=new ArrayList();
	static class Position{
		int x;
		int y;
		Position(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		combi(map,0);
		System.out.println(Collections.max(ans));
		

	}
	static void combi(int[][] map,int cnt) {
		int[][] copy=new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				copy[i][j]=map[i][j];
			}
		}
		if(cnt==3) {
			int a=0;
			for(int i=0;i<N;i++) {
				Position[] change=new Position[3];
				
				for(int j=0;j<3;j++) {
					change[j]=BFS(copy,N-1,combi[j],1);	
				}
				for(int j=0;j<3;j++) {
					if(change[j].x==-1) continue;
					if(copy[change[j].x][change[j].y]==1) {
						copy[change[j].x][change[j].y]+=1;
						a++;
					}	
				}
				
				pushMap(copy);
			}
			ans.add(a);

			return;
		}
		for(int i=0;i<M;i++) {
			if(cnt==0||(cnt>0&&combi[cnt-1]<i)) {
				combi[cnt]=i;
				combi(copy,cnt+1);
			}
		}
	}
	static void pushMap(int[][] m) {
		for(int i=N-1;i>0;i--) {
			for(int j=0;j<M;j++) {
				m[i][j]=m[i-1][j];
			}
		}
		Arrays.fill(m[0], 0);
		
	}
	static Position BFS(int[][] m,int x,int y,int cnt) {
		Queue<Position> q =new LinkedList();
		q.add(new Position(x,y));
		visited=new int[N][M];
		visited[x][y]=cnt;
		
		if(m[x][y]==1)
			return new Position(x,y);
		while(!q.isEmpty()) {
			Position now=q.poll();

			if(visited[now.x][now.y]==D)
				break;
			for(int i=0;i<way;i++) {
				int nx=now.x+ways[i][0];
				int ny=now.y+ways[i][1];
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if(visited[nx][ny]!=0) continue;
				if(m[nx][ny]==1) {
					return new Position(nx,ny);
				}
				else {
					visited[nx][ny]=visited[now.x][now.y]+1;
					q.add(new Position(nx,ny));
				}
				
			}
		}
		return new Position(-1,-1);
		
	}

}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_핀볼 {
	static int T,N;
	static int[][] map;
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; // 상 우 하 좌
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static int[][] next_dir= {{0,1,2,3},{2,3,1,0},{1,3,0,2},{3,2,0,1},{2,0,3,1},{2,3,0,1}};
	static Queue<Pos> q=new ArrayDeque();
	static int ans;
	static ArrayList<Pos>[] worm;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine().trim());
		StringBuilder sb=new StringBuilder();

		for(int t=0;t<T;t++) {
			ans=0;
			N=Integer.parseInt(br.readLine().trim());
			map=new int[N][N];
			worm=new ArrayList[5];
			for(int i=0;i<5;i++) {
				worm[i]=new ArrayList();
			}
			for(int i=0;i<N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==0) {
						q.add(new Pos(i,j));
					}
					else if(map[i][j]>5&&map[i][j]<11) {
						int a=map[i][j]-6;
						worm[a].add(new Pos(i,j));
					}
				}
			}
			Move();
			sb.append("#").append(t+1).append(" ").append(ans).append("\n");			
			
		}
		System.out.println(sb.toString());
	}
	static void Move() {
		int cnt=q.size();
		for(int i=0;i<cnt;i++) {
			Pos now=q.poll();
			for(int j=0;j<4;j++) {
				int count=0;
				int now_dir=j;
				int nx=now.x;
				int ny=now.y;
				while(true) {
					if(map[nx][ny]>-1&&map[nx][ny]<6) {
						now_dir=next_dir[map[nx][ny]][now_dir];
						if(map[nx][ny]!=0) 
							count++;					
					}
					else if(map[nx][ny]>=6) {
						int a=map[nx][ny]-6;
						if(worm[a].get(0).x==nx&&worm[a].get(0).y==ny) {
							nx=worm[a].get(1).x;
							ny=worm[a].get(1).y;
						}
						else {
							nx=worm[a].get(0).x;
							ny=worm[a].get(0).y;
						}
					}
					else if(map[nx][ny]==-1) {
						break;
					}
					nx=nx+dir[now_dir][0];
					ny=ny+dir[now_dir][1];
					if(nx<0||nx>N-1||ny<0|ny>N-1) {
						now_dir=next_dir[5][now_dir];
						nx=nx+dir[now_dir][0];
						ny=ny+dir[now_dir][1];
						count++;
					}

					if(nx==now.x&&ny==now.y)
						break;
				}
			ans=Math.max(ans, count);

			}
		}
	}
}

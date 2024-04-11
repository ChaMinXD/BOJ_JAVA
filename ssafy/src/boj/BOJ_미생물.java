package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_미생물 {
	static int T,N,M,K;
	static Bug[][] map;
	static ArrayList<Bug>[][] next;
	static int[][] dir= {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
	static class Bug{
		int v;
		int d;
		Bug(int v,int d) {
			this.v=v;
			this.d=d;
		}
	}
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}

	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();

		for(int t=1;t<=T;t++) {
			Queue<Pos> q=new ArrayDeque();
			Queue<Pos> dup=new ArrayDeque();
			ans=0;
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			map=new Bug[N][N];
			for(int i=0;i<K;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				q.add(new Pos(a,b));
				map[a][b]=new Bug(c,d);
			}
			for(int i=0;i<M;i++) {
				int size=q.size();
				next=new ArrayList[N][N];
				for(int z=0;z<N;z++) {
					for(int x=0;x<N;x++) {
						next[z][x]=new ArrayList();
					}
				}
				for(int j=0;j<size;j++) {		
					Pos now=q.poll();
					int val=map[now.x][now.y].v;
					int d=map[now.x][now.y].d;
					int nx=now.x+dir[d][0];
					int ny=now.y+dir[d][1];
					if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
					if(nx==0||nx==N-1||ny==0||ny==N-1) {
						val/=2;
						if(d==1||d==3)
							d+=1;
						else
							d-=1;
					}
					if(!next[nx][ny].isEmpty()) {
						next[nx][ny].add(new Bug(val,d));
						if(next[nx][ny].size()==2) {
							dup.add(new Pos(nx,ny));
						}
					}
					else {
						next[nx][ny].add(new Bug(val,d));
						q.add(new Pos(nx,ny));
					}	
				}
				while(!dup.isEmpty()) {
					Pos now=dup.poll();
					int sum=0;
					int d=0;
					int max=0;
					for(int k=0;k<next[now.x][now.y].size();k++) {
						sum+=next[now.x][now.y].get(k).v;
						max=Math.max(max, next[now.x][now.y].get(k).v);
						if(next[now.x][now.y].get(k).v==max)
							d=next[now.x][now.y].get(k).d;
					}
					next[now.x][now.y].set(0, new Bug(sum,d));

				}
				for(int j=0;j<N;j++) {
					for(int k=0;k<N;k++) {
						if(!next[j][k].isEmpty()) {
							map[j][k]=next[j][k].get(0);
						}
						else {
							map[j][k]=null;
						}
					}
				}
				
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]!=null) 
						ans+=map[i][j].v;
					
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			
		}
		
		System.out.println(sb.toString());
		
	}

}

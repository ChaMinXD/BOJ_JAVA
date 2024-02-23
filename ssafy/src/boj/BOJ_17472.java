package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_17472 {
	static int N;
	static int M;
	static int[][] map;
	// up -1 0 down 1 0 left 0 -1 right 0 1
	static int[][] ways= {{-1,0},{1,0},{0,-1},{0,1}};
	static boolean[][] visited;
	static boolean[] is_visited;
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static class Load implements Comparable<Load>{
		int e;
		int v;
		Load(int e,int v){
			this.e=e;
			this.v=v;
		}
		@Override
		public int compareTo(Load o) {
			return this.v-o.v;
		}
	}
	static int max=1000;
	static int cnt=2;
	static int[][] bridge;
	static int ans=0;
	static ArrayList<Integer> pr=new ArrayList();
	public static void main(String[] args) throws IOException {
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
			}
		}
		grouping();
		cnt-=2;
		bridge=new int[cnt][cnt];
		for(int i=0;i<cnt;i++) {
			for(int j=0;j<cnt;j++) {
				if(i==j) bridge[i][j]=0;
				else bridge[i][j]=max;
			}
		}
	
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0) {
					int is_num=map[i][j];
					for(int k=0;k<4;k++) {
						int b_cnt=0;
						for(int z=1;z<N+M;z++) {
							int nx=i+(ways[k][0]*z);
							int ny=j+(ways[k][1]*z);
							if(nx<0||nx>N-1||ny<0||ny>M-1) continue;								
							if(map[nx][ny]==0) {
								b_cnt++;
							}								
							else {
								if(b_cnt>1) {
									int is_next=map[nx][ny];
									if(map[nx][ny]!=is_num)
										bridge[is_num-2][is_next-2]=Math.min(bridge[is_num-2][is_next-2], b_cnt);
								}
								break;
							}
						}
					}
				}
			}
		}
	
		Prim();

		if(ans==0||ans>=max)
			ans=-1;
		if(pr.size()!=cnt)
			ans=-1;
		System.out.println(ans);
		
	}
	static void Prim() {
		PriorityQueue<Load> q=new PriorityQueue();
		is_visited=new boolean[cnt];
		q.add(new Load(0,0));
		int is_count=0;
		while(!q.isEmpty()) {
			Load now=q.poll();
			if(is_visited[now.e]) continue;
			is_visited[now.e]=true;
			pr.add(now.e);
			ans+=now.v;
			is_count++;
			max=Math.max(max, now.v);
			if(is_count==cnt) break;
			for(int i=0;i<cnt;i++) {
				if(bridge[now.e][i]!=0&&!is_visited[i]) {
					q.add(new Load(i,bridge[now.e][i]));	
				}
			}		
		}
	}
	static void grouping() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==1) {
					BFS(new Pos(i,j));
					
				}
				
			}
		}
	}
	static void BFS(Pos p) {
		Queue<Pos> q=new LinkedList();
		visited=new boolean[N][M];
		q.add(p);
		map[p.x][p.y]=cnt;
		while(!q.isEmpty()) {
			Pos now=q.poll();
			visited[now.x][now.y]=true;
			for(int i=0;i<4;i++) {
				int nx=now.x+ways[i][0];
				int ny=now.y+ways[i][1];
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==0) continue;
				visited[nx][ny]=true;
				map[nx][ny]=cnt;
				q.add(new Pos(nx,ny));
			}
		}
		cnt++;
	}
	

}

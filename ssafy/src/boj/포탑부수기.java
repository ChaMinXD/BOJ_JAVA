package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 포탑부수기 {
	static int N,M,K;
	static int[][] map;
	static int[][] count;
	static class Pos{
		int x;int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static Pos attack;
	static Pos target;
	static int max;
	static int min;
	static int[][] ways= {{0,1},{1,0},{0,-1},{-1,0},{-1,-1},{-1,1},{1,1},{1,-1}};
	// 4까지는 우 하 좌 상
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
	
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		count=new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int k=0;k<K;k++) {
			min=Integer.MAX_VALUE;
			max=-1;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					makeTarget(i,j);
				}
			}
			
			if(target.x==attack.x&&target.y==attack.y) break;
			attack();
			
	}
		System.out.println(getAns());
	}
	static void attack() {
		Pos[][] back=new Pos[N][M];
		Queue<Pos> q=new ArrayDeque();
		boolean[][] visited=new boolean[N][M];
		q.add(attack);
		int dmg=map[attack.x][attack.y]+N+M;
		map[attack.x][attack.y]=dmg;
		visited[attack.x][attack.y]=true;
		a:while(!q.isEmpty()) {
			Pos now=q.poll();
			for(int i=0;i<4;i++) {
				int nx=now.x+ways[i][0];
				int ny=now.y+ways[i][1];
				nx = (nx < 0) ? N-1 : (nx > N-1) ? 0 : nx;
				ny = (ny < 0) ? M-1 : (ny > M-1) ? 0 : ny;
				if(visited[nx][ny]) continue;
				visited[nx][ny]=true;
				if(nx==target.x&&ny==target.y) {
					back[nx][ny]=new Pos(now.x,now.y);
					break a;
				}
				if(map[nx][ny]==0) continue;
				q.add(new Pos(nx,ny));
				back[nx][ny]=new Pos(now.x,now.y);
			}
		}
		boolean flag=visited[target.x][target.y];
		visited=new boolean[N][M]; // 공격에 관여된 포탑인지 ?
		if(flag) {
			Pos now=target;
			map[now.x][now.y]-=dmg;
			visited[now.x][now.y]=true;

			while(true) {
				now=back[now.x][now.y];
				visited[now.x][now.y]=true;
				if(now.x==attack.x&&now.y==attack.y) break;
				map[now.x][now.y]-=(dmg/2);
			}
		}else {// 포탄투척
			map[target.x][target.y]-=dmg;
			visited[target.x][target.y]=true;
			visited[attack.x][attack.y]=true;
			for(int i=0;i<8;i++) {
				int nx=target.x+ways[i][0];
				int ny=target.y+ways[i][1];
				nx = (nx < 0) ? N-1 : (nx > N-1) ? 0 : nx;
				ny = (ny < 0) ? M-1 : (ny > M-1) ? 0 : ny;
				if(visited[nx][ny]) continue;
				visited[nx][ny]=true;
				map[nx][ny]-=(dmg/2);
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				count[i][j]++;
				if(map[i][j]<0)
					map[i][j]=0;
				if(map[i][j]==0) continue;
				if(visited[i][j]) continue;
				map[i][j]++;
			}
		}
		count[attack.x][attack.y]=0;
		
	}
	static void makeTarget(int i,int j) {
		if(map[i][j]==0) return;
		if(map[i][j]<min) {
			min=map[i][j];
			attack=new Pos(i,j);
		}else if(map[i][j]==min) {
			if(count[i][j]==count[attack.x][attack.y]) {
				if(i+j>attack.x+attack.y) 
					attack=new Pos(i,j);
				else if(i+j==attack.x+attack.y) {
					if(j>attack.y)
						attack=new Pos(i,j);
				}
			}else {
				if(count[i][j]<count[attack.x][attack.y])
					attack=new Pos(i,j);
			}
		}
		
		
		if(map[i][j]>max) {
			max=map[i][j];
			target=new Pos(i,j);
		}else if(map[i][j]==max) {
			if(count[i][j]==count[target.x][target.y]) {
				if(i+j<target.x+target.y) 
					target=new Pos(i,j);
				else if(i+j==target.x+target.y) {
					if(j<target.y)
						target=new Pos(i,j);
				}
			}else {
				if(count[i][j]>count[target.x][target.y])
					target=new Pos(i,j);
			}
		}
	}
	
	static int getAns() {
		int m=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				m=Math.max(m, map[i][j]);
			}
		}
		return m;
	}
}

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460 {
	static int N,M;
	static char[][] map;
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static Pos o;
	static Pos r;
	static Pos b;
	static int[][] dir= {{1,0},{-1,0},{0,1},{0,-1}}; // 하 상 우좌 
	static int ans=Integer.MAX_VALUE;
	static int check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new char[N][M];
		for(int i=0;i<N;i++) {
			char[] c=br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				map[i][j]=c[j];
				if(map[i][j]=='R') {
					map[i][j]='.';
					r=new Pos(i,j);
				}
				if(map[i][j]=='B') {
					map[i][j]='.';
					b=new Pos(i,j);
				}
				if(map[i][j]=='O')
					o=new Pos(i,j);
			}
		}

		dfs(0);
		if(ans==Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(ans);
		}
		
	
	}
	static void dfs(int cnt) {
		if(cnt>ans||cnt>9)
			return;
		for(int i=0;i<4;i++) {
			check=0;
			int now_rx=r.x;
			int now_ry=r.y;
			int now_bx=b.x;
			int now_by=b.y;
			move(i,cnt);
			if(check==0)
				dfs(cnt+1);
			else if(check==1) {
				ans=Math.min(ans, cnt+1);
				return;
			}
			else
				return;
			r=new Pos(now_rx,now_rx);
			b=new Pos(now_bx,now_by);
		}
	}
	static void move(int d,int cnt) {
		boolean fir=true; // true 일떄는 r부터 flase 일때는 b부터
		if(d<2) { // 0 하 1 상 2 우 3 좌
			if(r.y==b.y) {
				if(r.x>b.x&&d==1)
					fir=false;
				if(r.x<b.x&&d==0)
					fir=false;
			}
		}else {
			if(r.x==b.x) {
				if(r.y>b.y&&d==3)
					fir=false;
				if(r.y<b.y&&d==2)
					fir=false;
			}
		}
		
		if(fir) {
			while(true) {
				int nx=r.x+dir[d][0];
				int ny=r.y+dir[d][1];
				if(map[nx][ny]=='.') {
					r=new Pos(nx,ny);
					continue;
				}
				else if(map[nx][ny]=='O') {
					r=new Pos(nx,ny);
					check=1;
					break;
				}
				else
					break;
			}

			while(true) {
				int nx=b.x+dir[d][0];
				int ny=b.y+dir[d][1];
				if(map[nx][ny]=='.') {
					b=new Pos(nx,ny);
					continue;
				}
				else if(map[nx][ny]=='O') {
					check=2;
					return;
				}
				else
					break;
			}
			if(r.x==b.x&&r.y==b.y) {
				int nx=b.x-dir[d][0];
				int ny=b.y-dir[d][1];
				b=new Pos(nx,ny);
			}


		}else {
			while(true) {
				int nx=b.x+dir[d][0];
				int ny=b.y+dir[d][1];
				if(map[nx][ny]=='.') {
					b=new Pos(nx,ny);
					continue;
				}
				else if(map[nx][ny]=='O') {
					check=2;
					return;
				}
				else
					break;
			}

			while(true) {
				int nx=r.x+dir[d][0];
				int ny=r.y+dir[d][1];
				if(map[nx][ny]=='.') {
					r=new Pos(nx,ny);
					continue;
				}
				else if(map[nx][ny]=='O') {
					r=new Pos(nx,ny);
					check=1;
					return;
				}
				else
					break;
			}
			if(r.x==b.x&&r.y==b.y) {
				int nx=r.x-dir[d][0];
				int ny=r.y-dir[d][1];
				r=new Pos(nx,ny);
			}
			
		}
		
	}

}

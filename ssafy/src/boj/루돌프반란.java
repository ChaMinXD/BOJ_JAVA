package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 루돌프반란 {
	static int N,M,P,C,D;
    static class Pos{
        int x;int y;
        Pos(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    static Pos rudolp;
    static Pos[] santa;
    static boolean[] isOut;
    static int[][] map;
    static int[][] ways= {{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
    static int[] score;
    static int[] isStun;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		P=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		int a=Integer.parseInt(st.nextToken())-1;
		int b=Integer.parseInt(st.nextToken())-1;
		rudolp=new Pos(a,b);
		map=new int[N][N];
		map[a][b]=-1;
		santa=new Pos[P];
		score=new int[P];
		isOut=new boolean[P];
		isStun=new int[P];
		for(int i=0;i<P;i++) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken())-1;
			b=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			santa[a]=new Pos(b,c);
			map[b][c]=a+1;
		}
		
		for(int i=0;i<M;i++) {
			moveR();
			if(check())
				break;
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					System.out.print(map[j][k]+" ");
				}
				System.out.println();
			}
			moveS();
			if(check())
				break;
			System.out.println();

			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					System.out.print(map[j][k]+" ");
				}
				System.out.println();
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<P;i++) {
			sb.append(score[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
	static void moveS() {
		for(int i=0;i<P;i++) {
			if(isStun[i]>0) {
				isStun[i]--;
				continue;
			}
			int dis=Math.abs(rudolp.x-santa[i].x)+Math.abs(rudolp.y-santa[i].y);
			int dir=-1;
			map[santa[i].x][santa[i].y]=0;
			for(int j=0;j<4;j++) {
				int nx=santa[i].x+ways[i][0];
				int ny=santa[i].y+ways[i][1];
				if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
				int ndis=Math.abs(nx-rudolp.x)+Math.abs(ny-rudolp.y);
				if(ndis>dis) continue;
				if(map[nx][ny]>0) continue;
				dir=j;
				if(map[nx][ny]==-1) {
					isStun[i]=1;
					score[i]+=D;
					dir=dir+2%4;
					nx=nx+ways[dir][0]*D;
					ny=ny+ways[dir][1]*D;
					if(nx<0||nx>N-1||ny<0||ny>N-1) {
						isOut[i]=true;
						break;
					}
					if(map[nx][ny]!=0) {
						interaction(nx,ny,dir);
						map[nx][ny]=i+1;
						santa[i]=new Pos(nx,ny);
					}
				}
				map[nx][ny]=i+1;
				santa[i]=new Pos(nx,ny);
			}

		}
	}
	static void moveR() {
		int min=Integer.MAX_VALUE;
		int idx=-1;
		for(int i=0;i<P;i++) {
			int x=rudolp.x-santa[i].x;
			int y=rudolp.y-santa[i].y;
			int dis=x*x+y*y;
			if(min>dis) {
				min=dis; idx=i;
			}else if(min==dis) {
				if(santa[idx].x<santa[i].x)
					idx=i;
				else if(santa[idx].x==santa[i].x) {
					if(santa[idx].y<santa[i].y)
						idx=i;
				}
			}	
		}
		map[rudolp.x][rudolp.y]=0;
		int dis=Math.abs(rudolp.x-santa[idx].x)+Math.abs(rudolp.y-santa[idx].y);
		int dir=-1;
		for(int i=0;i<8;i++) {
			int nx=rudolp.x+ways[i][0];
			int ny=rudolp.y+ways[i][1];
			if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
			int ndis=Math.abs(nx-santa[idx].x)+Math.abs(ny-santa[idx].y);
			if(ndis>=dis) continue;
			dis=ndis;
			dir=i;
			rudolp=new Pos(nx,ny);
		}
		if(map[rudolp.x][rudolp.y]!=0) {
			int s=map[rudolp.x][rudolp.y]-1;
			score[s]+=C;
			isStun[s]=2;
			int nx=rudolp.x+ways[dir][0]*C;
			int ny=rudolp.y+ways[dir][1]*C;
			if(nx<0||nx>N-1||ny<0||ny>N-1) {
				isOut[s]=true;
				map[rudolp.x][rudolp.y]=-1;
				return;
			}
			if(map[nx][ny]!=0) {
				interaction(nx,ny,dir);
				map[nx][ny]=s+1;
				santa[s]=new Pos(nx,ny);
			}
		}		
		map[rudolp.x][rudolp.y]=-1;
	}
	static void interaction(int x,int y,int dir) {
		int s=map[x][y];
		int nx=x+ways[dir][0];
		int ny=y+ways[dir][1];
		if(nx<0||nx>N-1||ny<0||ny>N-1) {
			isOut[s]=true;
		}
		if(map[nx][ny]!=0) {
			interaction(nx,ny,dir);
			map[nx][ny]=s;
			santa[s-1]=new Pos(nx,ny);
		}
	}
	static boolean check() {
		for(int i=0;i<P;i++) {
			if(!isOut[i])
				return false;
		}
		return true;
	}
		
}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ {
	static int N,M;
	static char[][] map;
	static int[] parent;
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new char[N][M];
		for(int i=0;i<N;i++) {
			char[] c=br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				map[i][j]=c[j];
			}
		}
		parent=new int[N*M];
		for(int i=0;i<N*M;i++) {
			parent[i]=i;
		}
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				Queue<Pos> q =new ArrayDeque();
				if(map[i][j]=='O') continue;
				q.add(new Pos(i,j));
				while(!q.isEmpty()) {
					Pos now=q.poll();
					int nx,ny;
					if(map[now.x][now.y]=='D') {
						nx=now.x+1;
						ny=now.y;
					}else if(map[now.x][now.y]=='U') {
						nx=now.x-1;
						ny=now.y;
					}else if(map[now.x][now.y]=='L') {
						nx=now.x;
						ny=now.y-1;
					}else  {
						nx=now.x;
						ny=now.y+1;
					}
					if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
					map[now.x][now.y]='O';

					if(map[nx][ny]=='O')
						break;
					q.add(new Pos(nx,ny));
				}
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}
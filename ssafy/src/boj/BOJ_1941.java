package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_1941 {
	static int[][] map=new int[5][5];
	static int[] list=new int[7];
	static boolean[] visited;
	static int way=4;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	static int ans=0;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<5;i++) {
			char[] c=br.readLine().toCharArray();
			for(int j=0;j<5;j++) {
				if(c[j]=='Y') 
					map[i][j]=1;
				
				else 
					map[i][j]=2;
			}
		}
		visited=new boolean[25];
		DFS(0,0);
		System.out.println(ans);
		
		
	}
	static void DFS(int cnt,int Y) {
		if(Y>3)
			return;
		if(cnt==7) {
			if(check())
				ans++;
			return;
		}
		for(int i=0;i<25;i++) {
			if(visited[i]) continue;
			if(cnt!=0) {
				if(list[cnt-1]>=i) continue;
			}	
			visited[i]=true;
			list[cnt]=i;
			if(map[i/5][i%5]==1)
				DFS(cnt+1,Y+1);
			else
				DFS(cnt+1,Y);
			visited[i]=false;
			
		}	
	}
	static boolean check() {
		Queue<Pos> q=new ArrayDeque();
		boolean[] checked=new boolean[7];
		int x=list[0]/5;
		int y=list[0]%5;
		q.add(new Pos(x,y));
		checked[0]=true;
		while(!q.isEmpty()) {
			Pos now=q.poll();
			for(int i=0;i<way;i++) {
				int nx=now.x+ways[i][0];
				int ny=now.y+ways[i][1];
				if(nx<0||nx>4||ny<0||ny>4) continue;
				for(int j=0;j<7;j++) {
					if(nx==list[j]/5&&ny==list[j]%5) {
						if(checked[j]) continue;
						checked[j]=true;
						q.add(new Pos(nx,ny));
					}
				}
			}
		}
		
		for(int i=0;i<7;i++) {
			if(!checked[i]) return false;
		}
		return true;
	}

}

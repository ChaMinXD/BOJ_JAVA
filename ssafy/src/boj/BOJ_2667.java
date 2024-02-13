package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2667 {
	static class position{
		int x;
		int y;
		position(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static int N;
	static int[][] map;
	static boolean [][] visited;
	static Queue<position> q=new LinkedList();
	static int way=4;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	static ArrayList<Integer> ans=new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		visited=new boolean[N][N];
		for(int i=0;i<N;i++) {
			char[] line=br.readLine().toCharArray();
			for(int j=0;j<N;j++) {
				map[i][j]=line[j]-'0';
			}
		}
	
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==0) continue;
				if(visited[i][j]) continue;
				int cnt=0;
				q.add(new position(i,j));
				visited[i][j]=true;
				cnt++;
				while(!q.isEmpty()) {
					position now=q.poll();
					int x=now.x;
					int y=now.y;

					for(int k=0;k<way;k++) {
						int nx=x+ways[k][0];
						int ny=y+ways[k][1];
						if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
						if(map[nx][ny]==0) continue;
						if(visited[nx][ny]) continue;
						q.add(new position(nx,ny));
						visited[nx][ny]=true;
						cnt++;
					}
				}
				ans.add(cnt);
			}
		}
		System.out.println(ans.size());
		Collections.sort(ans);
		for(int i=0;i<ans.size();i++) {
			System.out.println(ans.get(i));
		}
		
	}

}

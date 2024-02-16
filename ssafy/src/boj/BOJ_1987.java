package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1987 {
	static int R;
	static int C;
	static char[][] map;
	static int[][] visited;
	static int way=4;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	static ArrayList<Integer> isAlready=new ArrayList();	
	static ArrayList<Integer> ans=new ArrayList();
	static int max=-1;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		map=new char[R][C];
		visited=new int[R][C];
		for(int i=0;i<R;i++) {
			char[] c=br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				map[i][j]=c[j];
			}
		}
		isAlready.add(map[0][0]-'0');
		visited[0][0]=1;
		dfs(0,0,1);
		System.out.println(Collections.max(ans));
		
	}
	static void dfs(int x,int y,int cnt) {
		boolean check=false;
		for(int i=0;i<way;i++) {
			int nx=x+ways[i][0];
			int ny=y+ways[i][1];
			if(nx<0||nx>R-1||ny<0||ny>C-1) continue;
			if(visited[nx][ny]!=0) continue;
			if(isAlready.contains(map[nx][ny]-'0')) continue;
			visited[nx][ny]=cnt+1;
			isAlready.add(map[nx][ny]-'0');

			check=true;
			dfs(nx,ny,cnt+1);
			isAlready.remove(isAlready.size()-1);
			visited[nx][ny]=0;
		}
		if(!check) {
			ans.add(cnt);
		}
	}
	
	
	

}

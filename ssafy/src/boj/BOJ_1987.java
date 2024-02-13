package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	static int max=-1;
	static class Position{
		int x;
		int y;
		Position(int x,int y){
			this.x=x;
			this.y=y;
		}
	}

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
		visited[0][0]=1;
		

	
	}
	static void dfs() {
		
	}
	
	
	

}

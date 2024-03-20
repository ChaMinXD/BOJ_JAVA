package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19236 {
	static int[][] map;
	static int[][] dir;
	static class Pos{
		int x;
		int y;
		int dir;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map=new int[4][4];
		dir=new int[4][4];
		for(int i=0;i<4;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) {
				int v=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				map[i][j]=v;
				dir[i][j]=d;
			}
		}
		
	}

}

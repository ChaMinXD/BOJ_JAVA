package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_Gyukza {
	static int T;
	static int[][] map=new int[4][4];
	static int way=4;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int i=0;i<4;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int count=0;
		//dfs(count); 해쉬셋 쓰자
	}
	private static void dfs(int num,int count) {
		if(count==7) {
			
		}
		
	}
	

}

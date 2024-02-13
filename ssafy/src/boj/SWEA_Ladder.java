package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_Ladder {
	static int T=10;
	static int N=100,M;
	static int[][] ladder;
	static boolean[][] visited;
	static int way=2;
	static int[][] ways= {{0,1},{0,-1}};
	static int[] ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		ans=new int[T];
		for (int t = 0; t < T; t++) {
			
			M=Integer.parseInt(br.readLine());
			ladder=new int[N][N];
			visited=new boolean[N][N];
			for(int i=0;i<N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					if(ladder[i][j]==1)
						visited[i][j]=true;
				}
			}
			int depth=0;
			for(int i=0;i<N;i++) {
				if(ladder[0][i]==0) continue;
				dfs(1,i,i);
			}
			System.out.println("#"+M+" "+ans[M-1]);
			
		}

	}
	private static void dfs(int depth,int row,int start) {
		if(depth==N-1) {
			if(ladder[depth][row]==2)
				ans[M-1]=start;
			return;
		}
		for(int j=0;j<way;j++) {
			int ny=row+ways[j][1];
			if(ny<0||ny>N-1) continue;
			if(ladder[depth][ny]==0) continue;
			if(!visited[depth][ny]) continue;
				visited[depth][row]=false;
				dfs(depth,ny,start);
				visited[depth][row]=true;
				return;
			
			
			
		}
		
			visited[depth][row]=false;
			dfs(depth+1,row,start);
			visited[depth][row]=true;

	}
	
}

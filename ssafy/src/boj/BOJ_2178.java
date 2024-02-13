package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {
	static int N;
	static int M;
	static int[][] list;
	static int way=4;
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	static int[][] visited;
	public static class position{
		int x;
		int y;
		position(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static Queue<position> q=new LinkedList();
	public static void main(String[] args) throws IOException {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			list=new int[N][M];
			for(int i=0;i<N;i++) {
				char[] l=br.readLine().toCharArray();
				for(int j=0;j<M;j++) {
					list[i][j]=l[j]-'0';
				}
			}
			visited=new int[N][M];
			visited[0][0]=1;
			q.add(new position(0,0));
			while(!q.isEmpty()) {
				
				int x=q.peek().x;
				int y=q.peek().y;
				//System.out.println("x : "+x+"y : "+y);
				q.poll();
				
				if(x==N-1&&y==M-1) {
					break;
				}
					
				for(int i=0;i<way;i++) {
					int nx=x+ways[i][0];
					int ny=y+ways[i][1];
					if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
					if(list[nx][ny]==0) continue;
					if(visited[nx][ny]!=0) continue;
					q.add(new position(nx,ny));
					visited[nx][ny]=visited[x][y]+1;
				}
			}
		
			System.out.println(visited[N-1][M-1]);
	}
	
	
}


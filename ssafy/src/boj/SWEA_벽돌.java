package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_벽돌 {
	static int T,N,W,H;
	static int map[][];
	static int[][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
	static int[] sub;
	static int ans;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int t=1;t<T+1;t++) {
			ans=Integer.MAX_VALUE;
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			map=new int[H][W];
			sub=new int[N];

			for(int i=0;i<H;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			subset(0);	
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void subset(int cnt) {
		if(cnt==N) {
			int[][] copy_map=new int[H][W];
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					copy_map[i][j]=map[i][j];
				}
			}
			for(int k=0;k<N;k++) {
				int idx=sub[k];
				for(int x=0;x<H;x++) {
					visited=new boolean[H][W];
					if(copy_map[x][idx]==0) continue;
					int a=copy_map[x][idx];
					shot(x,idx,copy_map,a);
					move(copy_map);
					break;
				}
			}
			int count=0;
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(copy_map[i][j]==0) continue;
					count++;
				}
			}
	
			ans=Math.min(ans, count);
			return;
		}
		for(int i=0;i<W;i++) {
			sub[cnt]=i;
			subset(cnt+1);
			sub[cnt]=0;
		}
	}
	static void shot(int x,int y,int[][] m,int num) {
		visited[x][y]=true;
		m[x][y]=0;
		if(num>1) {
			for(int i=1;i<num;i++) {
				for(int j=0;j<4;j++) {
					int nx=x+ways[j][0]*i;
					int ny=y+ways[j][1]*i;
					if(nx<0||nx>H-1||ny<0||ny>W-1) continue;
					if(m[nx][ny]==0) continue;
					if(visited[nx][ny]) continue;
					visited[nx][ny]=true;
					if(m[nx][ny]==1) {
						m[nx][ny]=0;
					}
					else {
						int a=m[nx][ny];
						m[nx][ny]=0;
						shot(nx,ny,m,a);
						
					}
				}
			}
		}	
	}
	static void move(int[][] m) {
		for(int i=0;i<W;i++) {
			for(int j=H-1;j>=0;j--) {
				if(m[j][i]==0) {
					int cnt=0;
					for(int k=j;k>=0;k--) {
						cnt+=m[k][i];
					}
					if(cnt!=0) {
						moveline(m,j,i);
						j++;
					}
				}
			}
		}
	}
	static void moveline(int[][] m,int col,int row) {
		for(int k=col;k>0;k--) {
			m[k][row]=m[k-1][row];
		}
		m[0][row]=0;
	}
	

}

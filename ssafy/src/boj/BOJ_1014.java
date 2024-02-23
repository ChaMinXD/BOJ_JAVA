package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1014 {
	static int T;
	static int N,M;
	static char[][] map;
	static int[][] cnt_map;
	static int way=4;
	static int min;
	static int[][] ways= {{0,-1},{0,1},{-1,-1},{-1,1}};
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			map=new char[N][M];
			cnt_map=new int[N][M];
			ans=0;
			for(int i=0;i<N;i++) {
				char[] c=br.readLine().toCharArray();
				for(int j=0;j<M;j++) {
					map[i][j]=c[j];
				}
			}
		
			
			while(isEnd()) {
				check();
				a:for(int i=0;i<N;i++) {
					for(int j=0;j<M;j++) {
						if(cnt_map[i][j]==min) {
							setStudent(i,j);
							ans++;
							break a;
						}
					}
				}
				System.out.println(ans);

			}
			//System.out.println(ans);
			
		}
	}
	static void check() {
		min=Integer.MAX_VALUE/10;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				cnt_map[i][j]=-1;
			}
			}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]=='x') continue;
				if(map[i][j]=='o') continue;
				int cnt=0;
				b:for(int k=0;k<way;k++) {
					int nx=i+ways[k][0];
					int ny=j+ways[k][1];
					if(nx<0||nx>N-1||ny<0||ny>M-1) {
						cnt++;
						continue;
					}
					if(map[nx][ny]=='o') {
						map[i][j]='x';
						break b;
					}
					
					if(map[nx][ny]=='.') cnt++;
				}
				cnt_map[i][j]=cnt;
				min=Math.min(min, cnt);
				
			}
		}
	
	}
	static boolean isEnd() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]=='.') return true;
			}
		}
		return false;
	}
	static void setStudent(int x,int y) {
		map[x][y]='o';
		cnt_map[x][y]=-1;
		for(int i=0;i<way;i++) {
			int nx=x+ways[i][0];
			int ny=y+ways[i][1];
			if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
			if(map[nx][ny]=='x') continue;
			
			map[nx][ny]='x';
			cnt_map[nx][ny]=-1;
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

}

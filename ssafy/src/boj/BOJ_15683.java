package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15683 {
	static int[][] ways= {{-1,0},{0,-1},{1,0},{0,1}};
	static int[][] cc1= {{0},{1},{2},{3}};
	static int[][] cc2= {{0,2},{1,3}};
	static int[][] cc3= {{0,1},{1,2},{2,3},{0,3}};
	static int[][] cc4= {{0,1,2},{0,1,3},{0,2,3},{1,2,3}};
	static int[] cc5= {0,1,2,3};
	static int[] type= {4,2,4,4,1};
	static int N,M;
	static int[][] map;
	static ArrayList<Pos> cctv=new ArrayList();
	static int[] combi;
	static int max=64;
	static class Pos {
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]!=0&&map[i][j]!=6)
					cctv.add(new Pos(i,j));
			}
		}
		combi=new int[cctv.size()];
		Perm(0);
		System.out.println(max);
		
	}
	static void Perm(int cnt) {
		if(cnt==cctv.size()) {
			int[][] copy=new int[N][M];
			for(int a=0;a<N;a++) {
				for(int b=0;b<M;b++) {
					copy[a][b]=map[a][b];
				}
			}
			for(int i=0;i<cctv.size();i++) {
				Pos fir=cctv.get(i);
				int cctv_type=copy[fir.x][fir.y]-1;

				switch (cctv_type) {
				case 0: {
					int curx=fir.x;
					int cury=fir.y;
					for(int k=0;k<N+M;k++) {
						int w=cc1[combi[i]][0];
						int nx=curx+ways[w][0];
						int ny=cury+ways[w][1];
						if(nx<0||nx>N-1||ny<0||ny>M-1) break;
						if(copy[nx][ny]==6) break;
						if(copy[nx][ny]==0) {
							copy[nx][ny]=-1;
							
							
						}
						curx=nx;
						cury=ny;
					}				
					break;
				}			
				case 1: {
						for(int m=0;m<2;m++) {
							int curx=fir.x;
							int cury=fir.y;
							for(int k=0;k<N+M;k++) {
								int w=cc2[combi[i]][m];
								int nx=curx+ways[w][0];
								int ny=cury+ways[w][1];
								if(nx<0||nx>N-1||ny<0||ny>M-1) break;
								if(copy[nx][ny]==6) break;
								if(copy[nx][ny]==0) {
									copy[nx][ny]=-1;
									
								}
								curx=nx;
								cury=ny;	
							}
						}
											
					break;
				}
				case 2: {
					for(int m=0;m<2;m++) {
						int curx=fir.x;
						int cury=fir.y;
						int w=cc3[combi[i]][m];

						for(int k=0;k<N+M;k++) {
							int nx=curx+ways[w][0];
							int ny=cury+ways[w][1];
							if(nx<0||nx>N-1||ny<0||ny>M-1) break;
							if(copy[nx][ny]==6) break;
							if(copy[nx][ny]==0) {
								copy[nx][ny]=-1;
								
							}
							curx=nx;
							cury=ny;
						}
					}			
					break;
								
			}
				case 3: {
					for(int m=0;m<3;m++) {
						int curx=fir.x;
						int cury=fir.y;
						int w=cc4[combi[i]][m];
						
						for(int k=0;k<N+M;k++) {
							int nx=curx+ways[w][0];
							int ny=cury+ways[w][1];
							if(nx<0||nx>N-1||ny<0||ny>M-1) break;
							if(copy[nx][ny]==6) break;
							if(copy[nx][ny]==0) {
								copy[nx][ny]=-1;
							}
							curx=nx;
							cury=ny;
						}
					}
					break;
				}case 4: {			
					for(int m=0;m<cc5.length;m++) {
						int curx=fir.x;
						int cury=fir.y;
						for(int k=0;k<N+M;k++) {
							int w=cc5[m];
							int nx=curx+ways[w][0];
							int ny=cury+ways[w][1];
							if(nx<0||nx>N-1||ny<0||ny>M-1) break;
							if(copy[nx][ny]==6) break;
							if(copy[nx][ny]==0) {
								copy[nx][ny]=-1;
									
							}
							curx=nx;
							cury=ny;
						}
					}	
					break;
			}
			
			}
			
			}
		
			max=Math.min(count(copy), max);	
			return;
		}
		
		
		Pos now=cctv.get(cnt);
		int cctv_type=map[now.x][now.y]-1;
		for(int z=0;z<type[cctv_type];z++) {
			combi[cnt]=z;
			Perm(cnt+1);
		}
	}
	
	static int count(int[][] c) {
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(c[i][j]==0)
					cnt++;
			}
		}
		return cnt;
	}

}

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16927 {
	static int N,M,R;
	static int[][] map;
	static int[][] next_map;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		next_map=new int[N][M];
		for(int j=0;j<N;j++) {
			next_map[j]=map[j].clone();
		}
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
			rotate();
		
		
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}

	}
	static void rotate() {
		int min=((N>M)?M:N)/2;
		for(int i=0;i<min;i++) {
			int row=N-2*i;
			int col=M-2*i;
			int rotate=R%((row+col-2)*2);
			for(int k=0;k<rotate;k++) {
				for(int j=1+i;j<M-i;j++) {
					next_map[i][j-1]=map[i][j];
					next_map[N-1-i][M-j]=map[N-1-i][M-1-j];
				}
				for(int j=1+i;j<N-i;j++) {
					next_map[j][i]=map[j-1][i];
					next_map[N-j-1][M-1-i]=map[N-j][M-1-i];
				}
			
				for(int j=i;j<M-i;j++) {
					map[i][j]=next_map[i][j];
					map[N-1-i][j]=next_map[N-1-i][j];
				}
				for(int j=i;j<N-i;j++) {
					map[j][i]=next_map[j][i];
					map[j][M-1-i]=next_map[j][M-1-i];
					
				}
			}
		
		}
		
	}

}

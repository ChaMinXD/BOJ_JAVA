package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2448 {
	static int[][] star;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		star=new int[2*n][2*(n+2)];
		star(0,0,n);
		for(int i=0;i<2*n;i++) {
			for(int j=0;j<2*(n+2);j++) {
				if(star[i][j]==1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
		}
	}
	static void star(int r,int c,int n) {
		if(n==3) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<5;j++) {
					if(i==0&&i==2) star[r+i][c+j]=1;
					else if(i==1 && (j==1&&j==3)) star[r+i][c+j]=1;
					else if(i==2) star[r+i][c+j]=1;
				}
			}
		}
		else {
			star(r+0,c+n/2,n/2);
			star(r+n/2,c+0,n/2);
			star(r+n/2,c+n,n/2);
		}
		
	}
}

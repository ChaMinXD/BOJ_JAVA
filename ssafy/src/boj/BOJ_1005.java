package boj;

import java.util.Scanner;

public class BOJ_1005 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		int t=scan.nextInt();
		for(int i=0;i<t;i++) {
			int n=scan.nextInt();
			int k=scan.nextInt();
			int[] val=new int[n];
			int[][] list=new int[k][2];
			for(int j=0;j<n;j++) {
				val[j]=scan.nextInt();
			}
			for(int j=0;j<k;j++) {
				list[j][0]=scan.nextInt();
				list[j][1]=scan.nextInt();
			}
			int f=scan.nextInt();
		}
		
	}

}

package boj;

import java.util.Scanner;

public class BOJ_2564 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		int row=scan.nextInt();
		int col=scan.nextInt();
		//int [][] list=new int[col+1][row+1];
		
		int n=scan.nextInt();
		int [][] test=new int[n+1][2];
		int sum=0;
		for(int i=0;i<n+1;i++) {
			int a=scan.nextInt();
			int b=scan.nextInt();
			switch (a) {
			case 1: {			
				//list[0][b]=i+1;
				test[i]=new int[] {0,b};
				break;
			}
			case 2: {
			//	list[col][b]=i+1;
				test[i]=new int[] {col,b};

				break;
						}
			case 3: {
		//		list[b][0]=i+1;
				test[i]=new int[] {b,0};

				break;
			}
			case 4: {
			//	list[b][row]=i+1;
				test[i]=new int[] {b,row};

				break;
			}
			
			}
		}
		for(int i=0;i<n;i++) {
			int x,y;
			x=test[i][1]-test[n][1];
			if(x==0) {
				y=Math.abs(test[i][0]-test[n][0]);
			}
			else {
				y=Math.min(test[i][0]+test[n][0],col-test[i][0]+col-test[n][0]);

			}
			
			if(y==0) {
				x=Math.abs(test[i][1]-test[n][1]);
			}
			else {
				x=Math.min(test[i][1]+test[n][1],row-test[i][1]+row-test[n][1]);
			}
			sum+=(x+y);

		}
		System.out.println(sum);
	}

}

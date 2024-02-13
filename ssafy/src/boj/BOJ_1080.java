package boj;
import java.util.Scanner;

public class BOJ_1080 {
	static int n;
	static int m;
	static char [][] before;
	static char [][] after;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		m=scan.nextInt();
		before=new char[n][m];
		after=new char[n][m];
		int cnt=0;
		for(int i=0;i<n;i++) {
			before[i]=scan.next().toCharArray();
		}
		for(int i=0;i<n;i++) {
			after[i]=scan.next().toCharArray();	
		}
		
		
		for(int i=0;i<n-2;i++) {
			for(int j=0;j<m-2;j++) {
				if(before[i][j]==after[i][j])
					continue;
				cnt++;
				swap(i,j);
			}
		}
		
		if(check()) {
			System.out.println(cnt);
		}
		else {
			System.out.println(-1);
		}
		
	}
	
	static boolean check() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(before[i][j]!=after[i][j])
					return false;
			}
		}
		return true;
	}
	
	static void swap(int x,int y) {
		if((x+2<n)&&(y+2<m)) {
			for(int i=x;i<x+3;i++) {
				for(int j=y;j<y+3;j++) {
					before[i][j]=before[i][j]=='0'? '1':'0';
					
			}
		}
	}
	
}
}


package boj;

import java.util.Scanner;

public class BOJ_1629 {
	static int A;
	static int B;
	static int C;
	public static void main(String[] args) {
			
		Scanner scan=new Scanner(System.in);
		A=scan.nextInt();
		B=scan.nextInt();
		C=scan.nextInt();
		System.out.println(devide(A,B));
	}
	
	static long devide(int a,int b) {
		if(b==1) {
			return a%C;
		}
		
		if(b%2==0) {
			long num=devide(a,b/2);
			return num*num%C;
		}
		else {
			return devide(a,b-1)*a%C;
		}		
	}
	
}

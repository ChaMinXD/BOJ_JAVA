package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class BOJ_2443 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		for(int i=0;i<2*n-1;i++) {
			for(int k=0;k<i;k++)
				System.out.print(" ");
			for(int j=0;j<2*n-(2*i+1);j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
}

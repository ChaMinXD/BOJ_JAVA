package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_1226 {
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int ans=0;
			int[][] map=new int[16][16];
			for (int i = 0; i < 16; i++) {
		          String line = sc.next();
		          for (int j = 0; j < 16; j++) {
		              char digit = line.charAt(j);
		              map[i][j] = Character.getNumericValue(digit);
		              System.out.print(map[i][j]);
		          }
		          System.out.println();
		      }
			 
			
			System.out.printf("#%d %d",T,ans);
			System.out.println();
		}
	}
	static void BFS() {
		Queue<ArrayList<Integer>> q=new LinkedList<>();
	}
}


package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_19003 {
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int ans=0;
			int N=sc.nextInt();
			int M=sc.nextInt();
			String[] list=new String[N];
			boolean[] ispel=new boolean[N];
			boolean check=false;
			for(int i=0;i<N;i++) {
				list[i]=sc.next();
				ispel[i]=checkPel(list[i]);
				if(ispel[i])
					check=true;
			}
			for(int i=0;i<N-1;i++) {
				if(ispel[i]) continue;
				StringBuffer sb=new StringBuffer(list[i]);
				String reverse=sb.reverse().toString();
				for(int j=i+1;j<N;j++) {
					if(ispel[j]) continue;
					if(reverse.equals(list[j]))
						ans+=M*2;
				}
			}
			if(check)
				ans+=M;
			 
			
			System.out.printf("#%d %d",test_case,ans);
			System.out.println();
		}
	}
	static boolean checkPel(String a) {	
		if(a.length()==1)
			return true;
		else if(a.length()%2==1) { // 홀수자리
			int center=(a.length()-1)/2;
			String forward=a.substring(0,center);
			String b=a.substring(center+1);
			StringBuffer sb=new StringBuffer(b);
			String back=sb.reverse().toString();
			if(forward.equals(back)) {
				return true;
			}
			else
				return false;
		}
		else {
			int center=a.length()/2;
			String forward=a.substring(0,center);
			String b=a.substring(center);
			StringBuffer sb=new StringBuffer(b);
			String back=sb.reverse().toString();
			if(forward.equals(back)) {
				return true;
			}
			else
				return false;
		}
	}
	
}

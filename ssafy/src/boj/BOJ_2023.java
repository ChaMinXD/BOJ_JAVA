package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2023 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		dfs(2,1);
		dfs(3,1);
		dfs(5,1);
		dfs(7,1);
	}
	
	static void dfs(int num,int cnt) {
		if(cnt==N) {
			if(isP(num))
				System.out.println(num);
			return;
		}
		for(int i=1;i<10;i++) {
			int next=num*10+i;
			if(isP(next)) dfs(next,cnt+1);
		}
	}
	static boolean isP(int n) {
		if(n<2) return false;
		for(int i=2;i<=Math.sqrt(n);i++) {
			if(n%i==0) return false;	
		}
		return true;
	}
	
}

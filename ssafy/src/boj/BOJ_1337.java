package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1337 {
	static int N;
	static int[] list;
	static int[] seq;
	static int max=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new int[N+1];
		seq=new int[N+1];
	
		for(int i=1;i<N+1;i++) {
			list[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(list);
		list[0]=-99;
		seq[0]=1;
		for(int i=1;i<N+1;i++) {
			if(list[i-1]+1==list[i]) {
				seq[i]=seq[i-1]+1;
			}
	
			else {
				seq[i]=1;
				int a=seq[i]-seq[i-1];
			}
			max=Math.max(max,seq[i]);
		}
		System.out.println(5-max);
	}

}

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2961 {
	static int N;
	static int[][] list;
	static ArrayList<ArrayList<Integer>> aa;
	static int min=Integer.MAX_VALUE/10;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new int[N][2];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			list[i][0]=Integer.parseInt(st.nextToken()); 
			list[i][1]=Integer.parseInt(st.nextToken());
		}
		aa=new ArrayList<>();
		sub();

		for(int i=0;i<aa.size();i++) {
			int s=1;
			int b=0;
			for(int j=0;j<aa.get(i).size();j++) {
				s=s*list[aa.get(i).get(j)][0];
				b+=list[aa.get(i).get(j)][1];
			}
			if(min>Math.abs(s-b))
				min=Math.abs(s-b);
		}
		System.out.println(min);
	}

	private static void sub() {

		for(int j=1;j<(1<<N);j++) {
			ArrayList<Integer> a=new ArrayList();
			for(int i=0;i<N;i++) {
				if((j&(1<<i))!=0)
					a.add(i);
			}
			aa.add(a);
		}
	}
}

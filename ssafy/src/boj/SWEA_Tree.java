package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_Tree {
	static int N;
	static int[] list;
	static int T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int i=1;i<T+1;i++) {
			N=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer(br.readLine());
			list=new int[N];
			int max=-1;
			for(int j=0;j<N;j++) {
				list[j]=Integer.parseInt(st.nextToken());
				max=Math.max(max, list[j]);
			}
			int sum=0;
			int extra=0;
			for(int j=0;j<N;j++) {
				int sub=max-list[j];
				if(extra!=0&&sub%3!=0) {
					extra--;
					sub--;
				}
				sum+=sub/3*2;				
				if(sub%3==0) continue;
				else if(sub%3==1) sum+=1;
				else {
					sum+=2;
					extra++;
				}
				
			}
			System.out.println("#"+i+" : "+sum);
			
			
		}
	}

}

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253 {
	static int N;
	static int[] list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int cnt=0;
		list=new int[N];
		for(int i=0;i<N;i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list);
		if(N<3)
			System.out.println("0");
		else {
			for(int i=0;i<N;i++) {
				int s=0;
				int e=N-1;
				while(s<e) {
					if(s==i) 
						s++;
					if(e==i) 
						e--;				
					if(s==e)
						break;
					
					if(list[s]+list[e]==list[i]) {
						cnt++;
						break;
					}else if(list[s]+list[e]<list[i])
						s++;
					else
						e--;
				}
			}
			System.out.println(cnt);
		}
		
		
		
	}
}

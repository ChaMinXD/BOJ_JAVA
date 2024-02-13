package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1654 {
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int N=scan.nextInt();
		int K=scan.nextInt();
		int []list=new  int[N];
		
		for(int i=0;i<N;i++) {
			list[i]=scan.nextInt();
		}
		Arrays.sort(list);
		long min=1;
		long max=list[N-1];
		int cnt=0;
		long ans=0;
		
		while(min<=max) {
			long mid=(min+max)/2;
			cnt=0;
			for(int i=0;i<N;i++) {
				cnt+=list[i]/mid;
			}
			if(cnt>=K) {
				min=mid+1;
			}
			else {
				max=mid-1;
			}
			ans=max;
		}
	    
		System.out.println(ans);
	}
	
}

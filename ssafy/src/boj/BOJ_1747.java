package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1747 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		int N=scan.nextInt();
		int num=1100001;
		int ans=1003001;
		boolean []list=new boolean[num];
		list[0]=list[1]=true; // false - 소수 , true 소수아님
		for(int i=2;i*i<=num-1;i++) {
			  if(!list[i]){
	            	for(int j=i*i; j<=num-1; j+=i) list[j] = true;                
	            }   
		}
		
		for(int i=0;i<num;i++) {
			if(list[i])
				continue;
			else {
				list[i]=!checkPel(i);
			}
		}
		
		for(int i=N;i<num;i++) {
			if(!list[i]) {
				ans=i;
				break;
			}
			
		}
		System.out.println(ans);
		  
	}
	
	static boolean checkPel(int a) {
		String num=Integer.toString(a);
		
		if(num.length()==1)
			return true;
		else if(num.length()%2==1) { // 홀수자리
			int center=(num.length()-1)/2;
			String forward=num.substring(0,center);
			String b=num.substring(center+1);
			StringBuffer sb=new StringBuffer(b);
			String back=sb.reverse().toString();
			if(forward.equals(back)) {
				return true;
			}
			else
				return false;
		}
		else {
			int center=num.length()/2;
			String forward=num.substring(0,center);
			String b=num.substring(center);
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

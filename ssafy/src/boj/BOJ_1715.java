package boj;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1715 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		int N=scan.nextInt();
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			pq.add(scan.nextInt());
		}

		int sum=0;
		while(pq.size()>0) {
			int a=pq.poll();
			if(pq.peek()==null) {
				
				break;
			}
			else {
				int b=pq.poll();
				sum+=(a+b);
				
				pq.add(a+b);
			}
		}
		System.out.println(sum);

	}

}

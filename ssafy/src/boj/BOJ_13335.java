package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_13335 {
	public static void main(String [] args) {
		int n,w,l;
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		w=scan.nextInt();
		l=scan.nextInt();
		Queue<Integer> list = new LinkedList<> ();

		for(int i=0;i<n;i++) {
			list.add(scan.nextInt());
		}
		int sum=0;
		int now=0;
		Queue<Integer> queue = new LinkedList<> ();
		for(int j=0;j<w;j++) {
			queue.add(0);
		}
		while(queue.size()>0) {
			now-=queue.element();
			queue.poll();
			sum++;
			if(!list.isEmpty()) {
				if(now+list.element()>l) {
					queue.add(0);
				}
				else {
					queue.add(list.element());
					now+=list.element();
					list.poll();
				}
				
			}
			
		}
		
		System.out.println(sum);
	}
	

}

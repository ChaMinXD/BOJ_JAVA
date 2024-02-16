package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000 {
	static int N;
	static lec[] list;
	static int[] room;
	static class lec implements Comparable<lec>{
		int s;
		int e;
		lec (int s,int e){
			this.s=s;
			this.e=e;
		}
		@Override
		public int compareTo(lec o) {
			// TODO Auto-generated method stub
			if(this.s!=o.s)
				return this.s-o.s;
			else {
				return this.e-o.e;
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new lec[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			list[i]=new lec(s,e);
		}
		Arrays.sort(list);
		PriorityQueue<Integer> q=new PriorityQueue();
		q.add(list[0].e);
		for(int i=1;i<N;i++) {
			if(list[i].s>=q.peek()) {
				q.poll();
			}
			q.add(list[i].e);
		}
		System.out.println(q.size());
		
	}

}

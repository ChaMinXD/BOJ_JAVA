package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903 {
	static int n; // 카드 개수
	static int m; // 합체 수
	static PriorityQueue<Long> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		list=new PriorityQueue();
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			list.add(Long.parseLong(st.nextToken()));
		}
		
		for(int i=0;i<m;i++) {
			Long min=list.poll();
			Long min2=list.poll();
			Long sum=min+min2;
			list.add(sum);
			list.add(sum);
		}
		Long ans=(long) 0;
		while(!list.isEmpty())
			ans+=list.poll();
		System.out.println(ans);

		
	}

}

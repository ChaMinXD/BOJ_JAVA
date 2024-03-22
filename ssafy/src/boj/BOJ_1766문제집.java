package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766문제집 {
	static int N,M;
	static ArrayList<Integer>[] list;
	static int[] degree;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list=new ArrayList[N];
		degree=new int[N];
		for(int i=0;i<N;i++) {
			list[i]=new ArrayList();
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			list[s].add(e);
			degree[e]++;
		}
		Topology();
		
		bw.write(sb.toString());
		bw.flush();
	}
	public static void Topology( ) {
		PriorityQueue<Integer> q=new PriorityQueue();
		for(int i=0;i<N;i++) {
			if(degree[i]==0) {
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int now=q.poll();
			sb.append(now+1+" ");
			for(int i=0;i<list[now].size();i++) {
				degree[list[now].get(i)]--;
				if(degree[list[now].get(i)]==0)
					q.add(list[now].get(i));
			}
		}
		
	}

}

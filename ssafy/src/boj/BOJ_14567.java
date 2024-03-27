package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14567 {
	static int N,M;
	static int[] ans;
	static int[] degree;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list=new ArrayList[N];
		for(int i=0;i<N;i++) {
			list[i]=new ArrayList();
		}
		ans=new int[N];
		degree=new int[N];
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			list[a].add(b);
			degree[b]++;

		}
		Topology();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<N;i++) {
			sb.append(ans[i]).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	static void Topology() {
		PriorityQueue<Integer> q=new PriorityQueue();
		for(int i=0;i<N;i++) {
			if(degree[i]==0) {
				q.add(i);
				ans[i]=1;
			}
		}
		while(!q.isEmpty()) {
			int now=q.poll();
		
			for(int i=0;i<list[now].size();i++) {
				int n=list[now].get(i);
				degree[n]--;
				ans[n]=Math.max(ans[n], ans[now]+1);
				if(degree[list[now].get(i)]==0) {
					q.add(list[now].get(i));
					
				}
			}
		}
	}

}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1516게임개발 {
	static int N;
	static ArrayList<Integer>[] list;
	static int[] degree;
	static int[] ans;
	static int[] val;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		list=new ArrayList[N];
		degree=new int[N];
		ans=new int[N];
		val=new int[N];

		for(int i=0;i<N;i++) {
			list[i]=new ArrayList();
		}
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			val[i]=Integer.parseInt(st.nextToken());
			while(true) {
				int f=Integer.parseInt(st.nextToken());
				if(f==-1) break;
				else {
					degree[i]++;
					list[f-1].add(i);
				}
			}
		}
		topology();
		bw.write(sb.toString());
		bw.flush();
	
	}
	static void topology() {
		PriorityQueue<Integer> q=new PriorityQueue();
		for(int i=0;i<N;i++) {
			if(degree[i]==0)
				q.add(i);
		}
		while(!q.isEmpty()) {
			int now=q.poll();
			for(int i=0;i<list[now].size();i++) {
				int nex=list[now].get(i);
				degree[nex]--;
				ans[nex]=Math.max(ans[nex], ans[now]+val[now]);
				
				if(degree[list[now].get(i)]==0) {
					q.add(list[now].get(i));
				}
			}
		}
		for(int i=0;i<N;i++) {
			sb.append(ans[i]+val[i]).append("\n");
		}
		
		
	}
}
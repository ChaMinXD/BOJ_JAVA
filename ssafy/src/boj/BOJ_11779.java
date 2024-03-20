package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11779 {
	static int N,M,S,E;
	static class Node implements Comparable<Node>{
		int e;
		int v;
		Node(int e,int v){
			this.e=e;
			this.v=v;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.v-o.v;
		}
	}
	static ArrayList<Node>[] list;
	static int[] dis;
	static int max=Integer.MAX_VALUE/10;
	static int[] ans;
	static ArrayList<Integer>[] ans_list; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		list=new ArrayList[N];
		dis=new int[N];
		ans=new int[N];
		ans_list=new ArrayList[N];
		for(int i=0;i<N;i++) {
			ans_list[i]=new ArrayList();
		}
		Arrays.fill(dis,max );
		for(int i=0;i<N;i++) {
			list[i]=new ArrayList();
		}
		StringTokenizer st;
		for(int i=0;i<M;i++) {
			 st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			int v=Integer.parseInt(st.nextToken());
			list[s].add(new Node(e,v));
		}
		st=new StringTokenizer(br.readLine());
		S=Integer.parseInt(st.nextToken())-1;
		E=Integer.parseInt(st.nextToken())-1;
		dijkstra();
		StringBuilder sb=new StringBuilder();
		sb.append(dis[E]).append("\n").append(ans[E]).append("\n");
		for(int i=0;i<ans_list[E].size();i++) {
			sb.append(ans_list[E].get(i)).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
		
	}
	static void dijkstra() {
		PriorityQueue<Node> pq=new PriorityQueue();
		dis[S]=0;
		boolean[] visited=new boolean[N];
		pq.add(new Node(S,0));
		ans[S]++;
		ans_list[S].add(S+1);
		while(!pq.isEmpty()) {
			Node n=pq.poll();
			if(visited[n.e]) continue;
			visited[n.e]=true;
			for(int i=0;i<list[n.e].size();i++) {
				Node now= list[n.e].get(i);
				if(now.v+dis[n.e]<dis[now.e]) {
					ans[now.e]=ans[n.e]+1;
					ans_list[now.e]=new ArrayList(ans_list[n.e]);
					ans_list[now.e].add(now.e+1);
					dis[now.e]=now.v+dis[n.e];
					pq.add(new Node(now.e,dis[now.e]));
					
					
				}
			}
		}
	}
}

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14938 {
	static int n,m,r;
	static class Node implements Comparable<Node>{
		int e;
		int v;
		Node(int e,int v){
			this.e=e;
			this.v=v;
		}
		@Override
		public int compareTo(Node o) {
			return this.v-o.v;
		}
	}
	static ArrayList<Node>[] map;
	static int[] list;
	static int[] dis;
	static int ans=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		map=new ArrayList[n];
		list=new int[n];
		dis=new int[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			map[i]=new ArrayList();
			list[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<r;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			int v=Integer.parseInt(st.nextToken());
			map[s].add(new Node(e,v));
			map[e].add(new Node(s,v));
		}
		for(int i=0;i<n;i++) {
			Arrays.fill(dis, Integer.MAX_VALUE/10);
			dis[i]=0;
			dijk(i);
			int sum=0;
			
			for(int j=0;j<n;j++) {
				if(dis[j]<=m)
					sum+=list[j];
			}
			ans=Math.max(ans, sum);
		}
		System.out.println(ans);
		
	}
	static void dijk(int s) {
		PriorityQueue<Node> q=new PriorityQueue();
		q.add(new Node(s,0));
		boolean[] visited=new boolean[n];
		while(!q.isEmpty()) {
			Node now=q.poll();
			if(visited[now.e]) continue;
			visited[now.e]=true;
			for(Node e:map[now.e]) {
				int cost=dis[now.e]+e.v;
				if(dis[e.e]>cost) {
					dis[e.e]=cost;
					q.add(new Node(e.e,cost));
				}
			}
		}
		
	}

}

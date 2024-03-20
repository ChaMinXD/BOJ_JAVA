package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14284_dijk {
	static int N,M;
	static int s,t;
	static ArrayList<Node>[] map;
	static int[] dis;
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
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new ArrayList[N];
		for(int i=0;i<N;i++) {
			map[i]=new ArrayList();
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			int v=Integer.parseInt(st.nextToken());
			map[s].add(new Node(e,v));
			map[e].add(new Node(s,v));
		}
		st=new StringTokenizer(br.readLine());
		s=Integer.parseInt(st.nextToken())-1;
		t=Integer.parseInt(st.nextToken())-1;
		dis=new int[N];
		Arrays.fill(dis, Integer.MAX_VALUE/10);
		dis[s]=0;
		dijkstra();
		
		System.out.println(dis[t]);
		
		
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq=new PriorityQueue();
		boolean[] visited=new boolean[N];
		pq.add(new Node(s,0));
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			int start=now.e;
			if(visited[start]) continue;
			visited[start]=true;
			for(Node n:map[start]) {
				int cost=dis[start]+n.v;
				if(cost<dis[n.e]) {
					dis[n.e]=cost;
					pq.add(new Node(n.e,cost));
				}
			}
		
		}
	}
}

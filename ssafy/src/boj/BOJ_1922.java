package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1922 {
	static int N,M;
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
	static boolean[] visited;
	static ArrayList<Node>[] list;
	static int ans=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		list=new ArrayList[N];
		visited=new boolean[N];
		for(int i=0;i<N;i++) {
			list[i]=new ArrayList();
		}
		for(int i=0;i<M;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
		}
		Prim();
		System.out.println(ans);
	}
	static void Prim() {
		PriorityQueue<Node> pq=new PriorityQueue();
		pq.add(new Node(0,0));
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			if(visited[now.e]) continue;
			visited[now.e]=true;
			ans+=now.v;
			if(check())
				return;
			for(int i=0;i<list[now.e].size();i++) {
				pq.add(list[now.e].get(i));
				
			}
		}
	}
	static boolean check() {
		for(int i=0;i<N;i++) {
			if(!visited[i]) return false;
		}
		return true;
	}

}

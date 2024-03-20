package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1240 {
	static int N,M;
	static class Node{
		int e;
		int v;
		Node(int e,int v){
			this.e=e;
			this.v=v;
		}
	}
	static int min=Integer.MAX_VALUE/10;
	static ArrayList<Node>[] list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list=new ArrayList[N];
		for(int i=0;i<N;i++) {
			list[i]=new ArrayList();
		}
		for(int i=0;i<N-1;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			int v=Integer.parseInt(st.nextToken());
			list[s].add(new Node(e,v));
			list[e].add(new Node(s,v));
		}
		for(int i=0;i<M;i++) {
			visited=new boolean[N];

			min=Integer.MAX_VALUE/10;
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			DFS(s,e,0);
			System.out.println(min);
		}

	}
	static void DFS(int s,int e,int sum) {
		if(visited[s]) return;
		visited[s]=true;
		if(s==e) {
			min=Math.min(min, sum);
			return;
		}
		for(int i=0;i<list[s].size();i++) {
			int next=list[s].get(i).e;
			int val=list[s].get(i).v;
			if(visited[next]) continue;
			DFS(next,e,sum+val);
		}
	}

}

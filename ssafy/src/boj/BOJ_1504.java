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

public class BOJ_1504 {
	static int N;
	static int E;
	static ArrayList<Node>[] map;
	static int v1;
	static int v2;
	static int max=Integer.MAX_VALUE/10;
	static int ans[][];
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
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		map=new ArrayList[N];
		for(int i=0;i<N;i++) {
			map[i]=new ArrayList();
		}
		
		for(int i=0;i<E;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			int v=Integer.parseInt(st.nextToken());
			map[s].add(new Node(e,v));
			map[e].add(new Node(s,v));			
		}
		st=new StringTokenizer(br.readLine());
		v1=Integer.parseInt(st.nextToken())-1;
		v2=Integer.parseInt(st.nextToken())-1;
		ans=new int[3][N];
		for(int i=0;i<3;i++) {
			Arrays.fill(ans[i],max);
		}
		dijkstra(0,0);
		dijkstra(v1,1);
		dijkstra(v2,2);
		int answer=0;
		
		if(ans[0][v1]==max||ans[1][v2]==max||ans[2][N-1]==max)
			System.out.println("-1");
		else {
			int com1=0;
			int com2=0;
			com1+=ans[0][v1];
			com1+=ans[1][v2];
			com1+=ans[2][N-1];
			com2+=ans[0][v2];
			com2+=ans[2][v1];
			com2+=ans[1][N-1];
			answer=Math.min(com1, com2);
			System.out.println(answer);
		}
		

		
	}
	static void dijkstra(int a,int cnt) {
		PriorityQueue<Node> q =new PriorityQueue();
		boolean[] visited=new boolean[N];
		q.add(new Node(a,0));
		ans[cnt][a]=0;
		while(!q.isEmpty()) {
			Node now=q.poll();
			int n_end=now.e;
			if(visited[n_end]) continue;
			visited[n_end]=true;
			for(Node n:map[n_end]) {
				if(ans[cnt][n.e]>ans[cnt][n_end]+n.v) {
					ans[cnt][n.e]=ans[cnt][n_end]+n.v;
					q.add(new Node(n.e,ans[cnt][n.e]));
				}
			}
		}
	}

}

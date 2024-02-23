package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197 {
	static int N,M;
	static Node[] list;
	static int sum=0;
	static class Node implements Comparable<Node>{
		int s;
		int e;
		int v;
		Node(int s,int e,int v){
			this.s=s;
			this.e=e;
			this.v=v;
		}
		@Override
		public int compareTo(Node o) {
			return this.v-o.v;
		}
		
	}
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list=new Node[M];
		parent=new int[N];
		
		for(int i=0;i<N;i++) {
			parent[i]=i;
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			int v=Integer.parseInt(st.nextToken());
			list[i]=new Node(s,e,v);
		}
		Arrays.sort(list);
		kruskal();
		System.out.println(sum);
	}
	static void kruskal() {
		for(int i=0;i<M;i++) {
			if(find(list[i].s)!=find(list[i].e)) {
				sum+=list[i].v;
				union(list[i].s,list[i].e);

			}
		}
	}
	static void union(int x,int y) {
		x=find(x);
		y=find(y);
		if(x<y) parent[y]=x;
		else parent[x]=y;
	}
	static int find(int x) {
		if(parent[x]==x) return x;
		else return find(parent[x]);
	}
	

}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15681 {
	static int N,R,Q;
	static ArrayList<Integer>[] list;
	static ArrayList<Integer>[] tree;
	static int[] parent,size;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken())-1;
		Q=Integer.parseInt(st.nextToken());
		list=new ArrayList[N];
		tree=new ArrayList[N];
		parent=new int[N];
		size=new int[N];
		parent[R]=-1;
		for(int i=0;i<N;i++) {
			list[i]=new ArrayList();
			tree[i]=new ArrayList();
		}
		for(int i=0;i<N-1;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			list[s].add(e);
			list[e].add(s);
		}
		makeTree(R,-1);
		countSubTree(R);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<Q;i++) {
			int a=Integer.parseInt(br.readLine())-1;
			sb.append(size[a]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	static void makeTree(int current,int p) {
		for(int Node : list[current]) {
			if(Node!=p) {
				tree[current].add(Node);
				parent[Node]=current;
				makeTree(Node,current);
			}
		}
	}
	static void countSubTree(int current) {
		size[current]=1;
		for(int Node :tree[current]) {
			countSubTree(Node);
			size[current]+=size[Node];
		}
	}
	

}


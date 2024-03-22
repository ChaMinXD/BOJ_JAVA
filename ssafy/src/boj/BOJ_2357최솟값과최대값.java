package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2357최솟값과최대값 {
	static int N,M;
	static int[] list;
	static Node[] tree;
	static class Node{
		int min;
		int max;
		Node(int min,int max){
			this.min=min;
			this.max=max;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list=new int[N+1];
		tree=new Node[4*N+1];
		for(int i=1;i<N+1;i++) {
			list[i]=Integer.parseInt(br.readLine());
		}
		makeTree(1,1,N);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			Node now=findTree(1,N,1,a,b);
			sb.append(now.min).append(" ").append(now.max).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		
	}
	static Node makeTree(int idx,int s,int e) {
		if(s==e) {
			tree[idx]=new Node(list[e],list[e]);
		}
		else {
			int mid=(s+e)/2;
			Node l=makeTree(idx*2,s,mid);
			Node r=makeTree(idx*2+1,mid+1,e);
			tree[idx]=new Node(Math.min(l.min, r.min),Math.max(l.max, r.max));
		}
		return tree[idx];
	}
	static Node findTree(int left,int right,int idx,int s,int e) {
		if(left>e||right<s) {
			return new Node(Integer.MAX_VALUE,Integer.MIN_VALUE);
		}
		if(s<=left&&right<=e) {
			return tree[idx];
		}
		int mid=(left+right)/2;
		Node l=findTree(left,mid,idx*2,s,e);
		Node r=findTree(mid+1,right,idx*2+1,s,e);
		return new Node(Math.min(l.min, r.min),Math.max(l.max, r.max));
	}
}

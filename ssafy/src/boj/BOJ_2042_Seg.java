package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042_Seg {
	static int N,M,K;
	static long[] list;
	static long[] tree;
	static int h;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		list=new long[N+1];
		tree=new long[4*N+1];
		h=(int) Math.ceil(Math.log(N)/Math.log(2));
		for(int i=1;i<N+1;i++) {
			list[i]=Long.parseLong(br.readLine());		
		}
		makeTree(1,1,N);
		for(int i=0;i<M+K;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());;
			long c=Long.parseLong(st.nextToken());;
			if(a==1) {
				updateTree(1,N,1,b,c);
				list[b]=c;
			}
			else {
				System.out.println(makeSum(1,N,1,b,c));
			}
		}
	}
	static long makeTree(int idx,int s,int e) {
		if(s==e) {
			tree[idx]=list[s];
		}
		else {
			int mid=(s+e)/2;
			tree[idx]=makeTree(idx*2,s,mid)+makeTree(idx*2+1,mid+1,e);
		}
		return tree[idx];
		
	}
	static long makeSum(int left,int right,int idx,int s,long e) {
		if(s>right||e<left)
			return 0;
		if(s<=left&&right<=e)
			return tree[idx];
		int mid=(left+right)/2;
		return makeSum(left,mid,idx*2,s,e)+makeSum(mid+1,right,idx*2+1,s,e);
		
	}
	static void updateTree(int left,int right,int idx, int n,long v) {
		if(n<left||n>right)
			return;
		tree[idx]=tree[idx]-list[n]+v;
		if(left==right)
			return;
		int mid=(left+right)/2;
		updateTree(left,mid,idx*2,n,v);
		updateTree(mid+1,right,idx*2+1,n,v);
		
	}
}


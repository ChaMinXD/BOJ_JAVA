package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12837 {
	static int N,Q;
	static int[] list;
	static long[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		list=new int[N+1];
		tree=new long[4*N+1];
		StringBuilder sb=new StringBuilder();

		for(int i=0;i<Q;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			if(a==1) {
					update(b,c,1,1,N);		
			}
			else {
					sb.append(getValue(b,c,1,1,N)).append("\n");
			}
		}
		System.out.println(sb.toString());
		
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
	static void update(int n,int val,int idx,int s,int e) {
		if(s<=n&&n<=e) {
			tree[idx]=tree[idx]+val;
			if(s==e) {
				return;
			}
			int mid=(s+e)/2;
			update(n,val,idx*2,s,mid);
			update(n,val,idx*2+1,mid+1,e);
		}
	
	}
	static long getValue(int l,int r,int idx,int s,int e) {
		if(e<l||s>r)
			return 0;
		if(l<=s&&e<=r) 
			return tree[idx];
		int mid=(s+e)/2;
		return getValue(l,r,idx*2,s,mid)+getValue(l,r,idx*2+1,mid+1,e);
		
	}

}

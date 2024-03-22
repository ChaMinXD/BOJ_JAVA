package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_16978수열과쿼리22 {
	static int N,M;
	static int[] list;
	static long[] tree;
	static class Q1{
		int idx;
		int v;
		Q1(int idx,int v){
			this.idx=idx;
			this.v=v;
		}
	}
	static class Q2 implements Comparable<Q2>{
		int cnt;
		int idx;
		int s;
		int e;
		Q2(int cnt,int idx,int s,int e){
			this.cnt=cnt;
			this.idx=idx;
			this.s=s;
			this.e=e;
		}
		@Override
		public int compareTo(Q2 o) {
			return this.idx-o.idx;
		}
	}
	static ArrayList<Q1> query1=new ArrayList();
	static ArrayList<Q2> query2=new ArrayList();
	static long[] ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		list=new int[N+1];
		tree=new long[4*N+1];
		for(int i=1;i<N+1;i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		M=Integer.parseInt(br.readLine());
		int cnt=0;
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int t=Integer.parseInt(st.nextToken());
			if(t==1) {
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				query1.add(new Q1(a,b));
			}
			else {
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				query2.add(new Q2(cnt,a,b,c));
				cnt++;
			}
		}
		ans=new long[cnt];
		Collections.sort(query2);
		makeTree(1,1,N);
		int now_idx=0;
		for(int i=0;i<query2.size();i++) {
			Q2 now=query2.get(i);
			if(now.idx!=now_idx) {
				now_idx=now.idx;
				Q1 qn=query1.get(now_idx-1);
				updateTree(qn.idx,qn.v,1,1,N);
			}
			ans[now.cnt]=makeSum(1,N,1,now.s,now.e);
		}
		for(int i=0;i<cnt;i++) {
			System.out.println(ans[i]);
		}
		
	}
	static long makeTree(int index,int s,int e) {
		if(s==e) {
			tree[index]=list[s];
		}
		else {
			int mid=(s+e)/2;
			tree[index]=makeTree(index*2,s,mid)+(makeTree(index*2+1,mid+1,e));
		}
		
		return tree[index];	
	}
	static long makeSum(int left,int right,int idx,int s,int e) { //
		if(left>e||right<s)
			return 0;
		if(s<=left&&right<=e)
			return tree[idx];
		int mid=(left+right)/2;
		return makeSum(left,mid,idx*2,s,e)+makeSum(mid+1,right,idx*2+1,s,e);

	}
	static void updateTree(int n,int v,int idx,int s,int e) {
		if(n<s||n>e)
			return;
		tree[idx]=tree[idx]-list[n]+v;
		if(s==e)
			return;
		int mid=(s+e)/2;
		updateTree(n,v,idx*2,s,mid);
		updateTree(n,v,idx*2+1,mid+1,e);
	}

}

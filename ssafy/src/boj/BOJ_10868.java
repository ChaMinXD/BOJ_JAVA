package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10868 {
	static int N,M;
	static int[] list;
	static int[] tree;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list=new int[N+1];
		tree=new int[4*N+1];
		for(int i=1;i<N+1;i++) {
			list[i]=Integer.parseInt(br.readLine());
		}
		makeTree(1,1,N);
//		for(int i=0;i<4*N;i++) {
//			System.out.print(tree[i]+" ");
//		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			sb.append(findMin(1,N,1,a,b)).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	static int makeTree(int idx,int s,int e) {
		if(s==e) {
			tree[idx]=list[s];
		}
		else {
			int mid=(s+e)/2;
			tree[idx]=Math.min(makeTree(idx*2,s,mid),makeTree(idx*2+1,mid+1,e));
		}
		return tree[idx];
	}
	static int findMin(int s,int e,int idx,int l,int r) { // l,r-> 고정범위 
		if(e<l||s>r)
			return Integer.MAX_VALUE;
		if(l<=s&&e<=r)
			return tree[idx];
		int mid=(s+e)/2;
		return Math.min(findMin(s,mid,idx*2,l,r),findMin(mid+1,e,idx*2+1,l,r));
		
	}

}

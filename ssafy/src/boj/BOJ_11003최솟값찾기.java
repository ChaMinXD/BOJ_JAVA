package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_11003최솟값찾기 {
	static int N,L;
	static int[] list;
	static class Node {
		int idx;
		int v;
		Node(int idx,int v){
			this.idx=idx;
			this.v=v;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		list=new int[N+1];
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<N+1;i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		StringBuilder sb=new StringBuilder();
		int start=1;
		int end=1;
		int[] ans=new int[N+1];
		Deque<Node> d=new ArrayDeque();
		ans[0]=list[1];
		while(end<N+1) {
			start=end-L+1;
			start=(start<=0)?1:start;	
			while(!d.isEmpty()&&d.peekFirst().idx<start) {
				d.pollFirst();
			}
			while(!d.isEmpty()&&d.peekLast().v>list[end]) {
				d.pollLast();
			}
			d.addLast(new Node(end,list[end]));
			ans[end]=d.peekFirst().v;
			sb.append(ans[end]).append(" ");
			end++;
		}
		
		bw.write(sb.toString());
		bw.flush();
	}


}

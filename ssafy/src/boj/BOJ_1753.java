package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
	static int V;
	static int E;
	static ArrayList<node>[] map;
	static int S;
	static int max=Integer.MAX_VALUE/10;
	static int[] ans;
	static class node implements Comparable<node>{
		int end;
		int val;
		node(int end,int val){
			this.end=end;
			this.val=val;
		}
		
		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return this.val-o.val;
		}
		
		
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		map=new ArrayList[V];
		for(int i=0;i<V;i++)
			map[i]=new ArrayList();
		ans=new int[V];
		Arrays.fill(ans, max);
		S=Integer.parseInt(br.readLine())-1;
		for(int i=0;i<E;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			int v=Integer.parseInt(st.nextToken());
			map[x].add(new node(y,v));
		}
		dijkstra(S);		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<V;i++) {
			if(S==i) sb.append("0").append("\n");
			else {
				if(ans[i]==max) {
					sb.append("INF").append("\n");
				}
				else {
					sb.append(ans[i]).append("\n");
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
		
		
	}
	  private static void dijkstra(int start){
	       PriorityQueue<node> queue = new PriorityQueue<>();
	       boolean[] check = new boolean[V];
	       queue.add(new node(start, 0));
	       ans[start] = 0;
	       while(!queue.isEmpty()){
	    	   node curNode = queue.poll();
	           int cur = curNode.end;

	           if(check[cur] == true) continue;
	           check[cur] = true;

	           for(node node : map[cur]){
	               if(ans[node.end] > ans[cur] + node.val){
	            	   ans[node.end] = ans[cur] + node.val;
	                   queue.add(new node(node.end, ans[node.end]));
	               }
	           }
	       }
	    }
}

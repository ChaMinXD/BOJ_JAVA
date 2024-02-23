package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647 {
	static class Load implements Comparable<Load>{
		int e;
		int v;
		Load(int e,int v){
			this.e=e;
			this.v=v;
		}
		@Override
		public int compareTo(Load o) {
			return this.v-o.v;
		}
		
	
	}
	static int N;
	static int M;
	static ArrayList<Load>[] list;
	static boolean[] visited;
	static int total=0;
	static int max=0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list=new ArrayList[N];
		visited=new boolean[N];
		for(int i=0;i<N;i++) {
			list[i]=new ArrayList();
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			int v=Integer.parseInt(st.nextToken());
			list[s].add(new Load(e,v));
			list[e].add(new Load(s,v));
		}
		Prim();

		System.out.println(total-max);
		
	}
	static void Prim() {
		PriorityQueue<Load> q=new PriorityQueue();
		q.add(new Load(0,0));
		int cnt=0;
		while(!q.isEmpty()) {
			Load now=q.poll();
			if(visited[now.e]) continue;
			visited[now.e]=true;
			total+=now.v;
			cnt++;
			max=Math.max(max, now.v);
			if(cnt==N) break;
			for(Load l:list[now.e]) {
				if(!visited[l.e])
					q.add(l);
			}
			
		}
	}
	

}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1967 {
	static int N;
	static ArrayList<Node>[] list;
	static boolean[] visited;
	static int start;
	static class Node implements Comparable<Node>{
		int child;
		int value;
		Node(int c,int v){
			child=c;
			value=v;
		}
		@Override
		public int compareTo(Node o) {
			return o.value-this.value;
		}
	}
	static int max_length=0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		list=new ArrayList[N];
		for(int i=0;i<N;i++) {
			list[i]=new ArrayList();
		}
		for(int i=0;i<N-1;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
			
		}
		for(int i=0;i<N;i++) {
			Collections.sort(list[i]);
		}

			visited=new boolean[N];
			start=0;
			dfs(start,0);
			visited=new boolean[N];
			dfs(start,0);

		
		System.out.println(max_length);
		
		
	}
	
	
	public static void dfs(int x, int len) {
        if(len > max_length) {
        	max_length = len;
            start = x;
        }
        visited[x] = true;
        
        for(int i = 0; i < list[x].size(); i++) {
            Node n = list[x].get(i);
            if(visited[n.child] == false) {
                dfs(n.child, n.value + len);
                visited[n.child] = true;
            }
        }
        
    }

}

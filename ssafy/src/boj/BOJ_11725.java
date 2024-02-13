package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11725 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=null;
		int N=Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> list=new ArrayList<>();
		for(int i=0;i<N+1;i++) {
			list.add(new ArrayList<Integer>());
		}
		boolean[] visited=new boolean[N];
		int [] ans=new int[N-1];
		Queue<Integer> q=new LinkedList<>();
		q.add(1);
		for(int i=0;i<N-1;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		while(!q.isEmpty()) {
			int now=q.poll();
			visited[now-1]=true;
			for(int i=0;i<list.get(now).size();i++) {
				if(visited[list.get(now).get(i)-1]) continue;
				q.add(list.get(now).get(i));
				ans[list.get(now).get(i)-2]=now;
			}
			
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<N-1;i++) {
			sb.append(Integer.toString(ans[i])).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

}

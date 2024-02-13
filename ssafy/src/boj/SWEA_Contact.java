package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_Contact {
	static int n;
	static int s;
	static boolean[][] map;
	static int[] visited;
	static Queue<Integer> q=new LinkedList<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		for(int T=1;T<11;T++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			map=new boolean[101][101];
			visited=new int[101];
			n=Integer.parseInt(st.nextToken());
			s=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n/2;i++) {
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				map[x][y]=true;
			}
			q.add(s);

			visited[s]=1;
			while(!q.isEmpty()) {
				int now=q.poll();
				
				for(int i=1;i<101;i++) {
					if(!map[now][i]) continue;
					if(visited[i]!=0) continue;
					q.add(i);
					visited[i]=visited[now]+1;	
					
				}
			}
			ArrayList<Integer> ans=new ArrayList();
			int m=0;
			for(int i=1;i<101;i++) {
				if(visited[i]>m)
					m=visited[i];
			}
			for(int i=1;i<101;i++) {
				if(visited[i]==m)
					ans.add(i);
			}
			System.out.println("#"+T+" "+Collections.max(ans));
			q.clear();
		}
		
	}

}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_7465 {
	static int T;
	static int N;
	static int M; 
	static boolean[][] map;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> ans=new ArrayList();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			map=new boolean[N][N];
			visited=new boolean[N];
			for(int i=0;i<M;i++) {
				st=new StringTokenizer(br.readLine());
				int s=Integer.parseInt(st.nextToken())-1;
				int e=Integer.parseInt(st.nextToken())-1;
				map[s][e]=true;
				map[e][s]=true;
			}
			
			for(int i=0;i<N-1;i++) {
				if(visited[i]) continue;
				visited[i]=true;
				ArrayList<Integer> a=new ArrayList();
				a.add(i);
				for(int j=i+1;j<N;j++) {
					if(visited[j]) continue;
					if(!map[i][j]) continue;
					visited[j]=true;
					a.add(j);
				}
				ans.add(a);
			}
			System.out.println(ans.toString());
			System.out.println(ans.size());
		}
		
	}
	static void dfs() {
		
	}

}

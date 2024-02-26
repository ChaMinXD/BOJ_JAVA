package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//14865
public class mAlgo1_서울_15반_차민혁 {
	static int N,M;
	static boolean[][] list;
	static boolean[] visited;
	static int cnt=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		list=new boolean[N][N];
		for(int i=0;i<M;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken())-1;
			int e=Integer.parseInt(st.nextToken())-1;
			list[s][e]=true;
			list[e][s]=true;
		}
		visited=new boolean[N];
		DFS(0);
		System.out.println(cnt);
	}
	static void DFS(int now) {
		visited[now]=true;
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			if(list[now][i]) {
				DFS(i);
				cnt++;
			}
		}
	}

}

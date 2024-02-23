package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15686 {
	static int N;
	static int M;
	static int[][] map;
	static boolean[] visited;
	static Queue<Pos> chicken=new LinkedList();
	static ArrayList<Pos> home=new ArrayList();
	static int com[];
	static int[][] length;
	static int c_cnt;
	static int min=Integer.MAX_VALUE/10;
	static ArrayList<Integer> ans=new ArrayList();

	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					chicken.add(new Pos(i,j));
				}
				if(map[i][j]==1) {
					home.add(new Pos(i,j));
				}
			}
		}
		c_cnt=chicken.size();
		visited=new boolean[c_cnt];
		com=new int[M];
		length=new int[c_cnt][home.size()];
		
		int cnt=0;
		while(!chicken.isEmpty()) {
			Pos start=chicken.poll();
			int min=Integer.MAX_VALUE/10;
			for(int i=0;i<home.size();i++) {
				Pos h=home.get(i);
				length[cnt][i]=Math.abs(h.x-start.x)+Math.abs(h.y-start.y);
			}
			cnt++;
		}
		DFS(0);
		Collections.sort(ans);
		System.out.println(ans.get(0));
	
		
		
		
	}
	static void DFS(int cnt) {
		if(cnt==M) {
			int sum=0;
			System.out.println(Arrays.toString(com));
			for(int i=0;i<home.size();i++) {
				int m=Integer.MAX_VALUE/10;
				for(int j=0;j<M;j++) {
		
					m=Math.min(m, length[com[j]][i]);
					
				}
				sum+=m;
			}
			ans.add(sum);
			return;
		}
		for(int i=0;i<c_cnt;i++) {
			if(cnt>0&&com[cnt-1]>=i) continue;
			com[cnt]=i;
			DFS(cnt+1);
		}
	}


}

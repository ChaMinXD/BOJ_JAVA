package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_15652 {
	static int N;
	static int M;
	static int[] list;
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		bw=new BufferedWriter(new OutputStreamWriter(System.out));
		String l=br.readLine();
		StringTokenizer st=new StringTokenizer(l);
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list=new int[M];
		
		int cnt=0;
		
		DFS(0);
		bw.flush();
		
	}
	private static void DFS(int c) throws IOException {
		if(c==M) {
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<list.length;i++) {
				sb.append(list[i]).append(" ");
				
			}
			sb.append("\n");
			bw.write(sb.toString());
			return ;
		}
		for(int i=0;i<N;i++) {
			if(c>0&&list[c-1]>i+1) continue;
			list[c]=i+1;
			DFS(c+1);
			list[c]=0;

		}
		
	}
	
}
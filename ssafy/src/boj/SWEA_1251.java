package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_1251 {
	static int T;
	static int N;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][2];
			for(int i=0;i<2;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[j][i]=Integer.parseInt(st.nextToken());
				}
			}
		}
		
		
	}

}

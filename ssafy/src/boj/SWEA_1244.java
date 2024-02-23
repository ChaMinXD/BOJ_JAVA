package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1244 {
	static int T;
	static String N;
	static int M;
	static int[] ans;
	static int[] sorted;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=st.nextToken();
			M=Integer.parseInt(st.nextToken());
			int L=N.length();
			ans=new int[L];
			sorted=new int[L];
			for(int i=0;i<L;i++) {
				ans[i]=N.charAt(i)-'0';
				sorted[i]=N.charAt(i)-'0';
			}
			Arrays.sort(sorted);
			for(int i=0;i<M;i++) {
				
			}
			

		}
		
	}
	

}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_20438 {
	static int N,K,Q,M;
	static boolean[] list;
	static int[] klist; 
	static ArrayList<Integer> qlist=new ArrayList(); 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		klist=new int[K];
		list=new boolean[N+3];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			klist[i]=Integer.parseInt(st.nextToken());
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<Q;i++) {
			qlist.add(Integer.parseInt(st.nextToken()));
		}
		for(int j=0;j<K;j++) {
			if(qlist.contains(klist[j])) {
				qlist.remove(Integer.valueOf(klist[j]));
			}
		}
		for(int j=0;j<qlist.size();j++) {
			int num=qlist.get(j);
			int i=1;
			while(num*i<N+3) {
				list[num*i]=true;
				i++;
			}
		}
		for(int i=0;i<K;i++) {
			list[klist[i]]=false;
		}
		
		//false 못받은거임
		int[] dp=new int[N+3];
		dp[3]=(list[3])? 0:1;
		for(int i=4;i<N+3;i++) {
			dp[i]=dp[i-1]+((list[i])?0:1);
		}

		
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			sb.append(dp[e]-dp[s-1]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();

	}

}

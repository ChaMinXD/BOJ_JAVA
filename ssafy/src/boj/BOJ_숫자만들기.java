package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_숫자만들기 {
	static int T,N;
	static int[] cal;
	static int[] list;
	static int[] combi;
	static int max;
	static int min;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int t=1;t<T+1;t++) {
			max=Integer.MIN_VALUE;
			min=Integer.MAX_VALUE;
			N=Integer.parseInt(br.readLine());
			cal=new int[4];
			list=new int[N];
			combi=new int[N-1];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {
				cal[i]=Integer.parseInt(st.nextToken());
			}
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				list[i]=Integer.parseInt(st.nextToken());
			}
			DFS(0);
			sb.append("#").append(t).append(" ").append(max-min).append("\n");				
		}
		System.out.println(sb.toString());
	}

	static void DFS(int cnt) {
		if(cnt==N-1) {
			int sum=list[0];
			for(int i=0;i<N-1;i++) {
				if(combi[i]==0) {
					sum+=list[i+1];
				}
				else if(combi[i]==1) {
					sum-=list[i+1];
				}else if(combi[i]==2) {
					sum=sum*list[i+1];
				}else {
					sum=sum/list[i+1];
				}
			}
			max=Math.max(max, sum);
			min=Math.min(min, sum);

			return;
		}
		for(int i=0;i<4;i++) {
			if(cal[i]>0) {
				cal[i]--;
				combi[cnt]=i;
				DFS(cnt+1);
				cal[i]++;
			}			
		}
	}
}

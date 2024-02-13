package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_6603 {
	static int k;
	static int[] S;
	static int[] ans;
	static boolean[] visited;
	static StringBuilder sb=new StringBuilder();

	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
 	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		

		while(true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			k=Integer.parseInt(st.nextToken());
			if(k==0)
				break;
			S=new int[k];
			ans=new int[6];
			visited=new boolean[k];
			for(int i=0;i<k;i++) {
				S[i]=Integer.parseInt(st.nextToken());
			}
			Arrays.sort(S);
			perm(0);
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length()-2);
		bw.write(sb.toString());
		bw.flush();
		
	}
	static void perm(int cnt) throws IOException{
		if(cnt==6) {
			for(int i=0;i<6;i++)
				sb.append(ans[i]).append(" ");
			sb.append("\n");
			return;
		}
		for(int i=0;i<k;i++) {
			if(visited[i]) continue;
			if(cnt>0&&ans[cnt-1]>S[i]) continue;
			visited[i]=true;
			ans[cnt]=S[i];
			perm(cnt+1);
			visited[i]=false;
		}
		
	}

}

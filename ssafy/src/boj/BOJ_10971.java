package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10971 {
	static int N;
	static int[][] W;
	static int min;
	static int[] nums;
	static boolean[] visited;
		public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		W=new int[N][N];
		nums=new int[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				W[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		min=Integer.MAX_VALUE/100;
		visited=new boolean[N];
		perm(0);
		System.out.println(min);
	}
		private static void perm(int cnt) {
			if(cnt==N) {
				int sum=0;
				
				for(int i=0;i<N-1;i++) {
					if(W[nums[i]][nums[i+1]]==0)
						return;	
					sum+=W[nums[i]][nums[i+1]];
				}
				if(W[nums[N-1]][nums[0]]==0)
					return;
				
				sum+=W[nums[N-1]][nums[0]];
				min=Math.min(min, sum);
				return;
			}
			for(int i=0;i<N;i++) {
				if(visited[i]) continue;
				visited[i]=true;
				nums[cnt]=i;
				perm(cnt+1);
				visited[i]=false;					
			}						
		}

}

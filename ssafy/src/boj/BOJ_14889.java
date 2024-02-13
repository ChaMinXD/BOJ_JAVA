package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14889 {
	static int N;
	static int[][] list;
	static ArrayList<Integer> t1;
	static ArrayList<Integer> t2;
	static boolean[] visited;
	static int min=Integer.MAX_VALUE/10;
	static int t_sum1;
	static int t_sum2;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new int[N][N];
		t1=new ArrayList<Integer>();
		t2=new ArrayList<Integer>();
		t_sum1=0;
		t_sum2=0;
		
		visited=new boolean[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				list[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		makeTeam(0);
		System.out.println(min);
	}
	private static void makeTeam(int cnt) {
		if(cnt==N/2){
			for(int i=0;i<N;i++) {
				t2.add(i);
			}
			for(int i=0;i<N/2;i++) {
				t2.remove(t1.get(i));
			}

	
			for(int i=0;i<N/2;i++) {
				for(int j=0;j<N/2;j++) {
					if(i==j) continue;
					t_sum1+=list[t1.get(i)][t1.get(j)];
					t_sum2+=list[t2.get(i)][t2.get(j)];
				}
			}
			t2.clear();
			
			min=Math.min(min, Math.abs(t_sum1-t_sum2));
			t_sum1=0;
			t_sum2=0;
			
		}
		else {
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			if(t1.size()>0) {
				if(t1.get(t1.size()-1)>=i) continue;
				visited[i]=true;
				t1.add(i);
				makeTeam(cnt+1);
				t1.remove(Integer.valueOf(i));
				visited[i]=false;
			}
			else {
				visited[i]=true;
				t1.add(i);
				makeTeam(cnt+1);
				t1.remove(Integer.valueOf(i));
				visited[i]=false;
			}
				
			
		}
		}
	}
	

}



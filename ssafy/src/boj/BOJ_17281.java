package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_17281 {
	static int N;
	static ArrayList<ArrayList<Integer>> list;
	static int[] seq;
	static boolean[] visited;
	static int ans=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new ArrayList<>();
		seq=new int[9];
		visited=new boolean[9];
		visited[0]=true;
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			ArrayList<Integer> a=new ArrayList();
			for(int j=0;j<9;j++) {
				a.add(Integer.parseInt(st.nextToken()));
			}
			list.add(a);
		}
	perm(0);	
	System.out.println(ans);
		
		
	}


	private static void perm(int cnt) {
		if(cnt==9) {
			cal();
		}
		if(cnt==3) {
			seq[cnt]=0;
			perm(cnt+1);
		}
		
		for(int i=1;i<9;i++) {
			if(visited[i]) continue;
			visited[i]=true;
			seq[cnt]=i;
			perm(cnt+1);
			visited[i]=false;
			
		}
	}
 

	private static void cal() {
		int start=0;
		int score=0;
		for(int i=0;i<N;i++) {
			int out=0;
			int[] lu=new int[4];
			while(out<3){
				switch (list.get(i).get(seq[start])) {
				case 1: {					
					for(int k=3;k>0;k--) {
						if(lu[k]==0) continue;
						int next=k+1;
						if(next>3) {
							score++;
							lu[k]=0;
						}
						else {
							lu[next]=lu[k];
							lu[k]=0;
						}
					}
					lu[1]=1;
					break;
				}
				case 2:{
					for(int k=3;k>0;k--) {
						if(lu[k]==0) continue;
						int next=k+2;
						if(next>3) {
							score++;
							lu[k]=0;
						}
						else {
							lu[next]=lu[k];
							lu[k]=0;
						}
					}
					lu[2]=1;
					break;
				}
				case 3:{
					for(int k=3;k>0;k--) {
						if(lu[k]==0) continue;
						int next=k+3;
						if(next>3) {
							score++;
							lu[k]=0;
						}
						else {
							lu[next]=lu[k];
							lu[k]=0;
						}
					}
					lu[3]=1;
					break;
				}
				case 4:{
					for(int k=1;k<4;k++) {
						if(lu[k]==0) continue;
						score++;
						lu[k]=0;
					}
					score++;
					break;
				}
				case 0:{
					out++;
					break;
				}
				
			}
			start=(start+1)%9;
			
			}
		}
		if(ans<score)
			ans=score;
	}
	  
}


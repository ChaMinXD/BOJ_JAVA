package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20055 {
	static int N,K;
	static int[]  list;
	static int[] robot;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine()); 
		list=new int[N*2];
		robot=new int[N*2];
		for(int i=0;i<N*2;i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		int seq=0;
		while(true) {
			seq++;
			for(int i=0;i<3;i++) {
				work(i);
			}
			if(!check())break;
			
				
		}
		System.out.println(seq);
		
	}
	private static void work(int seq) {
		
		switch (seq) {
		case 0: {
			int list_temp=list[2*N-1];
	
			for(int i=N*2-1;i>0;i--) {
				list[i]=list[i-1];
				robot[i]=robot[i-1];
			}
			for(int i=N;i<2*N;i++) {
				robot[i]=0;
			}
			list[0]=list_temp;
			robot[0]=0;
			if(robot[N-1]==1)
				robot[N-1]=0;
			
			break;
		}
			
		case 1: {
			for(int i=N-2;i>=0;i--) {
				if(robot[i]==0) continue;
				if(robot[i+1]==1) continue;
				if(list[i+1]==0) continue;
				robot[i+1]=1;
				robot[i]=0;
				list[i+1]--;
			}
			if(robot[N-1]==1)
				robot[N-1]=0;

			break;
		}case 2: {
			if(list[0]!=0) {
				robot[0]=1;
				list[0]--;
			}

			break;
			
		}
		}
		
		
	}
	private static boolean check() {
		int cnt=0;
		for(int i=0;i<N*2;i++) {
			if(list[i]==0)
				cnt++;
		}
		if(cnt>=K) {
			return false;
		}
		return true;
	}
	
	
	
}

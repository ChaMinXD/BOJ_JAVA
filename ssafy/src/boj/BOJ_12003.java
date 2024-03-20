package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_12003 {
	static int N;
	static int[] list;
	static int[] leng;
	public static void main(String args[]) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		list=new int[N];
		leng=new int[N];
		for(int i=0;i<N;i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		System.out.println(LIS()+1);
		
		
	}
	static int LIS() {
		int end=0;
		leng[end]=list[end];
		for(int i=1;i<N;i++) {
			if(leng[end]<list[i]) {
				leng[++end]=list[i];
			}
			else {
				int left=0;
				int right=end;
				while(left<right) {
					int mid=(left+right)/2;
					if(leng[mid]<list[i])
						left=mid+1;
					else
						right=mid;
				}
				leng[right]=list[i];
			}
		}
		return end;
	}

}

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2352_LIS {
	static int N;
	static int[] list;
	static int[] length;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new int[N];
		length=new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		System.out.println(BinaryLIS()+1);
	

	}
	
	static int BinaryLIS() {
		int end=0;
		length[end]=list[0];
		for(int i=1;i<N;i++) {
			if(length[end]<list[i]) {
				length[end+1]=list[i];
				end++;
			}
			else {
				int left=0;
				int right=end;
				while(left<right) {
					int mid=(right+left)/2;
					   if (length[mid] < list[i]) {
						   left=mid+1;
	                    }
					   else {
						   right=mid;
					   }
				}
				length[right]=list[i];
			}
			
		}
		
		return end;
	}

}

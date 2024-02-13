package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ_25916 {
	public static void main(String args[]) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String [] input=br.readLine().split(" ");
		int N=Integer.parseInt(input[0]);
		int M=Integer.parseInt(input[1]);
		int []list=new int[N];
		String []line=br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			list[i]=Integer.parseInt(line[i]);
		}
		int start=0;
		int end=0;
		int sum=0;
		ArrayList<Integer> ans=new ArrayList<>();
		while(start<N&&end<N) {
			sum+=list[end];
			if(list[end]>M) {
				sum=0;
				start=end+1;
				end=end+1;
				continue;
			}
			else {
				if(sum>M) {
					sum-=list[start];
                    sum-=list[end];
					start++;
				}
				else {
					ans.add(sum);
					end++;
				}
			}
		}
		int max;
		if(ans.size()==0) {
			max=0;
		}
		else {
			max=Collections.max(ans);
		}
		

		System.out.println(max);
	}
}
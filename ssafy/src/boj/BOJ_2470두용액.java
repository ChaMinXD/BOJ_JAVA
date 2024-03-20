package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2470두용액 {
	static int N;
	static ArrayList<Integer> p=new ArrayList();
	static ArrayList<Integer> m=new ArrayList();

	static int gap=Integer.MAX_VALUE-1;
	static int[] ans=new int[2];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int a=Integer.parseInt(st.nextToken());
			if(a<0)
				m.add(a);
			else
				p.add(a);
		}
		Collections.sort(m);
		Collections.sort(p);
		if(m.isEmpty()) {
			ans[0]=p.get(0);
			ans[1]=p.get(1);
		}
		else if(p.isEmpty()) {
			ans[0]=m.get(m.size()-2);
			ans[1]=m.get(m.size()-1);
		}
		else{
			for(int i=0;i<m.size();i++) {
				BS(i);
			}
			if(p.size()>1) {
				int sum=p.get(0)+p.get(1);
				if(sum<gap) {
					gap=sum;
					ans[0]=p.get(0);
					ans[1]=p.get(1);
				}
			}
			if(m.size()>1) {
				int sum=Math.abs(m.get(m.size()-2)+m.get(m.size()-1));
				if(sum<gap) {
					gap=sum;
					ans[0]=m.get(m.size()-2);
					ans[1]=m.get(m.size()-1);
				}
			}
		}
		System.out.println(ans[0]+" "+ans[1]);
	}
	static void BS(int num) {
		int s=0;
		int e=p.size();
		while(s<e) {
			int mid=(s+e)/2;
			int n=Math.abs(m.get(num));
			if(n<p.get(mid)) 
				e=mid;
			else
				s=mid+1;				
		}
		if(e==0) {
			int sum=Math.abs(m.get(num)+p.get(e));
			if(sum<gap) {
				gap=sum;
				ans[0]=m.get(num);
				ans[1]=p.get(e);
			}
		}
		else if(e==p.size()) {
			int sum=Math.abs(m.get(num)+p.get(e-1));
			if(sum<gap) {
				gap=sum;
				ans[0]=m.get(num);
				ans[1]=p.get(e-1);
			}
		}
		else {
			for(int i=0;i<2;i++) {
				int sum=Math.abs(m.get(num)+p.get(e-i));
				if(sum<gap) {
					gap=sum;
					ans[0]=m.get(num);
					ans[1]=p.get(e-i);
				}	
			}		
		}
	}

}

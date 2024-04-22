package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_7490 {
	static int T;
	static int[] list;
	static int[] oper;
	static int ans;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());		
		for(int t=0;t<T;t++) {
			ans=0;
			int a=Integer.parseInt(br.readLine());
			list=new int[a];
			for(int i=0;i<a;i++) {
				list[i]=i+1;
			}
			oper=new int[a-1];
			DFS(0,a);
			sb.append("\n");
			
		}
		System.out.println(sb.toString());
	}
	static void DFS(int cnt,int a) {
		if(cnt==a-1) {
			ArrayList<Integer> numlist=new ArrayList();
			ArrayList<Integer> operlist=new ArrayList();
			int num=list[0];
			
			for(int i=1;i<a;i++) {
				
				if(oper[i-1]==1) {
					numlist.add(num);
					operlist.add(0);
					num=list[i];
				}else if(oper[i-1]==2) {
					numlist.add(num);
					operlist.add(1);
					num=list[i];
				}else {
					num=Integer.parseInt(Integer.toString(num)+Integer.toString(list[i]));
				}
			}
			if(num!=-1)
				numlist.add(num);
			int sum=numlist.get(0);

			for(int i=0;i<operlist.size();i++) {
				if(operlist.get(i)==0) {
					sum+=numlist.get(i+1);
				}else {
					sum-=numlist.get(i+1);
				}
			}
			if(sum==0) {
				sb.append(list[0]);
				for(int i=0;i<a-1;i++) {
					if(oper[i]==0)
						sb.append(" ");
					else if(oper[i]==1)
						sb.append("+");
					else
						sb.append("-");
					sb.append(list[i+1]);
				}
				sb.append("\n");
			}
				
			return;
		}
		for(int i=0;i<3;i++) {
			oper[cnt]=i;
			DFS(cnt+1,a);
		}
	}

}

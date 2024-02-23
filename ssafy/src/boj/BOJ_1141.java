package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1141 {
	static int N;
	static ArrayList<String> list=new ArrayList();
	static class Com implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			return o1.length()-o2.length();
		}		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			list.add(br.readLine());
		}
		Collections.sort(list,new Com());
		for(int i=0;i<list.size()-1;i++) {
			int cnt=0;
			ArrayList<Integer> idx=new ArrayList();
			for(int j=i+1;j<list.size();j++) {
				StringBuilder sb=new StringBuilder();
				sb.append(list.get(j).substring(0, list.get(i).length()));
				if(sb.toString().equals(list.get(i))) {
					idx.add(j);
					cnt++;
				}
			}
			if(cnt>1) {
				list.remove(i);
				i--;
			}
			else {
				for(int k=0;k<idx.size();k++) {
					int index=idx.get(k);
					list.remove(index);
				}
			}
				
		}
		System.out.println(list.toString());
		System.out.println(list.size());
	}
	
}

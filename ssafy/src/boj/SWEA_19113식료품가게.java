package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_19113식료품가게 {
	static int T;
	static int N;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		for(int t=1;t<T+1;t++) {
			N=Integer.parseInt(br.readLine());
			StringBuilder sb=new StringBuilder();

			list=new ArrayList();		
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0;i<N*2;i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			for(int i=0;i<N;i++) {
				binaryS(i,list.get(i)/3*4);
			}
			sb.append("#"+t+" ");
			for(int i=0;i<list.size();i++) {
				sb.append(list.get(i)).append(" ");			
			}
			sb.append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
	}
	static void binaryS(int idx,int val) {
		int left=idx;
		int right=list.size();
		while(left<right) {
			int mid=(left+right)/2;
			if(list.get(mid)<val) {
				left=mid+1;
			}
			else {
				right=mid;
			}
		}
		list.remove(right);
	}

}

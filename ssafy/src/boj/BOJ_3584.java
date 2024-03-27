package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_3584 {
	static int T,N;
	static int[] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			N=Integer.parseInt(br.readLine());
			parent=new int[N+1];
			for(int i=1;i<N+1;i++) {
				parent[i]=i;
			}
			for(int i=0;i<N-1;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int p=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				parent[c]=p;
			}
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			ArrayList<Integer> l=new ArrayList();
			l.add(a);
			while(parent[a]!=a) {
				a=parent[a];
				l.add(a);
			}
			if(l.contains(b))
				System.out.println(b);
			else {
				while(parent[b]!=b) {
					b=parent[b];
					if(l.contains(b)) {
						System.out.println(b);
						break;
					}
				}
			}		
		}
	}

}

package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2568 {
	static int N;
	static class Node implements Comparable<Node>{
		int s;
		int e;
		Node(int s,int e){
			this.s=s;
			this.e=e;
		}
		@Override
		public int compareTo(Node o) {
			return this.s-o.s;
		}
	}
	static int[] leng;
	static int[] next;
	static ArrayList<Integer> ans=new ArrayList();
	static ArrayList<Node> list=new ArrayList();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		leng=new int[N+1];
		next=new int[N];

		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			list.add(new Node(s,e));
		}
		Collections.sort(list);
		int num=LIS();
		System.out.println(N-num-1);
		for(int i=0;i<N;i++) {
			ans.add(i);
		}
		int a=leng[num];
		ans.remove(a);
		for(int i=0;i<num;i++) {		
			a=next[a];
			ans.remove(a);
		}
		for(int i=0;i<ans.size();i++) {
			System.out.println(list.get(ans.get(i)).s);
		}
		
		
		
	}
	static int LIS() {
		int end=0;
		leng[end]=0;
	
		for(int i=1;i<N;i++) {
			Node now=list.get(i);
			if(list.get(leng[end]).e<now.e) {
				leng[end+1]=i;
				next[i]=leng[end];
				end++;
			}
			else {
				int left=0;
				int right=end;
				while(left<right) {
					int mid=(left+right)/2;
					if(list.get(leng[mid]).e<now.e)
						left=mid+1;
					else {
						right=mid;
					}
				}
				leng[right]=i;
				if(right==0) {
					next[i]=0;
				}
				else
					next[i]=leng[right-1];
				
			}
			
		}
		return end;
	}

}
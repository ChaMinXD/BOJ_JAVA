package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_2812 {
	static int N,K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		char[] list=br.readLine().toCharArray();
		Deque<Integer> num=new ArrayDeque();
		num.add(list[0]-'0');
		
		int cnt=0;
		for(int i=1;i<N;i++) {
			
			int fir=num.peek();
				while(fir<list[i]-'0') {
					if(!num.isEmpty()&&cnt<K) {
						num.pollFirst();
						cnt++;
						if(num.isEmpty())
							break;
						fir=num.peek();	
						}
					else
						break;
				}
			
			
			num.addFirst(list[i]-'0');
			
		}

		while(cnt<K) {
			cnt++;
			num.pollFirst();
		}
		while(!num.isEmpty()) {
			sb.append(num.pollLast());
		}
			
		
		System.out.println(sb.toString());
		
		


	}
}

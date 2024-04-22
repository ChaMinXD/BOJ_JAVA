package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1522 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String list=br.readLine();
		int cnt=0;
		int ans=Integer.MAX_VALUE;
		for(int i=0;i<list.length();i++) {
			if(list.charAt(i)=='b')
				cnt++;
		}
		for(int i=0;i<list.length();i++) {
			int bcnt=0;
			for(int j=0;j<cnt;j++) {
				if(i+j>=list.length()) {
					if(list.charAt(i+j-list.length())=='b')
						bcnt++;
				}else {
					if(list.charAt(i+j)=='b') {
						bcnt++;
					}
				}	
			}
			ans=Math.min(ans, cnt-bcnt);
		}
		if(ans==Integer.MAX_VALUE)
			ans=0;
		System.out.println(ans);
		
	}
}

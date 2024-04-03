package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_16637 {
	static int N,cnt;
	static char[] list;
	static int ans=Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		list=new char[N];
		list=br.readLine().toCharArray();
		cnt=N/2+1;
		int val=list[0]-'0';
		DFS(0,val);
		for(int i=1;i<cnt-1;i++) {
			if(list[i*2-1]=='+') {
				val=val+(list[i*2]-'0');
			}
			else if(list[i*2-1]=='-') {
				val=val-(list[i*2]-'0');

			}	
			else if(list[i*2-1]=='*'){
				val=val*(list[i*2]-'0');
			}
			DFS(i,val);
		}
		System.out.println(ans);
	}
	static void DFS(int idx,int v) {
		if(idx==cnt-1) {
			System.out.println("ans");
			ans=Math.max(ans, v);
			System.out.println(ans);
			return;
		}
		for(int i=idx+1;i<cnt;i++) {
			int cal=v;
			if(list[i*2-1]=='+') {
				cal=cal+(list[i*2]-'0');
			}
			else if(list[i*2-1]=='-') {
				cal=cal-(list[i*2]-'0');

			}	
			else if(list[i*2-1]=='*'){
				cal=cal*(list[i*2]-'0');
			}
			DFS(i,cal);
		}
	}
	
}

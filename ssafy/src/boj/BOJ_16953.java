package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16953 {
	static int A,B;
	static int ans;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		A=Integer.parseInt(st.nextToken());
		B=Integer.parseInt(st.nextToken());
		cal(B,1);
		System.out.println(ans);
	}
	static void cal(int k,int cnt) {
		if(k==A)
			ans=cnt;
		else if(k>A) {
			if(k%10==1) {
				k=k/10;
				cal(k,cnt+1);
			}
			else {
				if(k%2==0)
					cal(k/2,cnt+1);
				else {
					ans=-1;
				}
			}
		}
		else {
			ans=-1;
		}
		
		
		
		
	}
}

package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_9935 {
	static String s;
	static String rep;
	static char[] check;
	static ArrayList<Integer> idx= new ArrayList();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		s=br.readLine();
		rep=br.readLine();
		int cnt=0;
		check=new char[s.length()];
		for(int i=0;i<s.length();i++) {
			int now=i-cnt*rep.length();
			check[now]=s.charAt(i);
			if(s.charAt(i)==rep.charAt(rep.length()-1)) {
				if(now>rep.length()-2) {
				
					StringBuilder sb=new StringBuilder();
					for(int j=0;j<rep.length();j++) {
						sb.append(check[now-rep.length()+1+j]);
					}
					if(sb.toString().equals(rep)) {
						for(int j=0;j<rep.length();j++) {
							check[now-rep.length()+1+j]=' ';
						}
						cnt++;
					}
				}
			}
			
		}
		int idx=s.length();;
		for(int i=0;i<check.length;i++) {
			if(check[i]=='\0'||check[i]==' ') {
				idx=i;
				break;
			}
		}
	
		if(idx==0)
			System.out.println("FRULA");
		else {
			StringBuilder sbb=new StringBuilder();
			for(int i=0;i<idx;i++) {
				sbb.append(check[i]);
			}
			System.out.println(sbb.toString());
		}	
	}
	
}

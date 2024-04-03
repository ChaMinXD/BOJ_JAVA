package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_17609 {
	static int T;
	static String[] list;
	static int idx;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		StringBuilder sbsb=new StringBuilder();
		list=new String[T];
		for(int i=0;i<T;i++) {
			idx=-1;
			list[i]=br.readLine();
			if(check(list[i])) {
				sbsb.append("0\n");
			}
			else {
				boolean flag=false;
				String a=new StringBuilder(list[i]).deleteCharAt(idx).toString();

				if(check(a)) {
					flag=true;
				}
				String b=new StringBuilder(list[i]).deleteCharAt(list[i].length()-idx-1).toString();
				if(check(b)) {
					flag=true;
				}
				
				if(flag)
					sbsb.append("1\n");
				else
					sbsb.append("2\n");
			}
			
		}
		bw.write(sbsb.toString());
		bw.flush();
	}
	static boolean check(String a) {
		int size=a.length();
		for(int i=0;i<size/2;i++) {
			if(a.charAt(i)!=a.charAt(size-1-i)) {
				if(idx==-1) {
				idx=i;
				}
				return false;
			}
		}
		return true;
	}

}

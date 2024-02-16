package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_16496 {
	static class Com implements Comparator<String>{
		@Override
		  public int compare(String a, String b) {
	        String ab = a + b;
	        String ba = b + a;
	        return ba.compareTo(ab);
	    }
			
		
	}
		
	static int N;
	static String[] list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new String[N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			list[i]=st.nextToken();
		}
		Arrays.sort(list , new Com());
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<N;i++) {
			sb.append(list[i]);
		}
		if(sb.toString().charAt(0)=='0')
			System.out.println("0");
		else
			System.out.println(sb.toString());
		
		
	}
}

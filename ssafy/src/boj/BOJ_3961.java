package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_3961 {
	static char[][] key;
	static class WordList implements Comparable<WordList> {
		String word;
		int leng;
		WordList(String word,int leng){
			this.word=word;
			this.leng=leng;
		}
		
		@Override
		public int compareTo(WordList o) {
			if(o.leng==leng) {
				return word.compareTo(o.word);
			}
			else {
				return leng-o.leng;
			}
			
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		int t=scan.nextInt();
		key=new char[3][];
		String line1="qwertyuiop";
		String line2="asdfghjkl";
		String line3="zxcvbnm";
		key[0]=line1.toCharArray();
		key[1]=line2.toCharArray();
		key[2]=line3.toCharArray();
		for(int i=0;i<t;i++) {
			String word=scan.next();
			int n=scan.nextInt();
			List<WordList> list=new ArrayList<>();
			for(int j=0;j<n;j++) {
				String w=scan.next();
				
				list.add(new WordList(w,0));
			}
			for(int j=0;j<n;j++) {
				for(int k=0;k<word.length();k++) {
					if(word.charAt(k)==list.get(j).word.charAt(k)) continue;
					list.get(j).leng+=find_length(word.charAt(k),list.get(j).word.charAt(k));
				}
			}
			Collections.sort(list);
			for(int j=0;j<n;j++) {
				System.out.println(list.get(j).word+" "+list.get(j).leng);
			}
		}
		
	}
	static int find_length(char a,char b) {
		int ax=0,ay=0,bx=0,by=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<key[i].length;j++) {
				if(key[i][j]==a) {
					ax=i;
					ay=j;
				}
				if(key[i][j]==b) {
					bx=i;
					by=j;
				}
			}
		}
		return Math.abs(ax-bx)+Math.abs(ay-by);
	}

}

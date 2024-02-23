package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11758 {
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static Pos[] list=new Pos[3];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0;i<3;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			list[i]=new Pos(x,y);
		}
		int ccw=(list[1].x-list[0].x)*(list[2].y-list[0].y)-(list[1].y-list[0].y)*(list[2].x-list[0].x);
		if(ccw==0)
			System.out.println(0);
		else if(ccw>0)
			System.out.println(1);
		else
			System.out.println(-1);
	}
}

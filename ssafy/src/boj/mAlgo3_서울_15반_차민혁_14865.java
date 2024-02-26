package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class mAlgo3_서울_15반_차민혁_14865 {
	static int N;
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static Pos[] list;
	static ArrayList<Pos> cut=new ArrayList();
	static ArrayList<Integer> mount=new ArrayList();

		public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		list=new Pos[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			list[i]=new Pos(x,y);
		}
		Cut();
		
		for(int i=0;i<cut.size();i++) {
			if(cut.get(i).y==0) {
				mount.add(cut.get(i).x);
			}
		}
		boolean dir=true;
		// 포함 o 포함 x
		int a_count=0;
		int b_count=0;
		for(int i=1;i<mount.size();i++) {
			if(i%2==0) {
				if(dir&&mount.get(i)>mount.get(i-1))
					b_count++;		
				else if(dir&&mount.get(i)<mount.get(i-1)) {
					dir=!dir;
					a_count++;
				}
				else if(!dir&&mount.get(i)>mount.get(i-1)) {
					dir=!dir;
					a_count++;
				}
				else
					b_count++;
			}
		}
		b_count++;
		if(a_count==0)
			a_count++;
		System.out.println(a_count+" "+b_count);
		
	}
	static void Cut() {
		for(int i=0;i<N-1;i++) {
			if((list[i].y>0&&list[i+1].y<0)||(list[i].y<0&&list[i+1].y>0)) {
				if(list[i+1].y==list[i].y) continue;
				else {
					if(list[i+1].y<0)list[i+1].y=0;
					else list[i].y=0;
				}
			}
			if(list[i].y>=0)
				cut.add(new Pos(list[i].x,list[i].y));
		}

	}
	
}

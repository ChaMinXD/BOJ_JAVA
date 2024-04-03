package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_무선충전 {
	static int T,M,A;
	static int[] listA;
	static int[] listB;
	static ArrayList<Integer>[][] map;
	static int[][] visited;
	static int[][] ways= {{-1,0},{0,1},{1,0},{0,-1}};
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	static class Charge{
		Pos p;
		int l;
		int v;
		Charge(Pos p,int l, int v){
			 this.p=p;
			 this.l=l;
			 this.v=v;
		}
	}
	static Charge[] bc;
	static int ans;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();

		for(int t=1;t<T+1;t++) {
			map=new ArrayList[10][10];
			
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					map[i][j]=new ArrayList();
				}
			}
			StringTokenizer st=new StringTokenizer(br.readLine());	
			M=Integer.parseInt(st.nextToken());
			A=Integer.parseInt(st.nextToken());
			listA=new int[M];
			listB=new int[M];
			bc=new Charge[A];
			st=new StringTokenizer(br.readLine());	
			for(int i=0;i<M;i++) {
				listA[i]=Integer.parseInt(st.nextToken());
			}
			st=new StringTokenizer(br.readLine());	
			for(int i=0;i<M;i++) {
				listB[i]=Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<A;i++) {
				st=new StringTokenizer(br.readLine());
				int y=Integer.parseInt(st.nextToken())-1;
				int x=Integer.parseInt(st.nextToken())-1;
				int c=Integer.parseInt(st.nextToken());
				int p=Integer.parseInt(st.nextToken());
				bc[i]=new Charge(new Pos(x,y),c,p); 
				
				
			}
			ans=0;
			Pos af=new Pos(0,0);
			Pos bf=new Pos(9,9);
			check(af,bf);
			for(int i=0;i<M;i++) {
				int adir=listA[i]-1;
				int bdir=listB[i]-1;
				if(adir>=0) {
					int ax=af.x+ways[adir][0];
					int ay=af.y+ways[adir][1];
					af=new Pos(ax,ay);
				}
				if(bdir>=0) {
					int bx=bf.x+ways[bdir][0];
					int by=bf.y+ways[bdir][1];
					bf=new Pos(bx,by);
				}
				check(af,bf);
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		
		}
		bw.write(sb.toString());
		bw.flush();
		
	}
	static void check(Pos a,Pos b) {
		ArrayList<Integer> list =new ArrayList();
		ArrayList<Integer> alist =new ArrayList();
		ArrayList<Integer> blist =new ArrayList();
		for(int i=0;i<A;i++) {
			Pos p=bc[i].p;
			int l=bc[i].l;
			if(Math.abs(a.x-p.x)+Math.abs(a.y-p.y)<=l) {
				alist.add(i);
			}
			if(Math.abs(b.x-p.x)+Math.abs(b.y-p.y)<=l) {
				blist.add(i);
			}
			Collections.sort(alist,(o1,o2)->{
				return bc[o2].v-bc[o1].v;
			});
			Collections.sort(blist,(o1,o2)->{
				return bc[o2].v-bc[o1].v;
			});
		}
		if(alist.size()>0&&blist.size()>0) {
			list=new ArrayList(alist);
			for(int j=0;j<blist.size();j++) {
				if(list.contains(blist.get(j))) continue;
				list.add(blist.get(j));
			}
			if(list.size()>1) {
				Collections.sort(list,(o1,o2)->{
					return bc[o2].v-bc[o1].v;
				});
				if(list.get(0)==alist.get(0)&&list.get(0)==blist.get(0)) {
					ans+=bc[list.get(0)].v+bc[list.get(1)].v;
				}
				else
					ans+=bc[alist.get(0)].v+bc[blist.get(0)].v;
			}
			else
				ans+=bc[list.get(0)].v;
		}
		else {
			if(alist.size()>0) {
				Collections.sort(alist,(o1,o2)->{
					return bc[o2].v-bc[o1].v;
				});
				ans+=bc[alist.get(0)].v;
			}
			if(blist.size()>0) {
				Collections.sort(blist,(o1,o2)->{
					return bc[o2].v-bc[o1].v;
				});
				ans+=bc[blist.get(0)].v;
			}
		}
	}
	

}

package boj;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_4963 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		while(true) {
			int w=scan.nextInt();
			int h=scan.nextInt();
			int sum=0;
			if(w==0||h==0)
				break;
			else {
				int [][]map=new int[h][w];
				for(int i=0;i<h;i++) {
					for(int j=0;j<w;j++) {
						map[i][j]=scan.nextInt();
					}
				}
				int way=8;
				int [][]ways=new int[][] {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
				boolean [][]visited=new boolean[h][w];
				for(int i=0;i<h;i++) {
					for(int j=0;j<w;j++) {
						if(map[i][j]==0||visited[i][j]) continue;
						else {
							visited[i][j]=true;
							Queue<ArrayList<Integer>> q=new LinkedList<>();
							ArrayList<Integer> a=new ArrayList<>();
							a.add(i);
							a.add(j);
							q.add(a);
							while(!q.isEmpty()) {
								int hh=q.element().get(0);
								int ww=q.element().get(1);
								q.remove();
								for(int k=0;k<way;k++) {
									int nh=hh+ways[k][0];
									int nw=ww+ways[k][1];
									if(nh<0||nh>h-1||nw<0||nw>w-1) continue;
									if(visited[nh][nw])continue;
									if(map[nh][nw]==1) {
										ArrayList<Integer> ar=new ArrayList<>();
										ar.add(nh);
										ar.add(nw);
										q.add(ar);
										visited[nh][nw]=true;
									}					
								}
							}
							sum++;
						}
					}
				}
				System.out.println(sum);
			}
		}
	}
}

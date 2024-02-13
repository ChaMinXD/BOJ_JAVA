package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_12100 {
	static int N;
	static ArrayList<ArrayList<Integer>> map;
	static ArrayList<ArrayList<Integer>> start_map;
	static int max=0;
	static boolean visited[];
	static int[][] way= {{-1,0},{1,0},{0,-1},{0,1}};
	static int[] list=new int[5];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		visited=new boolean[4]; // 0 상 1 하 2좌 3우 
		map=new ArrayList<>();
		for(int i=0;i<N;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			ArrayList<Integer> a=new ArrayList();
			for(int j=0;j<N;j++) {
				a.add(Integer.parseInt(st.nextToken()));
			}
			map.add(a);
			
	
		}
		// 초기 맵 깊은복사
		start_map=new ArrayList<>();
		for(int i=0;i<N;i++) {
			ArrayList<Integer> copy=new ArrayList<>();
			copy.addAll(map.get(i));
			start_map.add(copy);
		}
		play(0);	
		System.out.println(max);

	}
	static void play(int cnt) {
		if(cnt==5) {
			for(int i=0;i<5;i++) {
				push(list[i]);
			}
			int m=0;
			for(int i=0;i<N;i++) {
				m=m>Collections.max(map.get(i))?m:Collections.max(map.get(i));
			}
			if(max<m) {
				max=m;
			}
			back();
			
			return;
		}
		for(int i=0;i<4;i++) {
			list[cnt]=i;
			play(cnt+1);
			
		}
		
	}
	static void back() {
		map=new ArrayList<>();
		for(int i=0;i<N;i++) {
			ArrayList<Integer> copy=new ArrayList<>();
			copy.addAll(start_map.get(i));
			map.add(copy);
		}
	}
	static void push(int direction) {
		// 0 상 1 하 2좌 3우 
		switch (direction) {
		case 0: { // 위로 밀기
			//0인곳 밀기
			for(int i=0;i<N;i++) {
				ArrayList<Integer> save=new ArrayList<>();
				for(int j=0;j<N;j++) {
					if(map.get(j).get(i)==0) continue;
					save.add(map.get(j).get(i));
				}
				for(int j=0;j<N;j++) {
					if(j<save.size()) {
						map.get(j).set(i,save.get(j));
					}
					else {
						map.get(j).set(i,0);
					}
				}
			}
		
			for(int i=1;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map.get(i).get(j)==0) continue;
					int nx=i+way[direction][0];
					if(map.get(i).get(j).equals(map.get(nx).get(j))) {
						
						map.get(nx).set(j,map.get(i).get(j)*2);
						for(int k=i;k<N-1;k++) {
							map.get(k).set(j, map.get(k+1).get(j));
						}
						map.get(N-1).set(j, 0);
					}
				}
			}
			break;
			
		}
		case 1: { // 아래로 밀기
			//0인곳 밀기
			for(int i=0;i<N;i++) {
				ArrayList<Integer> save=new ArrayList<>();
				for(int j=N-1;j>=0;j--) {
					if(map.get(j).get(i)==0) continue;
					save.add(map.get(j).get(i));
				}
				for(int j=N-1;j>=0;j--) {
					if(j>N-1-save.size()) {
						map.get(j).set(i,save.get(N-j-1));
					}
					else {
						map.get(j).set(i,0);
					}
				}
			}

			for(int i=N-2;i>=0;i--) {
				for(int j=0;j<N;j++) {
					if(map.get(i).get(j)==0) continue;
					int nx=i+way[direction][0];
					if(map.get(i).get(j).equals(map.get(nx).get(j))) {
						map.get(nx).set(j,map.get(i).get(j)*2);
						for(int k=i;k>0;k--) {
							map.get(k).set(j, map.get(k-1).get(j));
						}
						map.get(0).set(j, 0);
					}
				}
			}
			
			break;
				
			}
		case 2: {
			for(int i=0;i<N;i++) {
				ArrayList<Integer> save=new ArrayList<>();
				for(int j=0;j<N;j++) {
					if(map.get(i).get(j)==0) continue;
					save.add(map.get(i).get(j));
				}
				for(int j=0;j<N;j++) {
					if(j<save.size()) {
						map.get(i).set(j,save.get(j));
					}
					else {
						map.get(i).set(j,0);
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=1;j<N;j++) {
					if(map.get(i).get(j)==0) continue;
					int ny=j+way[direction][1];
					if(map.get(i).get(j).equals(map.get(i).get(ny))) {
						map.get(i).set(ny,map.get(i).get(j)*2);
						map.get(i).remove(j);
						map.get(i).add(0);
					}
				}
			}
			
			break;
			
		}
		case 3: {
			for(int i=0;i<N;i++) {
				ArrayList<Integer> save=new ArrayList<>();
				for(int j=N-1;j>=0;j--) {
					if(map.get(i).get(j)==0) continue;
					save.add(map.get(i).get(j));
				}
				for(int j=N-1;j>=0;j--) {
					if(j>N-1-save.size()) {
						map.get(i).set(j,save.get(N-j-1));
					}
					else {
						map.get(i).set(j,0);
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=N-2;j>=0;j--) {
					if(map.get(i).get(j)==0) continue;
					int ny=j+way[direction][1];
					if(map.get(i).get(j).equals(map.get(i).get(ny))) {
						map.get(i).set(ny,map.get(i).get(j)*2);
						for(int k=j;k>0;k--) {
							map.get(i).set(k, map.get(i).get(k-1));
						}
						map.get(i).set(0,0);
					}
				}
			}
			
			break;		
		}
		
	}
	}
}


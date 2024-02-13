package boj;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1194 {
	static int n;
	static int m;
	static int min=Integer.MAX_VALUE;
	static char[][] map;
	static Queue<ArrayList<Integer>> q=new LinkedList<>();
	static int [][][] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		m=scan.nextInt();

		map=new char[n][m];
		visited=new int[n][m][2];
		int start_x = 0;
		int start_y = 0;
		for(int i=0;i<n;i++) {
			map[i]=scan.next().toCharArray();
		}
		
		for(int i=0;i<n;i++) { 
			for(int j=0;j<m;j++) {
				if(map[i][j]=='0') {
					start_x=j;
					start_y=i;
				}
				visited[i][j][0]=0; // visited
				visited[i][j][1]=0; // cnt
				
			}
		
		}
		ArrayList<Integer> a=new ArrayList<>();
		a.add(start_x); // x,y좌표 ,a,b,c,d,e,f 열쇠 유무 2 3 4 5 6 7
		a.add(start_y);
		for(int i=0;i<6;i++) {
			a.add(0);
		}
		q.add(a);
		BFS();
		
		if(min==Integer.MAX_VALUE)
			min=-1;
		
		System.out.println(min);

		
	}
	
	static void BFS() {
		int way=4;
		int [][] ways= {{1,0},{-1,0},{0,1},{0,-1}};
		
		while(!q.isEmpty()) {
			int x=q.element().get(0);
			int y=q.element().get(1);
			int key[]=new int[6];
			for(int w=0;w<6;w++) {
				key[w]=q.element().get(w+2);
			}
			q.poll();
			visited[y][x][0]=1;
//			System.out.println("y :"+y);
//			System.out.println("x :"+x);
//			System.out.println("cnt :"+visited[y][x][1]);
			for(int i=0;i<way;i++) {
				int nx=x+ways[i][0];
				int ny=y+ways[i][1];
				if(nx<0||nx>m-1||ny<0||ny>n-1) continue;
				if(visited[ny][nx][0]==1)
					continue;
				if(map[ny][nx]=='#')
					continue;
				
				else if(map[ny][nx]>='A' && map[ny][nx]<='F') { // 문
					int compare=map[ny][nx]-65;
					int check=key[compare];
					if(check==1) {
						ArrayList<Integer> a=new ArrayList<>();
						a.add(nx); // x,y좌표 ,a,b,c,d,e,f 열쇠 유무 2 3 4 5 6 7
						a.add(ny);
						for(int j=0;j<6;j++) {
							a.add(key[j]);
						}
						q.add(a);
						System.out.println(a.toString());

						visited[ny][nx][1]=visited[y][x][1]+1;
					}
					else {
						continue;
					}
				}
				
				else if(map[ny][nx]=='.'||map[ny][nx]=='0') {  //그냥 도착
					ArrayList<Integer> a=new ArrayList<>();
					a.add(nx); // x,y좌표 ,a,b,c,d,e,f 열쇠 유무 2 3 4 5 6 7
					a.add(ny);
					for(int j=0;j<6;j++) {
						a.add(key[j]);
					}
					q.add(a);
					System.out.println(a.toString());
					
					visited[ny][nx][1]=visited[y][x][1]+1;
				}
				else if(map[ny][nx]>='a' && map[ny][nx]<='f') { // 문
					
					ArrayList<Integer> a=new ArrayList<>();
					a.add(nx); // x,y좌표 ,a,b,c,d,e,f 열쇠 유무 2 3 4 5 6 7
					a.add(ny);
					int compare=map[ny][nx]-97;
					for(int j=0;j<6;j++) {
						if(j==compare) {
							a.add(1);
						}
						else {
							a.add(key[j]);
						}
					}
					q.add(a);
					System.out.println(a.toString());

					for(int z=0;z<n;z++) {
						for(int c=0;c<m;c++) {
							visited[z][c][0]=0;
						}
					}

					visited[ny][nx][1]=visited[y][x][1]+1;
					
					
				}
				else{ // 1 도착일때
					if(min>visited[y][x][1]+1)
						min=visited[y][x][1]+1;
				}
			}
		}
		
	}
	

}

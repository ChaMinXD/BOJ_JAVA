package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719 {
	static int W,H;
	static int[][] map;
	static int[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		H=Integer.parseInt(st.nextToken());
		W=Integer.parseInt(st.nextToken());
		map=new int[H][W];
		list=new int[W];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<W;i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<W;i++) {
			for(int j=0;j<list[i];j++) {
				map[H-1-j][i]=1;
			}
		}
		int cnt=0;
		for(int i=H-1;i>=0;i--) {
			int s=-1,e=-1;
			for(int j=0;j<W;j++) {
				if(map[i][j]==1) {
					if(s==-1)
						s=j;
					else
						e=j;
				}
			}
			if(s!=-1&&e!=-1) {
				for(int j=s+1;j<e;j++) {
					if(map[i][j]==0)
						cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

}

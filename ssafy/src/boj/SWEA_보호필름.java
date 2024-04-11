package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_보호필름 {
	static int T,D,W,K;
	static int[][] map;
	static int[] combi;
	static int[] sub;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine().trim());
		StringBuilder sb=new StringBuilder();
		for(int t=1;t<T+1;t++) {
			ans=-1;
			StringTokenizer st=new StringTokenizer(br.readLine());
			D=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			map=new int[D][W];
			combi=new int[D];
			sub=new int[D];
			for(int i=0;i<D;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			if(K==1||check(map))
				ans=0;
			else {
				for(int i=1;i<=K;i++) {
					DFS(i,0);
					if(ans!=-1)
						break;
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void DFS(int com,int cnt) {
		if(cnt==com) {
			subset(cnt,0);	
			return;
		}
		for(int i=0;i<D;i++) {
			if(cnt>0&&combi[cnt-1]>=i) continue;
			combi[cnt]=i;
			DFS(com,cnt+1);
			combi[cnt]=0;
		}
		
	}
	static void subset(int su,int cnt) {
		if(su==cnt) {
			int[][] copy_map=new int[D][W];
			for(int i=0;i<D;i++) {
				for(int j=0;j<W;j++) {
					copy_map[i][j]=map[i][j];
				}
			}
			for(int i=0;i<cnt;i++) {
				change(copy_map,combi[i],sub[i]);
			}
			if(check(copy_map))
				ans=cnt;
			return;
		}

		subset(su,cnt+1);
		sub[cnt]=1;
		subset(su,cnt+1);
		sub[cnt]=0;

	}
	static boolean check(int[][] c_map) {
		for(int i=0;i<W;i++) {
			int cnt=1;
			int s=c_map[0][i];
			boolean flag=false;
			for(int j=1;j<D;j++) {
				if(c_map[j][i]==s) {
					cnt++;
				}
				else {
					s=c_map[j][i];
					cnt=1;
				}
				if(cnt>K-1)
					flag=true;
			}
			if(cnt>K-1)
				flag=true;
			if(!flag)
				return false;
		}
		return true;
	}
	static void change(int[][] list,int idx,int ab) {
		for(int i=0;i<list[idx].length;i++) {
			list[idx][i]=ab;
		}
	}
}

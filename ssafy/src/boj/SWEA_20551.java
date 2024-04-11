package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_20551 {
	static int T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		for(int t=1;t<T+1;t++) {
			sb.append("#").append(t).append(" ");
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			if(a<b&&b<c) {
				sb.append("0").append("\n");
			}else {
				if(b<=1||c<=2)
					sb.append("-1").append("\n");
				else {
					int cnt=0;
					while(b>=c) {
						b--;
						cnt++;
					}
					while(a>=b) {
						a--;
						cnt++;
					}
					sb.append(cnt).append("\n");
				}

			}
		}
		System.out.println(sb.toString());

	}

}

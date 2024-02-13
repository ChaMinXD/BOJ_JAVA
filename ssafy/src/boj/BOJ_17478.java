package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_17478 {
	static int N;
	static String a="\"재귀함수가 뭔가요?\"\n";
	static String b="\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
	static String c="라고 답변하였지.\n";
	static String d="\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
	static String e="마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
	static String f="그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
	static String o="____";
	static String g="라고 답변하였지.\n";
	static BufferedWriter bw;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		bw=new BufferedWriter(new OutputStreamWriter(System.out));
		N=Integer.parseInt(br.readLine());
		sb=new StringBuilder();
		int cnt=0;
		String start="어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n";
		bw.append(start);
		repeat(cnt,"");
		bw.write(sb.toString());
		bw.flush();
	}
	public static void repeat(int count, String under) throws IOException {

  if (count == N) {
      sb.append(under+a);
      sb.append(under+b);
      sb.append(under+c);
      return;

  }

  sb.append(under+a);
  sb.append(under+d);
  sb.append(under+e);
  sb.append(under+f);


  repeat(count + 1, under + o);
  sb.append(under+g);
}
	
	
}

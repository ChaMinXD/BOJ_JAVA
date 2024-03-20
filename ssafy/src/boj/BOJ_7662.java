package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_7662 {
	static int T;
	static int K;
	static boolean[] isDel;

	static class Node implements Comparable<Node> {
		int idx;
		int v;

		Node(int idx, int v) {
			this.idx = idx;
			this.v = v;
		}

		@Override
		public int compareTo(Node o) {
			if (this.v > o.v)
				return 1;
			else if (this.v == o.v)
				return 0;
			else
				return -1;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			PriorityQueue<Node> min = new PriorityQueue();
			PriorityQueue<Node> max = new PriorityQueue(Collections.reverseOrder());
			isDel = new boolean[1000001];
			K = Integer.parseInt(br.readLine());
			StringTokenizer st;
			int index = 0;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				String s = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				if (s.equals("I")) {
					max.add(new Node(index, a));
					min.add(new Node(index, a));
					index++;
				} else {
					if (a == -1) {
						while (!min.isEmpty()) {
							Node now = min.poll();
							if (isDel[now.idx])
								continue;
							else {
								isDel[now.idx] = true;
								break;
							}
						}

					} else {
						while (!max.isEmpty()) {
							Node now = max.poll();
							if (isDel[now.idx])
								continue;
							else {
								isDel[now.idx] = true;
								break;
							}
						}
					}
				}
			}

			// 출력
			boolean flag = false;
			if (max.size() == 0 || min.size() == 0)
				System.out.println("EMPTY");
			else {
				while (!max.isEmpty()) {
					Node now = max.poll();
					if (!isDel[now.idx]) {
						System.out.print(now.v + " ");
						flag = true;
						break;
					}
				}
				while (!min.isEmpty()) {
					Node now = min.poll();
					if (!isDel[now.idx]) {
						System.out.println(now.v);
						flag = true;
						break;
					}

				}
				if (!flag)
					System.out.println("EMPTY");
			}

		}
	}
}

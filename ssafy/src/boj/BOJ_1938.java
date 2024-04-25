package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_1938 {
	static int N;
	static int[][] map;

	static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Train {
		int dir;
		Pos cen;

		Train(int dir, Pos cen) {
			this.dir = dir;
			this.cen = cen;
		}
	}

	static Queue<Train> q = new ArrayDeque();
	static Queue<Pos> fir = new ArrayDeque();
	static Queue<Pos> End = new ArrayDeque();
	static int[][][] visited;
	static int[][] ways = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { -1, 1 }, { 1, 1 }, { -1, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[2][N][N];
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (c[j] == 'B') {
					fir.add(new Pos(i, j));
				} else if (c[j] == 'E') {
					End.add(new Pos(i, j));
				} else
					map[i][j] = c[j] - '0';
			}
		}
		// 시작과 끝의 가운데 위치 , dir check
		Pos ff = fir.poll();
		Pos fs = fir.poll();
		int fdir = -1;
		if (ff.x - fs.x == 0)
			fdir = 1;
		else
			fdir = 0;
		q.add(new Train(fdir, fs));
		visited[fdir][fs.x][fs.y] = 1;
		Pos ef = End.poll();
		Pos es = End.poll();
		int edir = -1;
		if (ef.x - es.x == 0)
			edir = 1;
		else
			edir = 0;
		// BFS
		while (!q.isEmpty()) {
			Train tr = q.poll();
			Pos now = tr.cen;
			for (int i = 0; i < 4; i++) {
				int nx = now.x + ways[i][0];
				int ny = now.y + ways[i][1];
				if (tr.dir == 0) { // 세로
					if (nx - 1 < 0 || nx + 1 > N - 1 || ny < 0 || ny > N - 1)
						continue;
					if (visited[tr.dir][nx][ny] != 0)
						continue;
					if (map[nx][ny] == 1 || map[nx - 1][ny] == 1 || map[nx + 1][ny] == 1)
						continue;
				} else {// 가로
					if (nx < 0 || nx > N - 1 || ny - 1 < 0 || ny + 1 > N - 1)
						continue;
					if (visited[tr.dir][nx][ny] != 0)
						continue;
					if (map[nx][ny] == 1 || map[nx][ny - 1] == 1 || map[nx][ny + 1] == 1)
						continue;
				}
				visited[tr.dir][nx][ny] = visited[tr.dir][now.x][now.y] + 1;
				q.add(new Train(tr.dir, new Pos(nx, ny)));
			}
			boolean check = false;
			for (int i = 0; i < 8; i++) {
				int nx = now.x + ways[i][0];
				int ny = now.y + ways[i][1];
				if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
					check = true;
					break;
				}
				if (map[nx][ny] == 1) {
					check = true;
					break;
				}
			}
			if (!check) {
				int ndir = (tr.dir + 1) % 2;
				if (visited[ndir][now.x][now.y] != 0)
					continue;
				visited[ndir][now.x][now.y] = visited[tr.dir][now.x][now.y] + 1;
				q.add(new Train(ndir, new Pos(now.x, now.y)));
			}
		}
		if (visited[edir][es.x][es.y] == 0)
			System.out.println("0");
		else
			System.out.println(visited[edir][es.x][es.y] - 1);

	}

}
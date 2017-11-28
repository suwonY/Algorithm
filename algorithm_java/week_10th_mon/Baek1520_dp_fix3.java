package week_10th_mon;

import java.util.*;
public class Baek1520_dp_fix3 {
	static int[][] a = new int[501][501];
	static int n, m;
	static int[] dx={-1,1,0,0};
	static int[] dy={0,0,-1,1};

	static int search(int x, int y) {
		if (x==n-1 && y==m-1)
			return 1;
		int visit = 0;
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(a[x][y]<=a[nx][ny]) continue;

			visit+=search(nx,ny);
		}
		return visit;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				a[i][j] = in.nextInt();
			}
		}
		System.out.println(search(0, 0));
		in.close();
	}
}
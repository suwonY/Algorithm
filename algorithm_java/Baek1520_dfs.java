package week_10th_mon;

import java.util.*;
public class Baek1520_dfs {

	/*내리막길
	 * 상하좌우로 이동 가능
	 * 자신의 높이보다 낮은 곳을 이동하고 싶어한다*/
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] a = new int[501][501];
	static boolean[][] c = new boolean[501][501];
	static int n, m, ans = 0;
	public static void go(Point now){
		int x = now.x;
		int y = now.y;
		if(x==n-1 && y==m-1){
			++ans;
			return;
		}
		
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(a[x][y]<a[nx][ny]) continue;
			if(c[nx][ny]==true) continue;
			
			c[nx][ny] = true;
			go(new Point(nx,ny));
			c[nx][ny] = false;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				a[i][j] = in.nextInt();
			}
		}
		
		go(new Point(0,0));
		c[0][0] = true;
		System.out.println(ans);
		
		in.close();
	}
	public static class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}

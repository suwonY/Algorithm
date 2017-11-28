package Baek_SWPractice_2nd;

import java.util.*;
public class Baek1261_Deque {

	static int n,m;
	static int[][] a = new int[1001][1001];
	static int[][] d = new int[1001][1001];
	static boolean[][] c = new boolean[1001][1001];
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		m = in.nextInt();
		n = in.nextInt();
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<m; j++){
				if(temp.charAt(j)=='0') a[i][j] = 0;
				else a[i][j] = 1;
			}
		}
		Deque<Point> q = new LinkedList<>();
		q.add(new Point(0,0));
		d[0][0] = (a[0][0]==1?1:0);
		c[0][0] = true;
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(c[nx][ny]) continue;
				
				if(a[nx][ny]==0){
					d[nx][ny] = d[x][y];
					c[nx][ny] = true;
					q.addFirst(new Point(nx,ny));
				}
				if(a[nx][ny]==1)
					d[nx][ny] = d[x][y] + 1;
					c[nx][ny] = true;
					q.addLast(new Point(nx,ny));
			}
		}
		System.out.println(d[n-1][m-1]);
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
package Baek_SWPractice_2nd;

import java.util.*;
public class Baek2206 {

	static int n,m;
	static int[][] a = new int[1001][1001];
	static int[][][] d = new int[1001][1001][2];
	static boolean[][] c = new boolean[1001][1001];
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<m; j++){
				if(temp.charAt(j)=='0') a[i][j] = 0;
				else a[i][j] = 1;
			}
		}
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0,0));
		d[0][0][0] = 1;
		
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			int w = now.w;
			
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(d[nx][ny][w]!=0) continue;
				
				if(a[nx][ny] == 0 && d[nx][ny][w] == 0){
					d[nx][ny][w] = d[x][y][w]+1;
					q.add(new Point(nx,ny,w));
				}
				
				if(w==0 && a[nx][ny] == 1 && d[nx][ny][w+1] == 0){
					d[nx][ny][w+1] = d[x][y][w]+1;
					q.add(new Point(nx,ny,w+1));
				}
			}
		}

		if(d[n-1][m-1][0] != 0 && d[n-1][m-1][1] != 0) System.out.println(Math.min(d[n-1][m-1][0], d[n-1][m-1][1]));
		else if(d[n-1][m-1][0] != 0) System.out.println(d[n-1][m-1][0]);
		else if(d[n-1][m-1][1] != 0) System.out.println(d[n-1][m-1][1]);
		else System.out.println(-1);
		
		in.close();
	}
	public static class Point{
		int x, y, w;
		public Point(int x, int y, int w){
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
}
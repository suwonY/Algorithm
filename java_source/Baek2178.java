package week_20th_thu;

import java.util.*;
public class Baek2178 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] a = new int[n][m];
		int[][] d = new int[n][m];
		boolean[][] c = new boolean[n][m];
		
		for(int i=0; i<n; i++){
			String s = in.next();
			for(int j=0; j<m; j++)
				a[i][j] = s.charAt(j)-48;
		}
		
		int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
		
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0));
		c[0][0] = true;
		d[0][0] = 1;
		
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			
			if(x==n-1 && y==m-1){
				q.clear();
				break;			//이 부분은 없어도 되는데 시간을 조금 줄여주는 부분
			}
			
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(c[nx][ny]) continue;
				if(a[nx][ny]==0) continue;
				
				c[nx][ny]=true;
				d[nx][ny] = d[x][y]+1;
				q.add(new Point(nx,ny));
			}
		}
		System.out.println(d[n-1][m-1]);
	}
	public static class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}

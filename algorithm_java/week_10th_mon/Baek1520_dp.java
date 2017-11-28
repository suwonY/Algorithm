package week_10th_mon;

import java.util.*;
public class Baek1520_dp {

	/*내리막길
	 * 상하좌우로 이동 가능
	 * 자신의 높이보다 낮은 곳을 이동하고 싶어한다
	 * 
	 * d[n][m]
	 * n,m까지 내리막길로 이동하는 경로의 수
	if(d[i-1][j] > d[i][j]) d[i][j] += d[i-1][j];
 	if(d[i+1][j] > d[i][j]) d[i][j] += d[i+1][j];
	if(d[i][j-1] > d[i][j]) d[i][j] += d[i][j-1];
	if(d[i][j+1] > d[i][j]) d[i][j] += d[i][j+1];
	 * */
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] d = new int[501][501];
	static int[][] a = new int[501][501];
	static int n, m, ans = 0;
	public static void go(){
		d[0][0] = 1;
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(i==0 && j==0) continue;

				for(int k=0; k<4; k++){
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
					if(a[i][j]>a[nx][ny]) continue;
					d[i][j] += d[nx][ny];
				}
			}
		}
	}
	public static int go(int x, int y){
		if(x==n-1 && y==m-1) return 1;
		int num = d[x][y];
		if(num!=-1) return d[x][y];
		d[x][y] = 0;
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(0<=nx && nx<n && 0<=ny && ny<m){
				if(a[nx][ny]<a[x][y]) {
					num += go(nx,ny);
				}
			}
		}
		
		return num;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				a[i][j] = in.nextInt();
				d[i][j] = -1;
			}
		}
	
		d[0][0] = 1;
		System.out.println(go(n-1,m-1));
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				System.out.print(d[i][j]+" ");
			}
			System.out.println();
		}
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

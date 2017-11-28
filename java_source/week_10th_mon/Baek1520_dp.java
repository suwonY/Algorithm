package week_10th_mon;

import java.util.*;
public class Baek1520_dp_fix_fin {

	/*내리막길
	 * 상하좌우로 이동 가능
	 * 자신의 높이보다 낮은 곳을 이동하고 싶어한다
	 * */
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] a = new int[501][501];
	static int[][] d = new int[501][501];
	static int n, m;
	public static int go(int x, int y){
		if(x==n-1 && y==m-1) return 1;
		if(d[x][y]!=-1) return d[x][y];
		
		d[x][y] = 0;
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			/*if(nx<0 || nx>=n || ny<0 || ny<=m) continue;
			if(a[x][y]<=a[nx][ny]) continue;*/
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(a[x][y]<=a[nx][ny]) continue;

			d[x][y] += go(nx,ny);
		}
		
		return d[x][y];
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
		System.out.println(go(0,0));
		in.close();
	}
}
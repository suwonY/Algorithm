import java.util.*;
public class Baek14500 {
	static int[] sx = {-1,1,0,0}, sy = {0,0,-1,1};
	static int[][] dx = {{0,0,-1},{0,-1,1},{0,0,1},{0,-1,1}};
	static int[][] dy = {{1,2,1},{1,0,0},{1,2,1},{1,1,1}};
	static int n,m,ans;
	static int[][] a = new int[501][501];
	static boolean[][] c = new boolean[501][501];
	static void go(int x, int y, int cnt, int total){
		if(cnt==4){
			ans = Math.max(ans, total);
			return;
		}
		for(int i=0; i<4; i++){
			int nx = x + sx[i];
			int ny = y + sy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(c[nx][ny]) continue;
			c[nx][ny] = true;
			go(nx,ny,cnt+1,total+a[nx][ny]);
			c[nx][ny] = false;
		}
	}
	public static void other(int x, int y){
		for(int i=0; i<4; i++){
			int total = a[x][y];
			boolean no = false;
			for(int k=0; k<3; k++){
				int nx = x + dx[i][k];
				int ny = y + dy[i][k];
				if(nx<0 || nx>=n || ny<0 || ny>=m){
					no = true;
					break;
				}
				total += a[nx][ny];
			}
			if(no) continue;
			ans = Math.max(ans, total);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				a[i][j] = in.nextInt();
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++){
				c[i][j] = true;
				go(i,j,1,a[i][j]);
				c[i][j] = false;
				other(i,j);
			}
		System.out.println(ans);
		in.close();
	}
}
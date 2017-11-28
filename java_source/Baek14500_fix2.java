package Baek_SWPractice_1st;

import java.util.*;
public class Baek14500_fix2 {

	static int n,m,ans;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[] dx1 = {0,1,1,1}, dy1 = {1,0,1,2};
	static int[] dx2 = {0,0,0,1}, dy2 = {0,1,2,1};
	static int[] dx3 = {0,1,1,2}, dy3 = {1,0,1,1};
	static int[] dx4 = {0,1,1,2}, dy4 = {0,0,1,0};
	static int[][] a = new int[501][501];
	static boolean[][] c = new boolean[501][501];
	public static void other(){
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				int sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx1[k];
					int ny = j + dy1[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx2[k];
					int ny = j + dy2[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);

				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx3[k];
					int ny = j + dy3[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);

				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx4[k];
					int ny = j + dy4[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
			}
		}
	}
	public static void go(int x, int y, int cnt, int sum){
		if(cnt==4){
			ans = Math.max(sum, ans);
			return;
		}
		
		c[x][y] = true;
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(c[nx][ny]) continue;
			c[nx][ny] = true;
			go(nx,ny,cnt+1,sum+a[nx][ny]);
			c[nx][ny] = false;
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
			for(int j=0; j<m; j++)
				go(i,j,0,0);
			
		other();
		System.out.println(ans);
		in.close();
	}
}
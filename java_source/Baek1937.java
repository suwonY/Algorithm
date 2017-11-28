package Baek_SWPractice_1st;

import java.util.*;
public class Baek1937 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,ans;
	static int[][] a = new int[501][501];
	static int[][] d = new int[501][501];
	public static int go(int x, int y){
		if(d[x][y]!=-1) return d[x][y];
		d[x][y] = 1;
		for(int k=0; k<4; k++){
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(a[nx][ny]<=a[x][y]) continue;
			d[x][y] = Math.max(d[x][y], go(nx,ny)+1);
		}
		return d[x][y];
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++){
				a[i][j] = in.nextInt();
				d[i][j] = -1;
			}
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				ans = Math.max(ans, go(i,j));
		
		System.out.println(ans);
		in.close();
	}
}
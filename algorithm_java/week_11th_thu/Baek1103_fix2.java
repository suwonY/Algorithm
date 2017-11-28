package week_11th_thu;

import java.util.*;
public class Baek1103_fix2 {
	/*∞‘¿”
	 * 
	 * ΩŒ¿Ã≈¨¿ª ª˝∞¢«ÿ¡‡æﬂµ»¥Ÿ
	 * ¿Ã∞… ∏Ù∂˙≥◊
	 * */
	static int n,m,ans=0;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] a = new int[51][51];
	static int[][] d = new int[51][51];
	static int[][] c = new int[51][51];
	static boolean[][] fin = new boolean[51][51];
	static int cnt = 0;
	public static boolean dfs(int x, int y){
		c[x][y] = cnt++;
		boolean cycle = false;
		for(int i=0; i<4; i++){
			int nx = x + dx[i]*a[x][y];
			int ny = y + dy[i]*a[x][y];
			
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(a[nx][ny]==-1) continue;
			if(c[nx][ny]==-1) dfs(nx,ny);
			if(!fin[nx][ny]) return true;

			if(cycle) return true;
		}
		fin[x][y]=true;
		return cycle;
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<m; j++){
				a[i][j]=temp.charAt(j);
				if(a[i][j]=='H')
					a[i][j]=-1;
				else
					a[i][j]-=48;
			}
		}
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				d[i][j] = c[i][j] = -1;
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println(dfs(0,0));
		
		in.close();
	}
	public static class Point{
		int x, y, cnt;
		Prev prev = null;
		public Point(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		public void pushPrev(int x, int y){
			prev = new Prev(x,y);
		}
	}
	public static class Prev{
		int x, y;
		public Prev(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}

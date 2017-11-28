package week_19th_mon;

import java.util.*;
public class swExpert1868_fix2 {
	static int n,ans,cnt;
	static int[][] a;
	static int[] dx = {-1, 0, 1,-1, 1,-1, 0, 1};
	static int[] dy = {-1,-1,-1, 0, 0, 1, 1, 1};
	static Queue<Point> z = new LinkedList<>();
	public static boolean zero(int x, int y){
		for(int i=0; i<8; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(a[nx][ny]==-1) return false;
		}
		return true;
	}
	public static void findZero(){
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(a[i][j]==-1) continue;
				if(zero(i,j)) z.add(new Point(i,j));
			}
		}
	}
	public static void go(){
		ans = z.size();
		
		while(!z.isEmpty()){
			Point now = z.remove();
			int x = now.x;
			int y = now.y;
			if(a[x][y]==1) --ans;
			a[x][y] = 1;
			for(int i=0; i<8; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if(a[nx][ny]==-1) continue;
				if(zero(nx,ny)){
					a[nx][ny] = 1;
					z.add(new Point(nx,ny));
				}
			}
		}
		System.out.println(ans);
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				if(a[i][j]==0) ++ans;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			a = new int[n][n];
			ans = cnt = 0;
			for(int i=0; i<n; i++){
				String s = in.next();
				for(int j=0; j<n; j++){
					if(s.charAt(j)=='*') {
						++cnt;
						a[i][j] = -1;
					}
				}
			}
			findZero();
			go();
			System.out.print("#"+t+" ");
			System.out.println(ans);
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
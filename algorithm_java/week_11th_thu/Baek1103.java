package week_11th_thu;

import java.util.*;
public class Baek1103 {
	/*게임
	 * 
	 * 싸이클을 생각해줘야된다
	 * 이걸 몰랐네
	 * */
	static int n,m,ans=0;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] a = new int[51][51];
	static int ex=-1, ey=-1;
	public static void go(Point now){
		int x = now.x;
		int y = now.y;
		int cnt = now.cnt;
		Point prev = now.prev;
		//종료조건
		if(x<0 || x>=n || y<0 || y>=m){
			ans = Math.max(ans, cnt);
			return;
		}
		if(a[x][y]==-1){
			ans = Math.max(ans, cnt);
			return;
		}
		for(int i=0; i<4; i++){
			int nx = x + dx[i]*a[x][y];
			int ny = y + dy[i]*a[x][y];
			
			//왔던 곳으로 다시 돌아가는건 안됨
			if(prev !=null)
				if(nx==prev.x && ny==prev.y) continue;

			Point next = new Point(nx,ny,cnt+1);
			next.pushPrev(x, y);
			go(next);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<m; j++){
				a[i][j]=temp.charAt(j);
				if(a[i][j]=='H'){
					ex=i;
					ey=j;
					a[i][j]=-1;
				}
				else
					a[i][j]-=48;
			}
		}
		go(new Point(0,0,0));
		System.out.println(ans);
		in.close();
	}
	public static class Point{
		int x, y, cnt;
		Point prev = null;
		public Point(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		public void pushPrev(int x, int y){
			prev = new Point(x,y,0);
		}
	}
}

package week_11th_thu;

import java.util.*;
public class Baek1103_fix3 {
	/*게임
	 * 
	 * 싸이클을 생각해줘야된다
	 * 이걸 몰랐네
	 * */
	static int n,m,ans=0;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] a = new int[51][51];
	static boolean[][] c = new boolean[51][51];
	/*	133
		5HH
		HHH
		21H
		-> 이 경우에 예외가 발생한다 루프를 돌지 않지만 도는것처럼 인식해버림
	*/
	public static boolean loop(){
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0));
		while(!q.isEmpty()){
			Point now = q.poll();
			int x = now.x;
			int y = now.y;
			c[x][y] = true;
			
			for(int i=0; i<4; i++){
				int nx = x + dx[i] * a[x][y];
				int ny = y + dy[i] * a[x][y];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(a[nx][ny]==-1) continue;
				if(c[nx][ny]) return true;
				q.add(new Point(nx,ny));
			}
		}
		return false;
	}
	public static boolean cycle(){
		
		
		
		return false;
	}
	public static void go(Point now){
		int x = now.x;
		int y = now.y;
		int cnt = now.cnt;
		//맵 밖으로 나갈 경우
		if(x<0 || x>=n || y<0 || y>=m){
			ans = Math.max(cnt, ans);
			return;
		}
		//구멍에 빠진 경우
		if(a[x][y]==-1){
			ans = Math.max(ans, cnt);
			return;
		}
		for(int i=0; i<4; i++){
			int nx = x + dx[i] * a[x][y];
			int ny = y + dy[i] * a[x][y];
			
			go(new Point(nx,ny,cnt+1));
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
				if(a[i][j]=='H')
					a[i][j]=-1;
				else
					a[i][j]-=48;
			}
		}
		if(loop()) System.out.println(-1);
		else {
			go(new Point(0,0,0));
			System.out.println(ans);
		}
		in.close();
	}
	public static class Point{
		int x, y, cnt;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		public Point(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}

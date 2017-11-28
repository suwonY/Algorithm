package week_12th_thu;

import java.util.*;
public class Baek1600 {
	/*말이 되고픈 원숭이
	 * 원숭이가 말처럼 움직일 수 있지만
	 * k번만 이동할 수 있고 그 다음부터는 상하좌우로 1칸 씩 움직일 수있음
	 * 
	 * 원숭이가 최소한의 동작으로 시작지점에서 도찾기점까지 갈 수 있는 방법을 구하기*/
	static final int INF = 987654321;
	static int[] hx = {-1,-1,-2,-2,1,1,2,2};
	static int[] hy = {-2,2,1,-1,2,-2,1,-1};
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,m,k;
	static int[][] a = new int[201][201];
	static int[][] d = new int[201][201];
	static boolean[][] c = new boolean[201][201];
	public static void go(){
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0,0));
		c[0][0] = true;
		d[0][0] = 0;
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			
			//말 처럼 뛸 수있는 횟수가 남아있다면
			if(cnt<k){
				for(int i=0; i<8; i++){
					int nx = x + hx[i];
					int ny = y + hy[i];
					
					if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
					if(c[nx][ny]) continue;
					if(a[nx][ny]==1) continue;
					
					d[nx][ny] = d[x][y]+1;
					c[nx][ny] = true;
					q.add(new Point(nx,ny,cnt+1));
				}
			}
			
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(c[nx][ny])continue;
				if(a[nx][ny]==1) continue;
				
				d[nx][ny] = d[x][y] + 1;
				c[nx][ny] = true;
				q.add(new Point(nx,ny,cnt));
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		k = in.nextInt();
		m = in.nextInt();
		n = in.nextInt();
		
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++){
				a[i][j] = in.nextInt();
				d[i][j] = INF;
			}
		
		go();
		if(d[n-1][m-1]==INF) System.out.println(-1);
		else System.out.println(d[n-1][m-1]);
		in.close();
	}
	public static class Point{
		int x, y, cnt;
		public Point(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}

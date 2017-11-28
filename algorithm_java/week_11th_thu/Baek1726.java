package week_11th_thu;

import java.util.*;
public class Baek1726_fix4 {

	/*·Îº¿
	 * 
	 bfs·Î Çª´Ï±ñ ¹º°¡ ¾Æ½±´Ù Ä«¿îÆÃÇÏ±â°¡
	 dfs·Î Ç®¾îºÁ¾ß°Ú´Ù
	 * */
	static int n,m;
	static int ex,ey,ed,sx,sy,sd;
	static int[] dx = {0,0,0,1,-1}, dy = {0,1,-1,0,0};
	static int[][] a = new int[101][101];
	static boolean[][][] c = new boolean[101][101][5];
	static Queue<Point> q = new LinkedList<>();
	public static int rev(int n){
		if(n==1)return 2;
		else if(n==2)return 1;
		else if(n==3)return 4;
		else return 3;
	}
	//1¿ì    2ÁÂ    3ÇÏ    4ÁÂ
	public static void go(){
		q.add(new Point(sx,sy,sd,0));
		c[sx][sy][sd] = true;
		
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			int dir = now.dir;
			int cnt = now.cnt;
			
			if(x==ex && y==ey && dir==ed){
				System.out.println(cnt);
			}
			
			for(int i=1; i<=3; i++){
				int nx = x + dx[dir] * i;
				int ny = y + dy[dir] * i;
				if(nx<0 || nx>=n || ny<0 || ny>=m) break;
				if(a[nx][ny]==1) break;
				if(c[nx][ny][dir]) continue;
				
				c[nx][ny][dir]=true;
				q.add(new Point(nx,ny,dir,cnt+1));
			}
			
			for(int i=1; i<=4; i++){
				if(i==dir || i==rev(dir)) continue;
				if(c[x][y][i]) continue;
				c[x][y][i]=true;
				q.add(new Point(x,y,i,cnt+1));
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				a[i][j] = in.nextInt();
			}
		}
		sx = in.nextInt()-1;
		sy = in.nextInt()-1;
		sd = in.nextInt();
		ex = in.nextInt()-1;
		ey = in.nextInt()-1;
		ed = in.nextInt();
		
		go();
		in.close();
	}
	public static class Point{
		int x, y, dir, cnt;
		public Point(int x, int y, int dir, int cnt){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
}

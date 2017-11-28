package Baek_SWPractice_2nd;

import java.util.*;
public class Baek9376 {

	/*Å»¿Á
	 * 
	 * µÎ¸íÀÇ ÁË¼ö¸¦ Å»¿Á½ÃÄÑ¾ß µÈ´Ù $Ç¥½Ã
	 * * º®
	 * # ¹®
	 * $ ÁË¼ö*/
	static int n,m,cnt, ans = 987654321;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] res;
	static int[][] d1,d2,d3;
	static char[][] a;
	static boolean[][] c1,c2,c3;
	static Point first,second;
	
	static Queue<Point> start = new LinkedList<>();
	public static void go(int[][] d, boolean[][] c, Point start){
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			c[x][y]=true;
			for(int k=0; k<4; k++){
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx<0 || nx>=n+2 || ny<0 || ny>=m+2) continue;
				if(c[nx][ny]) continue;
				if(a[nx][ny]=='*') continue;
				
				c[nx][ny]=true;
				if(a[nx][ny]=='#'){
					d[nx][ny]=cnt+1;
					q.add(new Point(nx,ny,cnt+1));
				}
				else{
					d[nx][ny]=cnt;
					q.add(new Point(nx,ny,cnt));
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=0; t<tc; t++){
			d1 = new int[103][103];
			d2 = new int[103][103];
			d3 = new int[103][103];
			c1 = new boolean[103][103];
			c2 = new boolean[103][103];
			c3 = new boolean[103][103];
			a = new char[103][103];
			res = new int[103][103];
			ans = 987654321;
			first=null;
			second=null;
			n = in.nextInt();
			m = in.nextInt();
			int next = 0;
			for(int i=1; i<=n; i++){
				String temp = in.next();
				for(int j=1; j<=m; j++){
					a[i][j] = temp.charAt(j-1);
					if(a[i][j]=='$'){
						if(next==0){
							first = new Point(i,j,0);
							++next;
						}
						else if(next==1){
							second = new Point(i,j,0);
							++next;
						}
					}
				}
			}
			go(d1,c1,first);
			go(d2,c2,second);
			go(d3,c3,new Point(0,0,0));
			
			for(int i=0; i<=n+1; i++){
				for(int j=0; j<=m+1; j++){
					if(a[i][j]=='*') continue;
					res[i][j] = d1[i][j]+d2[i][j]+d3[i][j];
					if(a[i][j]=='#') res[i][j]-=2;
					ans = Math.min(ans, res[i][j]);
				}
			}
			System.out.println(ans);
		}
		
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

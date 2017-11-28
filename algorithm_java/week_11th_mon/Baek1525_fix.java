package week_11th_mon;

import java.util.*;
public class Baek1525_fix {

	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] a = new int[3][3];
	static int[][] d = new int[3][3];
	//진짜 무식하게 x,y좌표에 상하좌우가 있다고 판단해보자 미친짓이다
	static boolean[][][][][][] c = new boolean[9][9][9][9][9][9];
	static int sx, sy, ans=987654321;
	public static boolean finish(int[][] a){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(i==2 && j==2) continue;
				if(a[i][j]!=d[i][j])
					return false;
			}
		}
		return true;
	}
	/*public static int up(int x, int y){
		return x-1<0?0:x-1;
	}
	public static int down(int x, int y){
		return x+1>=3?0:x+1;
	}
	public static int right(int x, int y){
		return y-1<0?0:y-1;
	}
	public static int left(int x, int y){
		return y+1>=3?0:y+1;
	}
	public static void go(){
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(sx,sy,0));
		c[sx][sy][up(sx,sy)][down(sx,sy)][left(sx,sy)][right(sx,sy)]=true;
		
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=3 || ny<0 || ny>=3) continue;
				int temp = a[x][y];
				a[x][y]=a[nx][ny];
				a[nx][ny]=temp;
				int up = up(nx,ny);
				int down = down(nx,ny);
				int left = left(nx,ny);
				int right = right(nx,ny);
				
			}
		}
	}*/
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int i=0,k=0; i<3; i++){
			for(int j=0; j<3; j++){
				a[i][j]=in.nextInt();
				d[i][j]=++k;
				if(a[i][j]==0){
					sx=i;
					sy=j;
				}
			}
		}
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				System.out.print(d[i][j]);
			}
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

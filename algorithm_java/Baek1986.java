package study_other_weeks;

import java.util.*;
public class Baek1986 {

	/*체스
	 * 
	 * Q는 가로세로대각선 모두 이동가능
	 * P는 장애물 역할
	 * K는 장애물이 있어도 움직일 수 있음(말이 움직이는 거랑 같음) */
	
	//Queen이 이동하는 qx qy
	static int[] qx = {-1,1,0,0,1,1,-1,-1};
	static int[] qy = {0,0,-1,1,-1,1,1,-1};
	static boolean[] qc;
	//Knight 이동하는 kx,ky
	static int[] kx = {2,2,-2,-2,1,1,-1,-1};
	static int[] ky = {1,-1,1,-1,2,-2,2,-2};
	static Queue<Point> q = new LinkedList<>();
	static int n,m,ans=n*m;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		int[][] a = new int[n][m];
		boolean[][] c = new boolean[n][m];

		//Queen 1
		int t = in.nextInt();
		for(int i=0; i<t; i++){
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			a[x][y] = 1;
			c[x][y] = true;
			q.add(new Point(x,y));
		}
		//Knight 2
		t = in.nextInt();
		for(int i=0; i<t; i++){
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			a[x][y] = 2;
			c[x][y] = true;
			
			for(int k=0; k<8; k++){
				int nx = x + kx[k];
				int ny = y + ky[k];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(c[nx][ny]) continue;
				c[nx][ny]=true;
			}
		}
		//Pawn 3
		t = in.nextInt();
		for(int i=0; i<t; i++){
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			a[x][y] = 3;
			c[x][y] = true;
		}
		
		while(!q.isEmpty()){
			qc = new boolean[8];
			Point queen = q.remove();
			int x = queen.x;
			int y = queen.y;
			
			int mul = 1;
			while(true){
				for(int k=0; k<8; k++){
					if(qc[k]) continue;
					int nx = x + qx[k]*mul;
					int ny = y + qy[k]*mul;
					
					if(nx<0 || nx>=n || ny<0 || ny>=m) {
						qc[k]=true;
						continue;
					}
					if(a[nx][ny]==2 || a[nx][ny]==3){
						qc[k] = true;
						continue;
					}
					
					c[nx][ny] = true;
				}
				++mul;
				
				boolean finish = true;
				for(int i=0; i<8; i++){
					if(!qc[i]) {
						finish = false;
						break;
					}
				}
				if(finish) break;
			}
		}
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(!c[i][j])
					++ans;
			}
		}
		System.out.println(ans);
		
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

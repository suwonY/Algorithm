package week_17th_thu;

import java.util.*;
public class swExpert_2105 {
	
	//				  ÁÂ»ó,ÁÂÇÏ,¿ìÇÏ,¿ì»ó
	static int[] dx = {-1,-1,1,1}, dy = {-1,1,1,-1};
	static int n,ans;
	static boolean[] c;
	static boolean[] d = new boolean[4];
	static int[][] a;
	public static void go(int sx, int sy, int x, int y, int cnt, int turn, int dir){
		if(turn>3) return;
		
		if(cnt==0){
			c[a[x][y]] = true;
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if(c[a[nx][ny]]) continue;
				
				c[a[nx][ny]]=true;
				go(sx,sy,nx,ny,cnt+1,turn,i);
				c[a[nx][ny]]=false;
			}
		}
		
		if(cnt!=0){
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if(cnt>3){
					if(nx==sx && ny==sy){
						ans = Math.max(ans, cnt+1);
						return;
					}
				}
				if(c[a[nx][ny]]) continue;
				
				c[a[nx][ny]]=true;
				if(dir==i) go(sx,sy,nx,ny,cnt+1,turn,i);
				if(dir!=i) go(sx,sy,nx,ny,cnt+1,turn+1,i);
				c[a[nx][ny]]=false;
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		int tc = in.nextInt();
//		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			a = new int[n][n];
			ans = -1;
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					a[i][j] = in.nextInt();
			
			for(int i=1; i<n; i++)
				for(int j=1; j<n; j++){
					c = new boolean[101];
					go(i,j,i,j,0,0,0);
				}
		
			System.out.println(ans);
//			System.out.println("#"+t+" "+ans);
//		}
		
		in.close();
	}
}
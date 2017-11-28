package week_11th_thu;

import java.util.*;
public class Baek1726_fix {
	static int ans = 987654321;
	static int n,m;
	static int[][] a = new int[101][101];
	static int[][] d = new int[101][101];
	static boolean[][] c = new boolean[101][101];
	static int[] dy = {1,-1,0,0}, dx = {0,0,-1,1};
	static Point start=null, end=null;
	//1우    2좌    3하    4좌
	public static int direction(int now, int next){
		if(now==next) return 0;
		if(next==1){
			if(now==2)	return 2;
			else return 1;
		}
		else if(next==2){
			if(now==1) return 2;
			else return 1;
		}
		else if(next==3){
			if(now==4) return 2;
			else return 1;
		}
		else{
			if(now==3) return 2;
			else return 1;
		}
	}
	public static void go(Point now, int total, int go){
		int x = now.x;
		int y = now.y;
		int dir = now.dir;
		if(x==end.x && y==end.y){
			if(dir==end.dir){
				ans = Math.min(ans, total);
				return;
			}
			else{
				ans = Math.min(ans, total+direction(dir,end.dir));
				return;
			}
		}
//		c[x][y]=true;
		
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(a[nx][ny]==1) continue;
//			if(c[nx][ny]) continue;
			
			//현재 방향과 가는 방향이 같으면 go명령어만 실행
			if(dir==(i+1)){
				//앞으로 가는 애는 한번만 실행하면 된다
				if(go==0)
					d[nx][ny] = d[x][y]+1;
				else
					d[nx][ny] = d[x][y];
				
				go(new Point(nx,ny,i+1),d[nx][ny],1);
				
			}
			//현재 방향과 가는 방향이 다르면 turn + go 명령어 실행
			else if(dir!=(i+1)){
				d[nx][ny] = d[x][y]+2;
				go(new Point(nx,ny,i+1),d[nx][ny],1);
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
		start = new Point(in.nextInt()-1, in.nextInt()-1, in.nextInt());
		end = new Point(in.nextInt()-1, in.nextInt()-1, in.nextInt());
		
		go(start,0,0);
		System.out.println(ans);
		in.close();
	}
	public static class Point{
		int x, y, dir;
		public Point(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}

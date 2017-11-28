package week_13th_mon;

import java.util.*;
public class Baek1938_bfs {
	static final int INF = 987654321;
	static int n, sx=-1, sy=-1, sd=-1, ex=-1, ey=-1, ed=-1, ans=INF;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[] dnx = {-1,-1,-1,0,0,1,1,1};
	static int[] dny = {-1,1,0,-1,1,-1,1,0};
	static int[][] a = new int[51][51];
	static boolean[][][] c = new boolean[51][51][2];
	public static int dir(int dir){
		return dir==1?0:1;
	}
	public static void go(){
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(sx,sy,sd,0,""));
		c[sx][sy][sd]=true;
		while(!q.isEmpty()){
			Point now = q.poll();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			int dir = now.dir;
			String temp = now.temp;
			
			if(x==ex && y==ey && dir==ed){
				System.out.println(temp);
				ans = Math.min(ans, cnt);
				continue;
			}
			
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if(c[nx][ny][dir]) continue;
				if(a[nx][ny]==1) continue;
				if(dir==0)	//가로일 때
					if(ny-1<0 || ny+1>=n || a[nx][ny-1]==1 || a[nx][ny+1]==1) continue;	//좌우가 맵 밖으로 나갈 경우
				if(dir==1)	//세로일 때
					if(nx-1<0 || nx+1>=n || a[nx-1][ny]==1 || a[nx+1][ny]==1) continue;	//위 아래가 맵 밖으로 나갈 경우
				
				c[nx][ny][dir]=true;
				q.add(new Point(nx,ny,dir,cnt+1,temp+i));
			}
			
//			if(dir==0)  //가로일 때
//				if(x-1<0 || x+1>=n || a[x-1][y]==1 || a[x+1][y]==1) continue;
//			if(dir==1)	//세로일 때
//				if(y-1<0 || y+1>=n || a[x][y-1]==1 || a[x][y+1]==1) continue;
			
			boolean possible = true;
			for(int i=0; i<8; i++){
				int nx = x + dnx[i];
				int ny = y + dny[i];
				if(nx<0 || nx>=n || ny<0 || ny>=n) {
					possible = false;
					break;
				}
				if(a[nx][ny]==1){
					possible = false;
					break;
				}
			}
			if(possible) continue;
			if(c[x][y][dir(dir)]) continue;
			
			
			c[x][y][dir(dir)]=true;
			q.add(new Point(x,y,dir(dir),cnt+1,temp+"C"));
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<n; j++){
				if(temp.charAt(j)=='0') a[i][j] = 0;
				if(temp.charAt(j)=='1') a[i][j] = 1;
				if(temp.charAt(j)=='B'){
					if(sx==-1 && sy==-1){
						sx=i;
						sy=j;
						continue;
					}
					if(sx==i && sd==-1) sd=0;	//가로(행이 같을 경우)
					if(sy==j && sd==-1) sd=1;	//세로(열이 같을 경우)
					sx+=i;
					sy+=j;
				}
				if(temp.charAt(j)=='E'){
					if(ex==-1 && ey==-1){
						ex=i;
						ey=j;
						continue;
					}
					if(ex==i && ed==-1) ed=0;	//가로(행이 같을 경우)
					if(ey==j && ed==-1) ed=1;	//세로(열이 같을 경우)
					ex+=i;
					ey+=j;
				}
			}
		}
		sx/=3; sy/=3;
		ex/=3; ey/=3;
//		System.out.println("시작점 : " + sx + "," + sy + " 방향 : " + ":" + sd + (sd==1?"세로":"가로"));
//		System.out.println("시작점 : " + ex + "," + ey + " 방향 : " + ":" + sd + (ed==1?"세로":"가로"));
		go();
		System.out.println(ans==INF?0:ans);
		
		in.close();
	}
	public static class Point{
		int x, y, dir, cnt;
		String temp;
		public Point(int x, int y, int dir, int cnt, String temp){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
			this.temp = temp;
		}
	}
}
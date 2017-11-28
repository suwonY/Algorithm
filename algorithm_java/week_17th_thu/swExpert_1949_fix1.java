package week_17th_thu;

import java.util.*;
public class swExpert_1949_fix1 {

	/*
	 가장 높은봉우리에서 시작
	 낮은지형에서 높은지형으로 가로 세로로 이동가능
	(높이가 같거나, 낮은 지형이나 대각선 방향 연결은 불가능)
	 
	 */
	static int n,k,ans;
	static int[][] a = new int[9][9];
	static boolean[][] c;
	static int[] dx = {-1,1,0,0},  dy = {0,0,-1,1};
	static List<Point> start = new ArrayList<>();
	public static void go(int x, int y, int cnt, int total, int temp, boolean used){
		ans = Math.max(ans, total);
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(c[nx][ny]) continue;
			
			//이미 한번 깎았고 깎은 값을 사용했을 때
			if(cnt==1 && used){
				if(a[nx][ny]>=a[x][y]) continue;
				c[nx][ny]=true;
				go(nx,ny,cnt,total+1,temp,false);
				c[nx][ny]=false;
				continue;
			}
			
			//이미 한번 깎아서 이제 그 값으로 비교해야될 때
			if(cnt==1 && !used){
				if(a[nx][ny]>=temp) continue;
				c[nx][ny]=true;
				go(nx,ny,cnt,total+1,temp,true);
				c[nx][ny]=true;
				continue;
			}
			
			//한번도 깎은 적이 없을 때
			if(cnt==0){
				//클 경우
				if(a[nx][ny]>=a[x][y]){
					if(a[nx][ny]-k>=a[x][y]) continue;//깎아도 안되면 ㅈㅈ
					c[nx][ny]=true;
					go(nx,ny,1,total+1,a[x][y]-1,used);
					c[nx][ny]=false;
					continue;
				}
				//작을 경우
				c[nx][ny] = true;
				go(nx,ny,cnt,total+1,temp,used);
				c[nx][ny] = false;
				continue;
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			k = in.nextInt();
			ans = 0;
			int max = 0;
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					a[i][j] = in.nextInt();
					max = Math.max(max, a[i][j]);
				}
			}
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					if(a[i][j]==max)
						start.add(new Point(i,j));
			
			for(Point s : start){
				c = new boolean[9][9];
				c[s.x][s.y]=true;
				go(s.x,s.y,0,0,0,false);
				c[s.x][s.y]=false;
			}
			
			System.out.println("#"+t+" "+ans);
		}
		
		
		in.close();
	}
	public static class Point{
		int x,y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
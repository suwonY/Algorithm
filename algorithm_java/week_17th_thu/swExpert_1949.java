package week_17th_thu;

import java.util.*;
public class swExpert_1949 {

	/*
	 가장 높은봉우리에서 시작
	 낮은지형에서 높은지형으로 가로 세로로 이동가능
	(높이가 같거나, 낮은 지형이나 대각선 방향 연결은 불가능)
	 
	 */
	static int n,k,ans;
	static int[][] a = new int[9][9];
	static boolean[][] c = new boolean[9][9];
	static int[] dx = {-1,1,0,0},  dy = {0,0,-1,1};
	static List<Point> start = new ArrayList<>();
	public static void go(int x, int y, int cnt, int total, int temp, boolean used){
		//종료조건 더이상 움직일 곳이 없을 때
		boolean finish = true;
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;//벽이거나
			
			//깎고나서 가능한지 확인하기
			if(a[nx][ny]>=a[x][y])//같거나 높은 곳일 경우
				if(cnt==0)	//깎은적이 없을 때
					if(a[nx][ny]-k>=a[x][y])//깎아도 높을 경우
						continue;
					
			finish = false;
			break;
		}
		
		if(finish){
			ans = Math.max(ans, total);
			return;
		}
		
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(c[nx][ny]) continue;
			
			//높거나 같다면
			if(a[nx][ny]>=a[x][y]){
				if(cnt==0){
					if(a[nx][ny]-k>=a[x][y]) continue;
					c[nx][ny] = true;
					go(nx,ny,cnt+1,total+1,a[x][y]-1, false);
					c[nx][ny] = false;
					continue;
				}
			}
			
			//깍은 친구로 돌고 있는 경우
			if(cnt==1 && !used){
				if(a[nx][ny]>=temp) continue;//깍은놈보다 크면 못감
				c[nx][ny]=true;
				go(nx,ny,cnt,total+1,1,true);
				c[nx][ny]=false;
				continue;
			}
			
			c[nx][ny]=true;
			go(nx,ny,cnt,total+1,temp,used);
			c[nx][ny]=false;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			System.out.println("#"+t);
		}*/
		n = in.nextInt();
		k = in.nextInt();
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
			go(s.x,s.y,0,0,0,false);
		}
		
		System.out.println(ans);
		
		
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
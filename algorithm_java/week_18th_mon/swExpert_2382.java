package week_18th_mon;

import java.util.*;
public class swExpert_2382 {

	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int n,time,k,ans;
	static Queue<Point> q = new LinkedList<>();
	static Point[][] a;
	static boolean[][] c;
	public static int opp(int num){
		if(num==0) return 1;
		else if(num==1) return 0;
		else if(num==2) return 3;
		else return 2;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*int t = in.nextInt();
		for(int tc=1; tc<=t; tc++){
			System.out.println("#"+tc+" "+ans);
		}*/
		n = in.nextInt();
		time = in.nextInt();
		k = in.nextInt();
		ans = 0;
		a = new Point[n][n];
		c = new boolean[n][n];
		
		for(int i=0; i<k; i++)
			q.add(new Point(in.nextInt(),in.nextInt(),in.nextInt()-1,in.nextInt()));
		
		for(int i=0; i<time; i++){
			
			while(!q.isEmpty()){
				Point now = q.remove();
				int x = now.x;
				int y = now.y;
				int cnt = now.cnt;
				int dir = now.dir;
				
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				//죽는대로 가면
				if(nx==0 || ny==0 || nx==n-1 || ny==n-1){
					cnt/=2;
					dir = opp(dir);
					
				}
				
			}
			
		}
		
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
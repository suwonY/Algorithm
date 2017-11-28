package week_18th_mon;

import java.util.*;
public class swExpert_1953 {
	static String[] d = {"1111","1100","0011","1001","0101","0110","1010"};
	static int n,m,cnt,end,ans;
	static int[][] a;
	static boolean[][] c;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	public static int opp(int num){
		if(num==0) return 1;
		else if(num==1) return 0;
		else if(num==2) return 3;
		else return 2;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			m = in.nextInt();
			Point hole = new Point(in.nextInt(), in.nextInt());
			end = in.nextInt();
			a = new int[n][m];
			c = new boolean[n][m];
			cnt = ans = 1;
			for(int i=0; i<n; i++)
				for(int j=0; j<m; j++)
					a[i][j] = in.nextInt();
			if(end==1) ans = 1;
			else if(end!=1){
				Queue<Point> q = new LinkedList<>();
				q.add(hole);
				c[hole.x][hole.y]=true;
				while(!q.isEmpty()){
					int size = q.size();
					for(int k=0; k<size; k++){
						Point now = q.remove();
						int x = now.x;
						int y = now.y;
						for(int i=0; i<4; i++){
							int nx = x + dx[i];
							int ny = y + dy[i];
							if(nx<0 || nx>=n || ny<0 || ny>=m || a[nx][ny]==0 || c[nx][ny]) continue;
							if(d[a[x][y]-1].charAt(i)=='0' || d[a[nx][ny]-1].charAt(opp(i))=='0') continue;
							q.add(new Point(nx,ny));
							c[nx][ny]=true;
							++ans;
						}
					}
					++cnt;
					if(cnt==end) {
						q.clear();
						break;
					}
				}
			}
			System.out.println("#"+t+" "+ans);
		}
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
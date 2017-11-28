package week_19th_mon;

import java.util.*;
public class swExpert1868_fix1 {

	static int n,ans=0,cnt;
	static int[][] a;
	static int[] dx = {-1, 0, 1,-1, 1,-1, 0, 1};
	static int[] dy = {-1,-1,-1, 0, 0, 1, 1, 1};
	public static void go(){
		while(true){
			Point start = null;
			find:
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++){
					if(a[i][j]==-1){
						start = new Point(i,j);
						break find;
					}
				}
			
			if(start==null) break;
			int x = start.x;
			int y = start.y;
			int total = 0;
			for(int i=0; i<8; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if(a[nx][ny]==-10) {
					++total;
					break;
				}
			}
			a[x][y] = 1;
			Queue<Point> q = new LinkedList<>();
			if(total==0){
				for(int i=0; i<8; i++){
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
					if(a[nx][ny]==-10 || a[nx][ny]!=-1) continue;
					q.add(new Point(nx,ny));
				}
			}
			while(!q.isEmpty()){
				Point next = q.remove();
				int x1 = next.x;
				int y1 = next.y;
				total = 0;
				for(int i=0; i<8; i++){
					int nx = x1 + dx[i];
					int ny = y1 + dy[i];
					if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
					if(a[nx][ny]==-10) {
						++total;
						break;
					}
				}
				a[x1][y1] = 1;
				if(total==0){
					for(int i=0; i<8; i++){
						int nx = x1 + dx[i];
						int ny = y1 + dy[i];
						if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
						if(a[nx][ny]==-10 || a[nx][ny]!=-1) continue;
						q.add(new Point(nx,ny));
					}
				}
			}
			print();
			++ans;
		}
	}
	public static void print(){
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			a = new int[n][n];
			ans = 0;
			for(int i=0; i<n; i++){
				String s = in.next();
				for(int j=0; j<n; j++){
					if(s.charAt(j)=='*') {
						a[i][j] = -10;
						++cnt;
					}
					else a[i][j] = -1;
				}
			}
			go();
			System.out.print("#"+t+" ");
			System.out.println(ans);
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
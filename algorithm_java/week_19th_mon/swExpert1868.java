package week_19th_mon;

import java.util.*;
public class swExpert1868 {

	static int n,ans=0,cnt;
	static int[][] a;
	static int[] dx = {-1, 0, 1,-1, 1,-1, 0, 1};
	static int[] dy = {-1,-1,-1, 0, 0, 1, 1, 1};
	static Queue<Point> z = new LinkedList<>();
	public static boolean zero(int x, int y){
		for(int i=0; i<8; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(a[nx][ny]==-10) return false;
		}
		return true;
	}
	public static void findZero(){
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(a[i][j]==-10) continue;
				if(zero(i,j)) z.add(new Point(i,j));
			}
		}
	}
	public static void go(){
		boolean first = true;
		Queue<Point> q = new LinkedList<>();
		int all = 0;
		while(!z.isEmpty()){
			int size = z.size();
			for(int k=0; k<size; k++){
				Point now = z.remove();
				int x = now.x;
				int y = now.y;
				if(a[x][y]==0 && first) {
					--ans;
					--all;
				}
				
				int total=0;
				for(int i=0; i<8; i++){
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
					if(a[nx][ny]==-10) ++total;
				}
				a[x][y] = total;
				++all;
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
						if(a[nx][ny]==-10) ++total;
					}
					a[x1][y1] = total;
					++all;
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
				if(first) ++ans;
			}
			first = false;
		}
		
		all = n*n - cnt - all;
		ans += all;
		
	}
	public static void print(){
		for(int i=0; i<n;i++){
			for(int j=0; j<n; j++){
				System.out.printf("%4d",a[i][j]);
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
			ans = cnt = 0;
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
			findZero();
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
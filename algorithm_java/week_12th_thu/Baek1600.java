package week_12th_thu;

import java.util.*;
public class Baek1600_final {
/*말이 되고픈 원숭이
반례
2
5 3
0 0 0 0 0
1 0 1 1 0
1 0 1 1 0
	 
말로 먼저 간애랑 한칸 간애랑 누가 먼저 도느냐에 따라 답이 바뀔 수 있다.
c배열을 
[][][말 사용횟수]
* */
	static final int INF = 987654321;
	static int[] hx = {-1,-1,-2,-2,1,1,2,2};
	static int[] hy = {-2,2,1,-1,2,-2,1,-1};
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,m,k,ans=INF;
	static int[][] a = new int[201][201];
	static int[][] d = new int[201][201];
	static boolean[][][] c = new boolean[201][201][31];
	public static void go(){
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0,0,0));
		c[0][0][0] = true;
		d[0][0] = 0;
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			int cnt = now.cnt;
			int total = now.total;
			
			if(x==n-1 && y == m-1){
				ans = Math.min(ans, total);
				continue;
			}
			//말 처럼 뛸 수있는 횟수가 남아있다면
			if(cnt<k){
				for(int i=0; i<8; i++){
					int nx = x + hx[i];
					int ny = y + hy[i];
					
					if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
					if(c[nx][ny][cnt+1]) continue;
					if(a[nx][ny]==1) continue;
					
					c[nx][ny][cnt+1] = true;
					q.add(new Point(nx,ny,cnt+1,total+1));
				}
			}
			
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(c[nx][ny][cnt])continue;
				if(a[nx][ny]==1) continue;
				
				c[nx][ny][cnt] = true;
				q.add(new Point(nx,ny,cnt,total+1));
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		k = in.nextInt();
		m = in.nextInt();
		n = in.nextInt();
		
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++){
				a[i][j] = in.nextInt();
			}
		
		go();
		if(ans==INF) System.out.println(-1);
		else System.out.println(ans);
		
		in.close();
	}
	public static class Point{
		int x, y, cnt, total;
		public Point(int x, int y, int cnt, int total){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.total = total;
		}
	}
}
package week_11th_mon;

import java.util.*;
public class co66_fix {

	/*마라톤 경로
	 * 
	 * 좌측 하단 (0,0)
	 * 최단경로로만 달린다
	 * m,n 필요생수통을 충족시키고 달리는 최소 고도의 길이*/
	static int n,m,k;
	static long ans;
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	static int[][] a = new int[101][101];
	static boolean[][] c;//생수통이있으면 true
	public static long min(long a, long b){
		return a<b?a:b;
	}
	public static int abs(int a, int b){
		return a-b<0?b-a:a-b;
	}
	public static void go(int x, int y, int water, long cnt){
		if(x==n && y==m && water>=k){
			ans = min(ans, cnt);
			return;
		}
		
		for(int i=0; i<2; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>n || ny>m) continue;
			if(c[nx][ny])
				go(nx,ny,++water,cnt+abs(a[nx][ny],a[x][y]));
			else if(!c[nx][ny])
				go(nx,ny,water,cnt+abs(a[nx][ny],a[x][y]));
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			m = in.nextInt();
			n = in.nextInt();
			k = in.nextInt();
			c = new boolean[101][101];
			ans = 987654321;
			for(int i=0; i<=n; i++){
				for(int j=0; j<=m; j++){
					a[i][j] = in.nextInt();
					if(a[i][j]<0){
						c[i][j]=true;
						a[i][j] = -a[i][j];
					}
				}
			}
			go(0,0,0,0);
			System.out.println("Case #"+t);
			System.out.println(ans);
		}
		in.close();
	}
}

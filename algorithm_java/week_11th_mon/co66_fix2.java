package week_11th_mon;

import java.util.*;
public class co66_fix2 {

	/*마라톤 경로
	 * 
	 * 좌측 하단 (0,0)
	 * 최단경로로만 달린다
	 * m,n 필요생수통을 충족시키고 달리는 최소 고도의 길이*/
	static int n,m,k,ans=987654321;
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	static int[][] a = new int[101][101];
	static boolean[][] c = new boolean[101][101];
	static int[][][] d = new int[101][101][11];
	public static int min(int a, int b){
		return a<b?a:b;
	}
	public static int abs(int a, int b){
		return a-b<0?b-a:a-b;
	}
	public static int dist(int a, int b){
		return Math.abs(a-b);
	}
	public static void go(){
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0,0,0));
		
		while(!q.isEmpty()){
			Point now = q.poll();
			int x = now.x;
			int y = now.y;
			int water = now.wat;
			for(int i=0; i<2; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx>n || ny>m) continue;
				if(d[nx][ny][water]!=0) continue;
				
				if(c[nx][ny]){
					d[nx][ny][water+1] = d[x][y][water]+abs(a[nx][ny],a[x][y]);
					q.add(new Point(nx,ny,water+1));
				}
				else{
					d[nx][ny][water] = d[x][y][water] + abs(a[nx][ny],a[x][y]);
					q.add(new Point(nx,ny,water));
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			System.out.println("Case #"+t);
		}*/
		m = in.nextInt();
		n = in.nextInt();
		k = in.nextInt();
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
		go();
		for(int i=k; i<11; i++){
			System.out.println("물통 : " + i+"개 일때");
			for(int j=0; j<=n; j++){
				for(int k=0; k<=m; k++){
					System.out.print(d[j][k][i]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		for(int i=0; i<11; i++){
			if(d[n][m][i]==0) continue;
			ans = Math.min(d[n][m][i], ans);
		}
		System.out.println(ans);
		in.close();
	}
	public static class Point{
		int x, y, wat;
		public Point(int x, int y, int wat){
			this.x = x;
			this.y = y;
			this.wat = wat;
		}
	}
}

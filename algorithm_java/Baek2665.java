package week_11th_thu;

import java.util.*;
public class Baek2665 {

	/*미로마들기
	 * 검은색/흰색
	 * 검은색은 벽으로 싸여있어 들어갈 수 없음
	 * 흰방 사이에는 문이 있어 지나다닐 수 있다
	 * */
	static int n;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] a = new int[51][51];
	static boolean[][] c = new boolean[51][51];
	static int[][] d = new int[51][51];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<n; j++){
				a[i][j] = temp.charAt(j)-48;
			}
		}
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				d[i][j] = -1;
			
		d[0][0] = 0;
		Queue<Point> q = new LinkedList<>();
		Queue<Point> nq = new LinkedList<>();
		q.add(new Point(0,0));
		d[0][0]=0;
		
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if(c[nx][ny]) continue;
				if(d[nx][ny]!=-1) continue;
				//벽이 없을 때
				if(a[nx][ny]==1){
					d[nx][ny] = d[x][y];
					q.add(new Point(nx,ny));
				}
				//벽이 있을 때
				else if(a[nx][ny]==0){
					d[nx][ny] = d[x][y]+1;
					nq.add(new Point(nx,ny));
				}
			}
			if(q.isEmpty()){
				q = nq;
				nq = new LinkedList<>();
			}
		}
		
		System.out.println(d[n-1][n-1]);
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

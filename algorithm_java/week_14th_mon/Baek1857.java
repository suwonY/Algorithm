package week_14th_mon;

import java.util.*;
public class Baek1857 {
	/* 발레리노
	 * m행 n열
	 * 0은 맨짱 
	 * 1은 방석
	 * 2는 돌맹이
	 * 3은 시작위치
	 * */
	static int[] dx = {1,1,2,2,-1,-1,-2,-2};
	static int[] dy = {2,-2,1,-1,2,-2,1,-1};
	static int n,m;
	static int total = 0; //total은 방법의 수 , ans는 최소 방석의 수
	static int[][] a = new int[31][31];
	static int[][] d = new int[31][31];
	static boolean[][] c = new boolean[31][31];
	static Point start = null;
	static Point end = null;
	public static void go(){
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		d[start.x][start.y]=0;
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			
			for(int i=0; i<8; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(a[nx][ny]==2) continue;
				
				if(a[nx][ny]==1){
					d[nx][ny] = d[x][y];
					q.add(new Point(nx,ny));
				}
				if(a[nx][ny]==0){
					d[nx][ny] = d[x][y]+1;
					q.add(new Point(nx,ny));
				}

				if(a[nx][ny]==4){
					//한번도 방문한 적이 없을 경우
					if(d[nx][ny]==0){
						d[nx][ny]=d[x][y];
						total = 1;
					}
					
					else{
						//기존의 방석의 수가 
						if(d[nx][ny]>d[x][y]){
							d[nx][ny]=d[x][y];
							total = 1;
						}
						else if(d[nx][ny]==d[x][y])
							++total;
						
					}
				}
				
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				a[i][j] = in.nextInt();
				if(a[i][j]==3)
					start = new Point(i,j);
				if(a[i][j]==4)
					end = new Point(i,j);
			}
		}
		
		go();
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				System.out.print(d[i][j]+" ");
			}	
			System.out.println();
		}
		
		if(d[end.x][end.y]==0)
			System.out.println(-1);
		else{
			System.out.println(d[end.x][end.y]);
			System.out.println(total);
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

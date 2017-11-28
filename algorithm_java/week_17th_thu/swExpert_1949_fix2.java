package week_17th_thu;

import java.util.*;
public class swExpert_1949_fix2 {
	/*
	 ���� �������츮���� ����
	 ������������ ������������ ���� ���η� �̵�����
	(���̰� ���ų�, ���� �����̳� �밢�� ���� ������ �Ұ���)
	 */
	static int n,k,ans;
	static int[][] a;
	static int[][] b;
	static boolean[][] c;
	static int[] dx = {-1,1,0,0},  dy = {0,0,-1,1};
	static List<Point> start;
	public static void go(int x, int y, int cnt, int total){
		ans = Math.max(ans, total);
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(c[nx][ny]) continue;
			//�̹� �ѹ� ��Ұ� ���� ���� ������� ��
			if(cnt==1){
				if(a[nx][ny]>=a[x][y]) continue;
				c[nx][ny]=true;
				go(nx,ny,cnt,total+1);
				c[nx][ny]=false;
				continue;
			}
			
			//�ѹ��� ���� ���� ���� ��
			if(cnt==0 && a[nx][ny]>=a[x][y]){
				if(a[nx][ny]-k>=a[x][y]) continue;//��Ƶ� �ȵǸ� ����
				for(int tt=1; tt<=k; tt++){
					c[nx][ny]=true;
					int num = a[nx][ny];
					a[nx][ny] = a[x][y]-1;
					go(nx,ny,1,total+1);
					c[nx][ny] = false;
					a[nx][ny] = num;
				}
				continue;
			}
			
			c[nx][ny] = true;
			go(nx,ny,cnt,total+1);
			c[nx][ny] = false;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			k = in.nextInt();
			a = new int[9][9];
			b = new int[9][9];
			start = new ArrayList<>();
			ans = 1;
			
			int max = 0;
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					a[i][j] = in.nextInt();
					max = Math.max(max, a[i][j]);
				}
			}
			b = a.clone();
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					if(a[i][j]==max)
						start.add(new Point(i,j));
			
			for(Point s : start){
				c = new boolean[9][9];
				go(s.x,s.y,0,1);
				a = b.clone();
			}
			
			System.out.println("#"+t+" "+ans);
		}
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
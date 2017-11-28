package week_17th_thu;

import java.util.*;
public class swExpert_1949 {

	/*
	 ���� �������츮���� ����
	 ������������ ������������ ���� ���η� �̵�����
	(���̰� ���ų�, ���� �����̳� �밢�� ���� ������ �Ұ���)
	 
	 */
	static int n,k,ans;
	static int[][] a = new int[9][9];
	static boolean[][] c = new boolean[9][9];
	static int[] dx = {-1,1,0,0},  dy = {0,0,-1,1};
	static List<Point> start = new ArrayList<>();
	public static void go(int x, int y, int cnt, int total, int temp, boolean used){
		//�������� ���̻� ������ ���� ���� ��
		boolean finish = true;
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;//���̰ų�
			
			//����� �������� Ȯ���ϱ�
			if(a[nx][ny]>=a[x][y])//���ų� ���� ���� ���
				if(cnt==0)	//�������� ���� ��
					if(a[nx][ny]-k>=a[x][y])//��Ƶ� ���� ���
						continue;
					
			finish = false;
			break;
		}
		
		if(finish){
			ans = Math.max(ans, total);
			return;
		}
		
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(c[nx][ny]) continue;
			
			//���ų� ���ٸ�
			if(a[nx][ny]>=a[x][y]){
				if(cnt==0){
					if(a[nx][ny]-k>=a[x][y]) continue;
					c[nx][ny] = true;
					go(nx,ny,cnt+1,total+1,a[x][y]-1, false);
					c[nx][ny] = false;
					continue;
				}
			}
			
			//���� ģ���� ���� �ִ� ���
			if(cnt==1 && !used){
				if(a[nx][ny]>=temp) continue;//�����𺸴� ũ�� ����
				c[nx][ny]=true;
				go(nx,ny,cnt,total+1,1,true);
				c[nx][ny]=false;
				continue;
			}
			
			c[nx][ny]=true;
			go(nx,ny,cnt,total+1,temp,used);
			c[nx][ny]=false;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			System.out.println("#"+t);
		}*/
		n = in.nextInt();
		k = in.nextInt();
		int max = 0;
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				a[i][j] = in.nextInt();
				max = Math.max(max, a[i][j]);
			}
		}
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				if(a[i][j]==max)
					start.add(new Point(i,j));
			
		for(Point s : start){
			c = new boolean[9][9];
			go(s.x,s.y,0,0,0,false);
		}
		
		System.out.println(ans);
		
		
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
package week_11th_thu;

import java.util.*;
public class Baek1726 {

	/*�κ�
	 * 
	 * Go k (���� ���⿡�� k��ŭ �̵�)
	 * Turn dir(�����̳� ���������� 90�� ȸ��)
	 * 0�� �̵����� 1�� �̵��Ұ���
	 * ���� ��ġ�� �ٶ󺸴� ������ ������
	 * ���ϴ� ��ġ�� �̵���Ű ���� �ּ� ���Ƚ���� ���ϴ� ���α׷�
	 * 
	 * 1. ���ϴ� ��ġ�� �̵���Ų��
	 * 2. ������ �����ش�
	 * */
	static int n,m;
	static int[][] a = new int[101][101];
	static int[][] d = new int[101][101];
	static int[] dy = {1,-1,0,0}, dx = {0,0,-1,1};
	static Point start=null, end=null;
	//1��    2��    3��    4��
	public static int direction(int now, int next){
		if(now==next) return 0;
		//�� ���ߵ� ��
		if(next==1){
			if(now==2)	return 2;
			else return 1;
		}
		else if(next==2){
			if(now==1) return 2;
			else return 1;
		}
		else if(next==3){
			if(now==4) return 2;
			else return 1;
		}
		else{
			if(now==3) return 2;
			else return 1;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				a[i][j] = in.nextInt();
			}
		}
		start = new Point(in.nextInt()-1, in.nextInt()-1, in.nextInt());
		end = new Point(in.nextInt()-1, in.nextInt()-1, in.nextInt());
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				d[i][j]=-1;
			}
		}
		//1��    2��    3��    4��
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		d[start.x][start.y]=0;
		while(!q.isEmpty()){
			int size = q.size();
			int cnt = 0;
			for(int t=0; t<size; t++){
				Point now = q.remove();
				int x = now.x;
				int y = now.y;
				int dir = now.dir;
				
				for(int i=0; i<4; i++){
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
					if(a[nx][ny]==1) continue;
					if(d[nx][ny]!=-1) continue;
					
					if(dir==i){
						d[nx][ny]= d[x][y] + cnt;
						q.add(new Point(nx,ny,i));
						
					}
					else if(dir!=i){
						d[nx][ny] = d[x][y] + cnt + direction(dir,i);
						q.add(new Point(nx,ny,i));
					}
					
				}
			}
			++cnt;
		}
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				System.out.print(d[i][j]+" ");
			}
			System.out.println();
		}
		in.close();
	}
	public static class Point{
		int x, y, dir;
		public Point(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}

package week_11th_thu;

import java.util.*;
public class Baek1726_fix3 {

	/*�κ�
	 * 
	 bfs�� Ǫ�ϱ� ���� �ƽ��� ī�����ϱⰡ
	 dfs�� Ǯ����߰ڴ�
	 * */
	static int n,m;
	static int ans = 987654321;
	static int[][] a = new int[101][101];
	static int[][] d = new int[101][101];
	static boolean[][] c = new boolean[101][101];
	static int[] dy = {1,-1,0,0}, dx = {0,0,1,-1};
	static Point start=null, end=null;
	//1��    2��    3��    4��
	public static int direction(int now, int next){
		if(now==next) return 0;
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
	public static void go(Point now, int go){
		int x = now.x;
		int y = now.y;
		int dir = now.dir;
		if(x==end.x && y==end.y){
			if(dir==end.dir){
//				System.out.println("����״�� ���� d[x][y]�� �� : " + d[x][y]);
//				System.out.println("����ȹٲ�");
//				System.out.println("���� �� : " + d[x][y]);
				ans = Math.min(d[x][y], ans);
//				d[x][y] = Math.min(d[x][y], d[end.x][end.y]);
				return;
			}
			else{
//				System.out.println("����ٲ�鼭 ���� d[x][y]�� �� : " + d[x][y]);
//				System.out.println("�������� �ٲ� ���� : " + direction(dir,end.dir));
//				System.out.println("���� �� : " + d[x][y]);
				ans = Math.min(d[x][y]+direction(dir,end.dir), ans);
//				d[x][y] = Math.min(d[x][y]/*+direction(dir,end.dir)*/, d[end.x][end.y]);
				return;
			}
		}
		c[x][y]=true;
		
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(a[nx][ny]==1) continue;
			if(c[nx][ny]) continue;
			
			//���� ����� ���� ������ ������ go��ɾ ����
			if(dir==(i+1)){
				//������ ���� �ִ� �ѹ��� �����ϸ� �ȴ�
				if(go==0)
					d[nx][ny] = d[x][y]+1;
				else if(0<go && go<3)
					d[nx][ny] = d[x][y];
				else if(go>2)
					d[nx][ny] = d[x][y]+1;
				
				/*for(int k=0; k<n; k++){
					for(int j=0; j<m; j++){
						System.out.print(d[k][j]+" ");
					}
					System.out.println();
				}
				System.out.println();*/
				go(new Point(nx,ny,i+1),go+1);
				
			}
			//���� ����� ���� ������ �ٸ��� turn + go ��ɾ� ����
			else if(dir!=(i+1)){
				d[nx][ny] = d[x][y]+2;
				
				/*for(int k=0; k<n; k++){
					for(int j=0; j<m; j++){
						System.out.print(d[k][j]+" ");
					}
					System.out.println();
				}
				System.out.println();*/
				go(new Point(nx,ny,i+1),1);
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
			}
		}
		start = new Point(in.nextInt()-1, in.nextInt()-1, in.nextInt());
		end = new Point(in.nextInt()-1, in.nextInt()-1, in.nextInt());
		
		
		go(start,0);
		/**for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				System.out.print(d[i][j]+" ");
			}
			System.out.println();
		}*/
		System.out.println(ans);
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

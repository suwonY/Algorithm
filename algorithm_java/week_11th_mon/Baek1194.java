package week_11th_mon;

import java.util.*;
public class Baek1194_fix {

	/*���� ��������
	 * 
	 * n,m
	 * . �̵�����
	 * # �� �̵� �Ұ���
	 * a~f ���� ���� ���踦 ���´�
	 * A~F ���谡 ������ �̵� ����
	 * 0 �ν��� ��ġ
	 * 1 �ⱸ
	 * �̷�Ż���ϱ�*/
	static int n,m,ans=987654321;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static char[][] a = new char[51][51];
	static boolean[][][] c = new boolean[51][51][1<<7];
	static boolean[] key = new boolean[6];
	static Point start = null;
	static List<Point> end = new ArrayList<>();
	public static void go(Point end){
		int ex = end.x;
		int ey = end.y;
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		c[start.x][start.y][start.key]=true;
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			int key = now.key;
			int cnt = now.cnt;
			
			for(int k=0; k<4; k++){
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(a[nx][ny]=='#') continue;//�� �Ÿ���
				if(c[nx][ny][key]) continue;//�ش� Ű�� ������ �湮���� �Ÿ���
				
				if(a[nx][ny]==a[ex][ey]){
					ans = Math.min(ans, cnt+1);
				}
				else if('A'<=a[nx][ny] && a[nx][ny]<='F'){
					int num = a[nx][ny]-'A';
					int temp = 1<<num;
					//Ű�� �ִٸ�
					if((key&temp)!=0){
						q.add(new Point(nx,ny,key,cnt+1));
						c[nx][ny][key]=true;
					}
				}
				else if('a'<=a[nx][ny] && a[nx][ny]<='f'){
					int num = a[nx][ny]-'a';
					int temp = 1<<num;
					//Ű�� ������
					if((key&temp)==0){
						q.add(new Point(nx,ny,key|temp,cnt+1));
						c[nx][ny][key]=true;
					}
				}
				else{
					q.add(new Point(nx,ny,key,cnt+1));
					c[nx][ny][key]=true;
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<m; j++){
				a[i][j]=temp.charAt(j);
				if(a[i][j]=='0')
					start = new Point(i,j,0,0);
				else if(a[i][j]=='1')
					end.add(new Point(i,j,0,0));
			}
		}
		for(Point e : end) go(e);
		
		System.out.println(ans);
		in.close();
	}
	public static class Point{
		int x , y, key, cnt;
		public Point(int x, int y, int key, int cnt){
			this.x = x;
			this.y = y;
			this.key = key;
			this.cnt = cnt;
		}
	}
}
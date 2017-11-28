package week_12th_thu;

import java.util.*;
public class Baek1600_fix {
	/*���� �ǰ��� ������
	 * 
	 * �ݷ�
	 2
	 5 3
	 0 0 0 0 0
	 1 0 1 1 0
	 1 0 1 1 0
	 
	 ���� ���� ���ֶ� ��ĭ ���ֶ� ���� ���� �����Ŀ� ���� ���� �ٲ� �� �ִ�.
	 c�迭�� 
	 [][][���� �Ἥ�� ��, ���� ���� �ʾƼ� �� �ֶ�� �ٱ��ָ�]
	 * */
	static final int INF = 987654321;
	static int[] hx = {-1,-1,-2,-2,1,1,2,2};
	static int[] hy = {-2,2,1,-1,2,-2,1,-1};
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,m,k,ans=INF;
	static int[][] a = new int[201][201];
	public static void go(int x, int y, int horse, int total){
		if(x==n-1 && y==m-1) {
			ans = Math.min(ans, total);
			return;
		}
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(a[nx][ny]==1) continue;
			go(nx,ny,horse,total+1);
		}
		
		if(horse<k){
			for(int i=0; i<8; i++){
				int nx = x + hx[i];
				int ny = y + hy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(a[nx][ny]==1) continue;
				go(nx,ny,horse+1,total+1);
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		k = in.nextInt();
		m = in.nextInt();
		n = in.nextInt();
		
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				a[i][j] = in.nextInt();
		
		go(0,0,0,0);
		System.out.println(ans);
		in.close();
	}
}

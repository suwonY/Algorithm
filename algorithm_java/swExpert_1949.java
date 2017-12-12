package week_17th_thu;

import java.util.*;
public class swExpert_1949_fix4 {
	/*
	 ���� �������츮���� ����
	 ������������ ������������ ���� ���η� �̵�����
	(���̰� ���ų�, ���� �����̳� �밢�� ���� ������ �Ұ���)
	 */
	static final int INF = -987654321;
	static int n,k,ans,max;
	static int[][] a,b;
	static boolean[][] c;
	static int[] dx = {-1,1,0,0},  dy = {0,0,-1,1};
	public static void go(int x, int y, int cnt, int total, int before){
		if(a[x][y]==max){
			if(cnt==1){
				ans = Math.max(total, ans);
				return;
			}
		}
		c[x][y]=true;
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(c[nx][ny]) continue;
			
			//������ ���� ��
			if(a[nx][ny]<=a[x][y]){
				if(cnt==1) continue;//�̹� ���� ���� ������ ����
				//���� ���� ���� ��
				//���� ó�� ������ ���� �ƴ϶��
				if(before!=INF){
					for(int tt=1; tt<=k; tt++){
						if(a[x][y]-tt>before && a[x][y]-tt<a[nx][ny]){
							a[x][y]-=tt;
							go(x,y,cnt+1,total,before);
							a[x][y]+=tt;
						}
					
					}
					if(a[x][y]==max) ans = Math.max(ans, total);
					continue;
				}
				if(before==INF){
					a[x][y]-=k;
					go(x,y,cnt+1,total,before);
					a[x][y]+=k;
					if(a[x][y]==max) ans = Math.max(ans, total);
					continue;
				}
			}
			c[nx][ny]=true;
			go(nx,ny,cnt,total+1,a[x][y]);
			c[nx][ny]=false;
		}
	}
	/*public static void print(int x, int y){
		System.out.println( x + "," + y + " �� ��");
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++)
				System.out.print(a[i][j]+" ");
			System.out.println();
		}
		System.out.println(ans);
		System.out.println();
	}*/
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		for(int tc=1; tc<=t; tc++){
			n = in.nextInt();
			k = in.nextInt();
			a = new int[n][n];
			b = new int[n][n];
			ans = max = 0;
			
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					a[i][j] = in.nextInt();
					max = Math.max(max, a[i][j]);
				}
			}
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++){
					c = new boolean[n][n];
					go(i,j,0,1,INF);
				}
			
			System.out.println("#"+tc+" "+ans);
		}
		
		in.close();
	}
}
package week_17th_thu;

import java.util.*;
public class swExpert_2105_fix01 {
	static int[] dx = {-1,-1,1,1}, dy = {-1,1,1,-1};
	static int n,ans;
	static boolean[] c;
	static int[][] a;
	public static void go(int start,int sx, int sy, int x, int y, int cnt, int turn, int dir){
		if(turn>3) return;	//3���̻� ������ �簢���� �ƴ�
		
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(cnt>2){	//2������ �ٽ� ���ƿ� ��� ī��Ʈ ���ټ����־ 2�̻��϶���
				if(sx==nx && sy==ny){
					ans = Math.max(ans, cnt+1);
					return;
				}
			}
			if(c[a[nx][ny]]) continue;
			
			c[a[nx][ny]]=true;
			int nturn = turn;
			if(start!=0) 	//ó�� �� ��
				if(dir!=i) 	//���������̶� ������ �ٲ�� ����
					nturn=turn+1;
			go(1,sx,sy,nx,ny,cnt+1,nturn,i);
			c[a[nx][ny]]=false;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			a = new int[n][n];
			
			ans = -1;
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					a[i][j] = in.nextInt();
			
			for(int i=1; i<n; i++)
				for(int j=1; j<n; j++){
					c = new boolean[101];
					c[a[i][j]] = true;
					go(0,i,j,i,j,0,0,0);
				}
			System.out.println("#"+t+" "+ans);
		}
		in.close();
	}
}
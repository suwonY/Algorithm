package week_13th_mon;

import java.util.*;
public class Baek1938_dfs {

	/* �볪�� �ű�� */
	static final int INF = 987654321;
	static int n, sx=-1, sy=-1, sd=-1, ex=-1, ey=-1, ed=-1, ans=INF;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] a = new int[51][51];
	//���θ� 0 ���θ� 1��� �����ϰڴ�
	static boolean[][][] c = new boolean[51][51][2];
	public static int dir(int n){
		return n==1?0:1;
	}
	public static void go(int x, int y, int cnt, int dir){
		if(x==ex && y==ey && dir==ed){
			ans = Math.min(ans, cnt);
			return;
		}
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(c[nx][ny][dir]) continue;
			if(dir==0){	//������ ��
				if(ny-1<0 || ny+1>=n) continue;	//�����̳� �������� �� ������ ���� ���
				if(a[nx][ny]==1 || a[nx][ny-1]==1 || a[nx][ny+1]==1) continue; //������ �������ϰ��
			}
			if(dir==1){	//������ ��
				if(nx-1<0 || nx+1>=n) continue;	//�����̳� �������� �� ������ ���� ���
				if(a[nx][ny]==1 || a[nx-1][ny]==1 || a[nx+1][ny]==1) continue; //������ �������ϰ��
			}
			//������ ������ ����
			c[nx][ny][dir]=true;
			go(nx,ny,cnt+1,dir);
			c[nx][ny][dir]=false;
		}
		
		if(dir==0){	//������ ��
			if(x-1>=0 && x+1<n && a[x-1][y]!=1 && a[x+1][y]!=1){ //���Ʒ��� ������ �� ���� ��
				if(!c[x][y][1]) { //���η� �ٲ� ���� ���ٸ�
					c[x][y][1]=true;
					go(x,y,cnt+1,1);
				}
			}
		}
		if(dir==1){	//������ ��
			if(y-1>=0 && y+1<n && a[x][y+1]!=1 && a[x][y-1]!=1){ //�¿� ������ �� ���� ��
				if(!c[x][y][0]) { //���η� �ٲ� ���� ���ٸ�
					c[x][y][0]=true;
					go(x,y,cnt+1,0);
				}
			}
		}
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<n; j++){
				if(temp.charAt(j)=='0') a[i][j] = 0;
				else if(temp.charAt(j)=='1') a[i][j] = 1;
				else if(temp.charAt(j)=='B'){
					if(sx==-1 && sy==-1){
						sx=i;
						sy=j;
						continue;
					}
					if(sx==i && sd==-1) sd=0;
					if(sy==j && sd==-1) sd=1;
					
					sx+=i;
					sy+=j;
				}
				else if(temp.charAt(j)=='E'){
					if(ex==-1 && ey==-1){
						ex=i;
						ey=j;
						continue;
					}
					if(ex==i && ed==-1) ed=0;
					if(ey==j && ed==-1) ed=1;
					ex+=i;
					ey+=j;
				}
			}
		}
		sx/=3; sy/=3;
		c[sx][sy][sd]=true;
		ex/=3; ey/=3;
		
		go(sx,sy,0,sd);
		System.out.println(ans==INF?-1:ans);
		
		in.close();
	}
}

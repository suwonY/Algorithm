package week_18th_mon;

import java.util.*;
public class swExpert_2382_fix3_done {
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int n,time,k,ans;
	static Point[][][] b;
	public static int opp(int num){
		if(num==0) return 1;
		else if(num==1) return 0;
		else if(num==2) return 3;
		else return 2;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int tc=1; tc<=t; tc++){
			n = in.nextInt();
			time = in.nextInt();
			k = in.nextInt();
			ans = 0;
			b = new Point[2][n][n];
			for(int i=0; i<k; i++){
				int x = in.nextInt();
				int y = in.nextInt();
				int cnt = in.nextInt();
				int dir = in.nextInt()-1;
				Point s = new Point(x,y,dir,cnt);
				b[0][x][y] = s;
			}
			for(int g=0; g<time; g++){
				for(int i=0; i<n; i++){
					for(int j=0; j<n; j++){
						if(g==0 && (i==0 || j==0)) continue;	//시작부터 셀은 검사 X
						if(b[0][i][j]==null) continue;
						Point now = b[0][i][j];
						int x = now.x;
						int y = now.y;
						int cnt = now.cnt;
						int dir = now.dir;
						int nx = x + dx[dir];
						int ny = y + dy[dir];
						if(nx==0 || nx==n-1 || ny==0 || ny==n-1){
							cnt/=2;	//개체수 반
							dir=opp(dir);	//방향 반대
						}
						if(b[1][nx][ny]!=null && cnt!=0){
							Point next = b[1][nx][ny];
							int ncnt = next.cnt;
							int max = next.max;
							int maxDir = next.maxDir;
							Point tt = null;
							//기존의 최대값보다 내께 더 크면 방향은 그대로
							if(cnt>max) {
								tt = new Point(nx,ny,dir,cnt+ncnt);
								tt.setMax(cnt, dir);
							}
							//기존 최대값이 더 클 경우
							else if(max>cnt){
								tt = new Point(nx,ny,maxDir,cnt+ncnt);
								tt.setMax(max, maxDir);
							}
							b[1][nx][ny] = tt;
							continue;
						}
						if(b[1][nx][ny]==null && cnt!=0){
							Point tt = new Point(nx,ny,dir,cnt);
							tt.setMax(cnt, dir);
							b[1][nx][ny] = tt;
							continue;
						}
					}
				}
				//0 애들을 초기화 시킴
				for(int i=0; i<n; i++)
					for(int j=0; j<n; j++){
						b[0][i][j] = null;
						Point temp = b[1][i][j];
						if(temp==null) continue;
						temp.setMax(-10, -10);
						b[0][i][j] = temp;
					}
				//1을 초기화
				for(int i=0; i<n; i++)
					for(int j=0; j<n; j++)
						b[1][i][j]=null;
			}
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					if(b[0][i][j]==null) continue;
					ans += b[0][i][j].cnt;
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
		in.close();
	}
	public static class Point{
		int x, y, dir, cnt, max, maxDir;
		public Point(int x, int y, int dir, int cnt){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
			max = maxDir = -10;
		}
		public void setMax(int max, int maxDir){
			this.max = max;
			this.maxDir = maxDir;
		}
	}
}
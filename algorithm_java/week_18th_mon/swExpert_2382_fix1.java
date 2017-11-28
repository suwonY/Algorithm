package week_18th_mon;

import java.util.*;
public class swExpert_2382_fix1 {

	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int n,time,k,ans;
	static Point[][][] b;
	public static int opp(int num){
		if(num==0) return 1;
		else if(num==1) return 0;
		else if(num==2) return 3;
		else return 2;
	}
	public static void print(){
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(b[1][i][j]!=null) System.out.print(b[1][i][j].cnt+"\t");
				else System.out.print(0+"\t");
			}
			System.out.println();
		}
		System.out.println();
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
//				System.out.println(g+"일 때");
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
						//이미 움직여서 옮겨진 친구가 있다면
						//여기 좀 고쳐야될듯  
						//만약에 3개가 동시에 합쳐질 경우 생각안했음
						if(b[1][nx][ny]!=null && cnt!=0){
							Point next = b[1][nx][ny];
							int ncnt = next.cnt;
							int ndir = next.dir;
							if(ncnt>cnt) dir = ndir;
							cnt += ncnt;
							Point tt = new Point(nx,ny,dir,cnt);
							b[1][nx][ny] = tt;
							continue;
						}
						if(b[1][nx][ny]==null && cnt!=0){
							Point tt = new Point(nx,ny,dir,cnt);
							tt.addTemp(cnt, dir);
							b[1][nx][ny] = tt;
							continue;
						}
					}
				}
				
				//0 애들을 초기화 시킴
				for(int i=0; i<n; i++)
					for(int j=0; j<n; j++){
						b[0][i][j] = null;
						b[0][i][j] = b[1][i][j];
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
		int x, y, dir, cnt;
		List<Temp> arr;
		public Point(int x, int y, int dir, int cnt){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
			arr = new ArrayList<>();
		}
		public void addTemp(int cnt, int dir){
			arr.add(new Temp(cnt,dir));
		}
	}
	public static class Temp{
		int cnt, dir;
		public Temp(int cnt, int dir){
			this.cnt = cnt;
			this.dir = dir;
		}
	}
}
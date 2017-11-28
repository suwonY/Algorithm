package week_12th_thu;

import java.util.*;
public class Baek2931 {
	/*가스관
	 * 블럭의 종류는 
	 * | 상하 연결
	 * - 좌우 연결
	 * + 상하좌우 연결
	 * 1 밑 - 오른쪽 연결	┌
	 * 2 위 - 오른쪽 연결 	└
	 * 3 왼쪽 - 위 연결 	┘
	 * 4 왼쪽 - 아래 연결	┐
	 * 
	 * M은 모스크바
	 * Z 자그레브
	 * 
	 * 블럭을 하나를 채워서 가스의 흐름이 유일하게 만들기
	 * */
	static int n,m;
	static int[] sdx = {-2,2,0,0,1,1,-1,-1};
	static int[] sdy = {0,0,2,-2,1,-1,1,-1};
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static String[] p = {"1111","1100","0011","0101","1001","1010","0110"};
	static char[][] a = new char[26][26];
	static int[][] d = new int[26][26];
	static boolean[][] c = new boolean[26][26];
	static int sx,sy,ax,ay;
	public static int pipe(char c){
		if(c=='+') return 0;
		else if(c=='|') return 1;
		else if(c=='-') return 2;
		else if(c=='1') return 3;
		else if(c=='2') return 4;
		else if(c=='3') return 5;
		else return 6;
	}
	public static int opposite(int n){
		if(n==0) return 1;
		else if(n==1) return 0;
		else if(n==2) return 3;
		return 2;
	}
	public static void print(int num){
		if(num==0) System.out.println("+");
		if(num==1) System.out.println("|");
		if(num==2) System.out.println("-");
		if(num==3) System.out.println("1");
		if(num==4) System.out.println("2");
		if(num==5) System.out.println("3");
		if(num==6) System.out.println("4");
	}
	public static int check(int x, int y, int nx, int ny){
		String com = p[pipe(a[nx][ny])];
		int dnx = nx - x;
		int dny = ny - y;
		int dir = 0;
		//남의놈이 상하좌우 중 어디에 있는 놈인지 확인하기
		for(int i=0; i<4; i++){
			if(dnx==dx[i] && dny==dy[i]){
				dir=opposite(i);
				break;
			}
		}
		if(com.charAt(dir)=='1')
			return 1;
		else return 0;
	}
	public static void go(Point start){
		Queue<Point> q = new LinkedList<>();

		for(int i=0; i<4; i++){
			int nx = start.x + dx[i];
			int ny = start.y + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(a[nx][ny]=='.') continue;
			q.add(new Point(nx,ny));
			c[nx][ny]=true;
			break;
		}
		String temp = "";
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			c[x][y]=true;
			//가스관이 연결되서 갈 수 있는 곳을 확인하기 위해서
			String pos = p[pipe(a[x][y])];
			for(int i=0; i<4; i++){
				//확인할 필요없는 곳은 확인하지않는다.
				if(pos.charAt(i)=='0') continue;
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(a[nx][ny]=='Z' || a[nx][ny]=='M') continue;
				if(c[nx][ny])continue;
				if(a[nx][ny]!='.'){
					q.add(new Point(nx,ny));
				}
				else if(a[nx][ny]=='.'){
					ax = nx;
					ay = ny;
					q.clear();
					break;
				}
			}
		}
		//빈 칸을 찾아냈음 빈칸에서 상하좌우중에 연결해줘야되는 부분을 찾는다
		for(int i=0; i<4; i++){
			int nx = ax + dx[i];
			int ny = ay + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) {
				temp+="0";
				continue;
			}
			if(a[nx][ny]=='.' || a[nx][ny]=='Z' || a[nx][ny]=='M'){
				temp+="0";
				continue;
			}
			temp+=check(ax,ay,nx,ny);
		}
		System.out.print((ax+1)+" "+(ay+1)+" ");
		for(int i=0; i<7; i++){
			if(p[i].equals(temp)){
				print(i);
				return;
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
				a[i][j] = temp.charAt(j);
				if(a[i][j]=='M'){
					sx=i;sy=j;
				}
			}
		}
		go(new Point(sx,sy));
		in.close();
	}
	public static class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
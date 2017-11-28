package pracAlgorithm;

import java.util.*;
public class Baek2931 {
	static int n,m;
	static int[] sdx = {-2,2,0,0,1,1,-1,-1};
	static int[] sdy = {0,0,2,-2,1,-1,1,-1};
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static String[] p = {"1111","1100","0011","0101","1001","1010","0110"};
	static char[][] a = new char[26][26];				
	static int[][] d = new int[26][26];
	static boolean[][] c = new boolean[26][26];
	static int sx,sy,ex,ey,ax,ay;
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
	public static void go(){
		Queue<Point> q = new LinkedList<>();
		for(int i=0; i<4; i++){
			int nx = sx + dx[i];
			int ny = sy + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(a[nx][ny]=='.') continue;
			q.add(new Point(nx,ny));
			c[nx][ny]=true;
			break;
		}
		if(q.size()==0){
			for(int i=0; i<4; i++){
				int nx = ex + dx[i];
				int ny = ey + dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(a[nx][ny]=='.') continue;
				q.add(new Point(nx,ny));
				c[nx][ny]=true;
				break;
			}
		}
		String temp = "";
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			c[x][y]=true;
			String pos = p[pipe(a[x][y])];
			for(int i=0; i<4; i++){
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
		for(int i=0; i<4; i++){
			int nx = ax + dx[i];
			int ny = ay + dy[i];
			if(nx<0 || nx>=n || ny<0 || ny>=m || a[nx][ny]=='.') {
				temp+="0";
				continue;
			}
			if(a[nx][ny]=='Z' || a[nx][ny]=='M'){
				temp+="1";
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
				if(a[i][j]=='Z'){
					ex=i;ey=i;
				}
			}
		}
		go();
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
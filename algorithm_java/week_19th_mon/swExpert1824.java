package week_19th_mon;

import java.util.*;
public class swExpert1824_fix1 {
	static int n,m,ans;
	static char[][] a;
	static boolean[][][][] c;
	public static int dir(char a){
		if(a=='^') return 0;
		else if(a=='v') return 1;
		else if(a=='<') return 2;
		else return 3;
	}
	public static int sum(int memory){
		++memory;
		if(memory==16) memory=0;
		return memory;
	}
	public static int sub(int memory){
		--memory;
		if(memory==-1)memory=15;
		return memory;
	}
	public static Point change(Point now, char dir){
		int x = now.x;
		int y = now.y;
		if(dir=='<'){
			if(y==0) y=m-1;
			else --y;
			return new Point(x,y);
		}
		else if(dir=='>'){
			if(y==m-1) y=0;
			else ++y;
			return new Point(x,y);
		}
		else if(dir=='v'){
			if(x==n-1) x=0;
			else ++x;
			return new Point(x,y);
		}
		else{
			if(x==0) x=n-1;
			else --x;
			return new Point(x,y);
		}
	}
	public static void go(){
		Queue<Point> q = new LinkedList<>();
		int mem = 0;
		ans = -1;
		char dir = '>';
		if(0<=a[0][0]-48 && a[0][0]-48<=9) mem = a[0][0]-48;
		if(a[0][0]=='>' || a[0][0]=='<' || a[0][0]=='^' || a[0][0]=='v')
			dir = a[0][0];
		c[0][0][mem][dir(dir)]=true;
		Point start = new Point(0,0);
		start.memory = mem;
		start.dir = dir;
		q.add(start);
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			if(a[x][y]=='@'){
				ans = 0;
				q.clear();
				return;
			}
			char ndir = now.dir;
			int memory = now.memory;
			
			Point next = null;
			if(a[x][y]=='?'){
				ndir = '>';
				next = change(now, ndir);
				next.dir = ndir;
				if(!c[next.x][next.y][memory][dir(ndir)]) {
					q.add(next);
					c[next.x][next.y][memory][dir(ndir)]=true;
				}
					
				ndir = '<';
				next = change(now, ndir);
				next.dir = ndir;
				if(!c[next.x][next.y][memory][dir(ndir)]) {
					q.add(next);
					c[next.x][next.y][memory][dir(ndir)]=true;
				}
				
				ndir = '^';
				next = change(now, ndir);
				next.dir = ndir;
				if(!c[next.x][next.y][memory][dir(ndir)]) {
					q.add(next);
					c[next.x][next.y][memory][dir(ndir)]=true;
				}
				
				ndir = 'v';
				next = change(now, ndir);
				next.dir = ndir;
				if(!c[next.x][next.y][memory][dir(ndir)]) {
					q.add(next);
					c[next.x][next.y][memory][dir(ndir)]=true;
				}
				
				continue;
			}
			if(a[x][y]=='>') ndir = '>';
			if(a[x][y]=='<') ndir = '<';
			if(a[x][y]=='v') ndir = 'v';
			if(a[x][y]=='^') ndir = '^';
			if(a[x][y]=='_'){
				if(memory==0) ndir = '>';
				else ndir = '<';
			}
			if(a[x][y]=='|'){
				if(memory==0) ndir = 'v';
				else ndir = '^';
			}
			if(0<=a[x][y]-48 && a[x][y]-48<=9) memory = a[x][y]-48;
			if(a[x][y]=='+') memory = sum(memory);
			if(a[x][y]=='-') memory = sub(memory);
			
			next = change(now,ndir);
			next.memory = memory;
			next.dir = ndir;
			if(!c[next.x][next.y][memory][dir(ndir)]){
				q.add(next);
				c[next.x][next.y][memory][dir(ndir)]=true;
			}
		}
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			boolean not = true;
			n = in.nextInt();
			m = in.nextInt();
			a = new char[n][m];
			c = new boolean[n][m][16][4];
			ans=-1;
			for(int i=0; i<n; i++){
				String temp = in.next();
				for(int j=0; j<m; j++){
					a[i][j] = temp.charAt(j);
					if(a[i][j]=='@' && not) not = false;
				}
			}
			if(not) {
				System.out.println("#"+t+" "+"NO");
				continue;
			}
			go();
			System.out.print("#"+t+" ");
			if(ans==-1) System.out.println("NO");
			if(ans==0) System.out.println("YES");
		}
		
		
		in.close();
	}
	public static class Point{
		int x, y, memory;
		char dir;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
			dir = '>';
			memory = 0;
		}
	}
}
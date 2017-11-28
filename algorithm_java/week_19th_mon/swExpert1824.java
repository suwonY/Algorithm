package week_19th_mon;

import java.util.*;
public class swExpert1824 {

	/*
	 문자를 처리하고 이동방향에 따라 다음 문자로 이동해야 한다
	 가장 처음 위치는 제일 왼쪽 위에 문자고 오른족으로 이동
	 이동방향이 상하좌우로 바뀔 수 있다
	 만약에 맵 밖으로 나가면 반대편으로 이동
	 
	 화살표는 이동방향
	 - 메모리에 0이 저장되있으면 오른쪽으로, 아니면 왼쪽
	 | 0이 저장되있으면 아래쪽으로 아니면 위로
	 ? 4방향 랜덤하게 바꿈
	 . 아무것도 하지 않음
	 @ 실행을 정지
	 
	 0~9 메모리에 문자가 나타내는 값을 저장한다
	 + 메모리에 저장된 값을 1로 더한다(15면 -> 0으로 바꾸기)
	 - 메모리에 저장된 값을 1로 뺀다(0-> 15)
	 * */
	static int n,m,ans,memory;
	static char dir;
	static char[][] a;
	static boolean[][][][] c;
	public static int dir(char a){
		if(a=='^') return 0;
		else if(a=='v') return 1;
		else if(a=='<') return 2;
		else return 3;
	}
	public static void sum(){
		++memory;
		if(memory==16) memory=0;
	}
	public static void sub(){
		--memory;
		if(memory==-1)memory=15;
	}
	public static Point change(Point now){
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
	public static void go(Point now){
		int x = now.x;
		int y = now.y;
//		System.out.println("현재 좌표 : " + x + " " + y + " 현재 방향 : " + dir);
		c[x][y][memory][dir(dir)] = true;
		if(a[x][y]=='@'){
			ans = 0;
			return;
		}
		Point next = null;
		if(a[x][y]=='?'){
			dir = '>';
			next = change(now);
			if(!c[next.x][next.y][memory][dir(dir)]) go(next);
			
			dir = '<';
			next = change(now);
			if(!c[next.x][next.y][memory][dir(dir)]) go(next);
			
			dir = '^';
			next = change(now);
			if(!c[next.x][next.y][memory][dir(dir)]) go(next);
			
			dir = 'v';
			next = change(now);
			if(!c[next.x][next.y][memory][dir(dir)]) go(next);
			
			return;
		}

		if(a[x][y]=='>') dir = '>';
		if(a[x][y]=='<') dir = '<';
		if(a[x][y]=='v') dir = 'v';
		if(a[x][y]=='^') dir = '^';
		if(a[x][y]=='_'){
			if(memory==0) dir = '>';
			else dir = '<';
		}
		if(a[x][y]=='|'){
			if(memory==0) dir = 'v';
			else dir = '^';
		}
		if(0<=a[x][y]-48 && a[x][y]-48<=9) memory = a[x][y]-48;
		if(a[x][y]=='+') sum();
		if(a[x][y]=='-') sub();
		
		next = change(now);
		if(!c[next.x][next.y][memory][dir(dir)]) go(next);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			m = in.nextInt();
			a = new char[n][m];
			c = new boolean[n][m][16][4];
			ans=-1;
			dir='>';
			memory = 0;
			for(int i=0; i<n; i++){
				String temp = in.next();
				for(int j=0; j<m; j++)
					a[i][j] = temp.charAt(j);
			}
			go(new Point(0,0));
			System.out.print("#"+t+" ");
			if(ans==-1) System.out.println("NO");
			if(ans==0) System.out.println("YES");
		}
		
		
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
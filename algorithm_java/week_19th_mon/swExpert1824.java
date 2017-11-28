package week_19th_mon;

import java.util.*;
public class swExpert1824 {

	/*
	 ���ڸ� ó���ϰ� �̵����⿡ ���� ���� ���ڷ� �̵��ؾ� �Ѵ�
	 ���� ó�� ��ġ�� ���� ���� ���� ���ڰ� ���������� �̵�
	 �̵������� �����¿�� �ٲ� �� �ִ�
	 ���࿡ �� ������ ������ �ݴ������� �̵�
	 
	 ȭ��ǥ�� �̵�����
	 - �޸𸮿� 0�� ����������� ����������, �ƴϸ� ����
	 | 0�� ����������� �Ʒ������� �ƴϸ� ����
	 ? 4���� �����ϰ� �ٲ�
	 . �ƹ��͵� ���� ����
	 @ ������ ����
	 
	 0~9 �޸𸮿� ���ڰ� ��Ÿ���� ���� �����Ѵ�
	 + �޸𸮿� ����� ���� 1�� ���Ѵ�(15�� -> 0���� �ٲٱ�)
	 - �޸𸮿� ����� ���� 1�� ����(0-> 15)
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
//		System.out.println("���� ��ǥ : " + x + " " + y + " ���� ���� : " + dir);
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
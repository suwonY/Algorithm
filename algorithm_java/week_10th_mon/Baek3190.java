package week_10th_mon;

import java.util.*;
public class Baek3190 {

	/*뱀
	 * n*n
	 * 사과가 놓여짐
	 * 뱀의 길이는 1 맨위 좌측에 존내
	 * 다음칸 이동시에 몸이 늘어나고 머리가 다음칸으로 이동
	 * 사과가 있으면 길이가 늘어나고 사과가 없으면 길이는 그대로
	 * 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초 후에 끝나는지 계산하기
	 * 벽이나 자기자신의 몸에 부딪히면 게임이 끝난다.*/
	static int n, k, l, time=0, dir=4, hx, hy;
	static Point head;
	static Snake snake;
	static int[][] a = new int[101][101];
	static char[] t = new char[101];
	static boolean[][] c = new boolean[101][101];
	//dir은 뱀의 방향으로 U D R L로 정하자
	public static void change(char where){
		if(t[time]!='L' && t[time]!='D') return;
		
		//상1	하2	   좌3	        우4 라고 생각을 하면 
		//상일때 좌로 돌면 좌 우로돌면 우
		if(dir==1){
			if(where=='L') dir=3;
			else dir=4;
		}
		//하일때 좌로 돌면 우 우로돌면 좌
		else if(dir==2){
			if(where=='L') dir=4;
			else dir=3;
		}
		//좌일때 좌로돌면 하 우로돌면 상
		else if(dir==3){
			if(where=='L') dir=2;
			else dir=1;
		}
		else{
			if(where=='L') dir=1;
			else dir=2;
		}
	}
	public static boolean moveUp(){
		if(hx-1<0) return true;//지도 밖으로 나가면 게임 끝
		if(a[hx-1][hy]==1) return true; //내 몸뚱이에 부딪히면 게임 끝

		//사과가있다면
		if(c[hx-1][hy]){
			//원래 좌표는 꼬리로 붙인다
			snake.push(new Point(hx,hy,dir));
			//머리를 바꿔준다
			--hx;
			a[hx][hy]=1;
			change(t[time]);
			//1. 만약에 이 시간에 방향이 바뀐다면
			snake.changeHead(hx, hy, dir);
		}
		//사과가 없다면
		else if(!c[hx-1][hy]){
			a[hx][hy]=0;
			--hx;
			a[hx][hy]=1;
			change(t[time]);
			snake.changeHead(hx, hy, dir);
		}
		
		return false;
	}
	public static boolean moveDown(){
		if(hx+1>=n) return true;//지도 밖으로 나가면 게임 끝
		if(a[hx+1][hy]==1) return true; //내 몸뚱이에 부딪히면 게임 끝

		//사과가있다면
		if(c[hx+1][hy]){
			//원래 좌표는 꼬리로 붙인다
			snake.push(new Point(hx,hy,dir));
			//머리를 바꿔준다
			++hx;
			a[hx][hy]=1;
			change(t[time]);
			//1. 만약에 이 시간에 방향이 바뀐다면
			snake.changeHead(hx, hy, dir);
		}
		//사과가 없다면
		else if(!c[hx+1][hy]){
			a[hx][hy]=0;
			++hx;
			a[hx][hy]=1;
			change(t[time]);
			snake.changeHead(hx, hy, dir);
		}
		
		return false;
	}
	public static boolean moveLeft(){
		if(hy-1<0) return true;//지도 밖으로 나가면 게임 끝
		if(a[hx][hy-1]==1) return true; //내 몸뚱이에 부딪히면 게임 끝

		//사과가있다면
		if(c[hx][hy-1]){
			//원래 좌표는 꼬리로 붙인다
			snake.push(new Point(hx,hy,dir));
			
			//머리를 바꿔준다
			--hy;
			a[hx][hy]=1;
			change(t[time]);
			//1. 만약에 이 시간에 방향이 바뀐다면
			snake.changeHead(hx, hy, dir);
		}
		//사과가 없다면
		else if(!c[hx][hy-1]){
			a[hx][hy]=0;
			--hy;
			a[hx][hy]=1;
			change(t[time]);
			snake.changeHead(hx, hy, dir);
		}
		
		return false;
	}
	public static boolean moveRight(){
		if(hy+1>=n) return true;//지도 밖으로 나가면 게임 끝
		if(a[hx][hy+1]==1) return true; //내 몸뚱이에 부딪히면 게임 끝

		//사과가있다면
		if(c[hx][hy+1]){
			//원래 좌표는 꼬리로 붙인다
			snake.push(new Point(hx,hy,dir));
			
			//머리를 바꿔준다
			++hy;
			a[hx][hy]=1;
			change(t[time]);
			//1. 만약에 이 시간에 방향이 바뀐다면
			snake.changeHead(hx, hy, dir);
		}
		//사과가 없다면
		else if(!c[hx][hy+1]){
			a[hx][hy]=0;
			++hy;
			a[hx][hy]=1;
			change(t[time]);
			snake.changeHead(hx, hy, dir);
		}
		
		return false;
	}
	public static void print(){
		System.out.println("time: " +time);
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void go(){
		boolean finish = false;
		//상1 하2 좌3 우4
		while(!finish){
			switch(dir){
			case 1:
				finish = moveUp();
				System.out.println("up");
				print();
				++time;
				break;
			case 2:
				finish = moveDown();
				System.out.println("down");
				print();
				++time;
				break;
			case 3:
				finish = moveLeft();
				System.out.println("left");
				print();
				++time;
				break;
			case 4:
				finish = moveRight();
				System.out.println("right");
				print();
				++time;
				break;
			}
		}
		System.out.println(time);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		for(int i=0; i<k; i++){
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			c[x][y] = true;	//사과
		}
		l = in.nextInt();
		int x = 0;
		for(int i=0; i<l; i++){
			x = in.nextInt();
			String temp = in.next();
			t[x]=temp.charAt(0);
		}
		
		a[0][0] = 1;
		snake = new Snake();
		head = snake.head;
		hx = head.x;
		hy = head.y;
		
		go();
		in.close();
	}
	public static class Snake{
		int dir, length, index;
		Point head = new Point(0,0,4);
		List<Point> p = new LinkedList<>();;
		public Snake(){
			p.add(head);
			dir = 4;
			length = 1;
			index = 1;
		}
		public void push(Point num){
			int x = num.x;
			int y = num.y;
			Point front = p.get(index-1); //자신의 앞에꺼의 정보를 받아 저장해둠
			int fx = front.x;
			int fy = front.y;
			int fdir = front.dir;
			p.add(new Point(x,y,dir,fx,fy,fdir));
			++index;
		}
		public void changeHead(int x, int y, int dir){
			head = new Point(x,y,dir);
		}
		public void chageTails(){
			for(int i=index-1; i>0; i--){
				
			}
		}
	}
	public static class Point{
		int x, y, dir;
		Point front = null;
		public Point(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		public Point(int x, int y, int dir, int fx, int fy, int fdir){
			this.x = x;
			this.y = y;
			front = new Point(fx,fy,fdir);
			this.dir = dir;
		}
	}
}

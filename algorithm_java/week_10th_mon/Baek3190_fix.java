package week_10th_mon;

import java.util.*;
public class Baek3190_fix {

	static int n, k, l, time=0, dir=4, hx=0, hy=0;
	static Deque<Point> tails = new ArrayDeque<>();
	static int[][] a = new int[101][101];
	static char[] t = new char[10001];
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

		--hx;
		//사과가있다면 유지
		if(a[hx][hy]==2){
			a[hx][hy]=1;
			tails.addFirst(new Point(hx,hy));//머리를 추가해준다
		}
		//사과가없다면 꼬리를 줄여준다
		else if(a[hx][hy]==0){
			a[hx][hy] = 1;
			//머리를 추가하고 꼬리를 뺀다
			tails.addFirst(new Point(hx,hy));
			Point last = tails.removeLast();
			a[last.x][last.y] = 0;
		}
		++time;
		change(t[time]);
		
		return false;
	}
	public static boolean moveDown(){
		if(hx+1>n) return true;//지도 밖으로 나가면 게임 끝
		if(a[hx+1][hy]==1) return true; //내 몸뚱이에 부딪히면 게임 끝

		++hx;
		//사과가있다면 유지
		if(a[hx][hy]==2){
			a[hx][hy]=1;
			tails.addFirst(new Point(hx,hy));//머리를 추가해준다
		}
		//사과가없다면 꼬리를 줄여준다
		else if(a[hx][hy]==0){
			a[hx][hy] = 1;
			//머리를 추가하고 꼬리를 뺀다
			tails.addFirst(new Point(hx,hy));
			Point last = tails.removeLast();
			a[last.x][last.y] = 0;
		}
		++time;
		change(t[time]);
		
		return false;
	}
	public static boolean moveLeft(){
		if(hy-1<0) return true;//지도 밖으로 나가면 게임 끝
		if(a[hx][hy-1]==1) return true; //내 몸뚱이에 부딪히면 게임 끝

		--hy;
		//사과가있다면 유지
		if(a[hx][hy]==2){
			a[hx][hy]=1;
			tails.addFirst(new Point(hx,hy));//머리를 추가해준다
		}
		//사과가없다면 꼬리를 줄여준다
		else if(a[hx][hy]==0){
			a[hx][hy] = 1;
			//머리를 추가하고 꼬리를 뺀다
			tails.addFirst(new Point(hx,hy));
			Point last = tails.removeLast();
			a[last.x][last.y] = 0;
		}
		++time;
		change(t[time]);
		
		return false;
	}
	public static boolean moveRight(){
		if(hy+1>n) return true;//지도 밖으로 나가면 게임 끝
		if(a[hx][hy+1]==1) return true; //내 몸뚱이에 부딪히면 게임 끝

		++hy;
		//사과가있다면 유지
		if(a[hx][hy]==2){
			a[hx][hy]=1;
			tails.addFirst(new Point(hx,hy));//머리를 추가해준다
		}
		//사과가없다면 꼬리를 줄여준다
		else if(a[hx][hy]==0){
			a[hx][hy] = 1;
			//머리를 추가하고 꼬리를 뺀다
			tails.addFirst(new Point(hx,hy));
			Point last = tails.removeLast();
			a[last.x][last.y] = 0;
		}
		++time;
		change(t[time]);
		
		return false;
	}
	public static void go(){
		boolean finish = false;
		//상1 하2 좌3 우4
		while(!finish){
			switch(dir){
			case 1:
				finish = moveUp();
				break;
			case 2:
				finish = moveDown();
				break;
			case 3:
				finish = moveLeft();
				break;
			case 4:
				finish = moveRight();
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
			a[x][y] = 2;	//사과
		}
		l = in.nextInt();
		int x = 0;
		for(int i=0; i<l; i++){
			x += in.nextInt();
			String temp = in.next();
			t[x]=temp.charAt(0);
		}
		
		a[0][0] = 1;
		tails.addFirst(new Point(0,0));
		
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

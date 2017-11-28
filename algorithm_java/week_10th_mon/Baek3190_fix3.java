package week_10th_mon;

import java.util.*;
public class Baek3190_fix3 {
	
	/*뱀
	 * 사과가 잇으면 몸이 늘어나고
	 * 사과가 없으면 몸이 줄어든다
	 * 
	 * 1.상  2.하  3..좌   4.우
	 * */
	static int n,k,ans=0,headX=0,headY=0,dir=4;
	static boolean finish = false;
	static int[][] a = new int[101][101];
	static char[] change = new char[101];
	static Deque<Point> tails = new ArrayDeque<>();
	public static void change(){
		if(change[ans]!='L' && change[ans]!='D') return;
		if(dir==1){ //상
			if(change[ans]=='L') dir=3;
			else dir=4;
		}
		else if(dir==2){ //하
			if(change[ans]=='L') dir=4;
			else dir=3;
		}
		else if(dir==3){ //좌
			if(change[ans]=='L') dir=2;
			else dir=1;
		}
		else if(dir==4){  //우
			if(change[ans]=='L') dir=1;
			else dir=2;
		}
	}
	public static void moveUp(){
		--headX;
		if(headX<0 || headX>=n || headY<0 || headY>=n){
			finish = true;
			return;
		}
		if(a[headX][headY]==1){
			finish=true;
			return;
		}
		
		//사과가 있을 경우
		if(a[headX][headY]==2){
			//꼬리로 늘린다
			tails.addLast(new Point(headX+1,headY));
			a[headX+1][headY]=1;
		}
		else if(a[headX][headY]!=2){
			a[headX+1][headY]=0;
			//꼬리를 땡긴다(마지막 꼬리를 원래의머리위치의 맨 앞 꼬리로 이동시킨다)
			if(!tails.isEmpty()){
				Point last = tails.removeFirst();
				a[last.x][last.y]=0;
				tails.addLast(new Point(headX+1,headY));
				a[headX+1][headY]=1;
			}
		}
		a[headX][headY]=3;
	}
	public static void moveDown(){
		++headX;
		if(headX<0 || headX>=n || headY<0 || headY>=n){
			finish = true;
			return;
		}
		if(a[headX][headY]==1){
			finish = true;
			return;
		}
		
		//사과가 있을 경우
		if(a[headX][headY]==2){
			//머리지점을 꼬리로 늘린다
			tails.addLast(new Point(headX-1,headY));
			a[headX-1][headY]=1;
		}
		else if(a[headX][headY]!=2){
			a[headX-1][headY]=0;
			//꼬리를 땡긴다(마지막 꼬리를 원래의머리위치의 맨 앞 꼬리로 이동시킨다)
			if(!tails.isEmpty()){
				Point last = tails.removeFirst();
				a[last.x][last.y]=0;
				tails.addLast(new Point(headX-1,headY));
				a[headX-1][headY]=1;
			}
		}
		a[headX][headY]=3;
	}
	public static void moveLeft(){
		--headY;
		if(headX<0 || headX>=n || headY<0 || headY>=n){
			finish = true;
			return;
		}
		if(a[headX][headY]==1){
			finish = true;
			return;
		}
		
		//사과가 있을 경우
		if(a[headX][headY]==2){
			tails.addLast(new Point(headX,headY+1));
			//머리지점을 꼬리로 늘린다
			a[headX][headY+1]=1;
		}
		else if(a[headX][headY]!=2){
			a[headX][headY+1]=0;
			//꼬리를 땡긴다(마지막 꼬리를 원래의머리위치의 맨 앞 꼬리로 이동시킨다)
			if(!tails.isEmpty()){
				Point last = tails.removeFirst();
				a[last.x][last.y]=0;
				tails.addLast(new Point(headX,headY+1));
				a[headX][headY+1]=1;
			}
		}
		a[headX][headY]=3;
	}
	public static void moveRight(){
		++headY;
		if(headX<0 || headX>=n || headY<0 || headY>=n){
			finish = true;
			return;
		}
		if(a[headX][headY]==1){
			finish = true;
			return;
		}
		
		//사과가 있을 경우
		if(a[headX][headY]==2){
			//머리지점을 꼬리로 늘린다
			tails.addLast(new Point(headX,headY-1));
			a[headX][headY-1]=1;
		}
		else if(a[headX][headY]!=2){
			a[headX][headY-1]=0;
			//꼬리를 땡긴다(마지막 꼬리를 원래의머리위치의 맨 앞 꼬리로 이동시킨다)
			if(!tails.isEmpty()){
				Point last = tails.removeFirst();
				a[last.x][last.y]=0;
				tails.addLast(new Point(headX,headY-1));
				a[headX][headY-1]=1;
			}
		}
		a[headX][headY]=3;
	}
	public static void print(){
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("현재 머리 : " + headX + "," + headY);
		System.out.println();
	}
	public static void go(){
		while(!finish){
			switch(dir){
				case 1:
					moveUp();
//					print();
					++ans;
					change();
					break;
				case 2:
					moveDown();
//					print();
					++ans;
					change();
					break;
				case 3:
					moveLeft();
//					print();
					++ans;
					change();
					break;
				case 4:
					moveRight();
//					print();
					++ans;
					change();
					break;
			}	
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		//사과가 있으면 a[x][y]=2;
		for(int i=0; i<k; i++){
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			a[x][y] = 2;
		}
		
		int t = in.nextInt();
		for(int i=0; i<t; i++){
			int x = in.nextInt();
			String temp = in.next();
			change[x] = temp.charAt(0);
		}
		a[0][0] = 3;
		go();
		
		System.out.println(ans);
		in.close();
	}
	public static class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static class Head{
		int x, y, dir;
		public Head(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}

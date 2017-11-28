package week_10th_thu;

import java.util.*;
public class Baek10875 {

	/*뱀
	 * 
	 * 조금 다른 뱀
	 * 벽에 부딪히거나 몸뚱이에 부딪히면 죽는건 똑같다
	 * 근데 2차원배열을 못만든다
	 * 무식하게 못한다
	 * 자신의 몸뚱이 정보만 담고있어보자*/
	static int n, time=0, k;
	static int change = -1;
	static char to = 'N';
	static int hx=0, hy=0, dir=4; //맨 처음에는 우
	static Queue<Time> c = new LinkedList<>();
	static List<Snake> snake = new ArrayList<>();
	public static boolean die(){
		for(Snake temp : snake){
			int x = temp.x;
			int y = temp.y;
			if(hx==x && hy==y) return true;
		}
		return false;
	}
	public static void change(){
		if(change==-1 && to=='N'){
			Time temp = c.remove();
			change = temp.x;
			to = temp.c;
		}
		if(change!=time) return;
		
		
		switch(dir){
		case 1: //상
			if(to=='L') dir=3;
			else if(to=='R') dir=4;
			break;
		case 2: //하
			if(to=='L') dir=4;
			else if(to=='R') dir=3;
			break;
		case 3: //좌
			if(to=='L') dir=2;
			else if(to=='R') dir=1;
			break;
		case 4: //우
			if(to=='L') dir=1;
			else if(to=='R') dir=2;
			break;
		}
		System.out.println("현재 시간: " + time + " 바뀐 방향 : " +to);
		change = -1;
		to = 'N';
	}
	/*public static void change(){
		if(c[time]!='L' && c[time]!='R'){
			return;
		}
		switch(dir){
		case 1: //상
			if(c[time]=='L') dir=3;
			else if(c[time]=='R') dir=4;
			break;
		case 2: //하
			if(c[time]=='L') dir=4;
			else if(c[time]=='R') dir=3;
			break;
		case 3: //좌
			if(c[time]=='L') dir=2;
			else if(c[time]=='R') dir=1;
			break;
		case 4: //우
			if(c[time]=='L') dir=1;
			else if(c[time]=='R') dir=2;
			break;
		}
	}*/
	public static void go(){
		boolean finish = false;
		while(true){
			switch(dir){
			case 1:	//상
				++hy;	//y를 증가
				finish = die();	//죽었는지 판별
				if(finish) finish = true;
				break;
			case 2:	//하
				--hy;	//y를 증가
				finish = die();	//죽었는지 판별
				if(finish) finish = true;
				break;
			case 3:	//좌
				--hx;	//y를 증가
				finish = die();	//죽었는지 판별
				if(finish) finish = true;
				break;
			case 4:	//우
				++hx;	//y를 증가
				finish = die();
				if(finish) finish = true;
				break;
			}
			++time;	//시간증가
			if(finish) break; //죽었으면 끝
			snake.add(new Snake(hx,hy)); //안죽었으면 머리 늘려주기
//			System.out.println(hx+","+hy+"가 추가됨");
			change(); //방향바꾸기
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		snake.add(new Snake(0,0));
		n = in.nextInt();
		
		//n보다 1큰 애들을 뱀의 일부라고 생각 
		for(int i=-n+1; i<=n+1; i++){
			snake.add(new Snake(i,n+1));
			snake.add(new Snake(i,-(n+1)));
			snake.add(new Snake(n+1,i));
			snake.add(new Snake(-(n+1),i));
		}
		k = in.nextInt();
		
		int x = in.nextInt();
		String temp = in.next();
		change = x;
		to = temp.charAt(0);
		for(int i=0; i<k-1; i++){
			int t = in.nextInt();
			x+=t;
			temp = in.next();
			c.add(new Time(x,temp.charAt(0)));
		}
		
		go();
		System.out.println(time);
		in.close();
	}
	public static class Snake{
		int x, y;
		public Snake(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static class Time{
		int x;
		char c;
		public Time(int x, char c){
			this.x = x;
			this.c = c;
		}
	}
}

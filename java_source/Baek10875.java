package week_10th_thu;

import java.util.*;
public class Baek10875_fix {

	/*뱀
		시간 초과가 뜸
		몸통을 다 비교하니깐 죽는 시간 비교하기가 너무 오래걸리는듯 하다
	*/
	static int n, k;
	static long change = -1, time = 0, hx=0, hy=0;
	static char to = 'N';
	static int dir=4; //맨 처음에는 우
	static List<Width> w = new ArrayList<>();
	static List<Height> h = new ArrayList<>();
	public static boolean die(long hx, long hy){
		if(hx<-n || hx>n || hy<-n || hy>n) {
//			System.out.println("맵 밖으로 나감");
			return true;
		}
		for(Height height : h){
			long x = height.x;
			long sy = Math.min(height.sy, height.ey);
			long ey = Math.max(height.sy, height.ey);
			if(hx==x){
				if(sy<=hy && hy<=ey){
//					System.out.println("x좌표 "+x+"에 대하여"+" y의 범위 "+sy +"~"+ey+"비교함");
//					System.out.println("세로선과 충돌했음");
					return true;
				}
			}
		}
		
		for(Width width: w){
			long y = width.y;
			long sx = Math.min(width.sx, width.ex);
			long ex = Math.max(width.sx, width.ex);
			if(hy==y){
				if(sx<=hx && hx<=ex){
//					System.out.println("y좌표 "+y+"에 대하여"+" x의 범위 "+sx +"~"+ex+"비교함");
//					System.out.println("가로선과 충돌했음");
					return true;
				}
			}
		}
		
		return false;
	}
	public static boolean go(long change, char to){
		boolean finish = false;
		//바뀌는 시간을 change - change되기 전까지 쭉쭉이동하기
		long x = hx;
		long y = hy;
		for(long i=0; i<change; i++){
			if(dir==1){		//상
//				System.out.println("위로 움직임");
				++y;
				finish=die(x,y);
			}
			else if(dir==2){//하
//				System.out.println("아래로 움직임");
				--y;
				finish=die(x,y);
			}
			else if(dir==3){//좌
//				System.out.println("좌로 움직임");
				--x;
				finish=die(x,y);
			}
			else{			//우
//				System.out.println("우로 움직임");
				++x;
				finish=die(x,y);
			}
			++time;
			if(finish) return true;
		}
//		System.out.println("원래 x,y좌표는 : " + hx+","+hy);
//		System.out.println("움직인 x,y좌표는 : " + x +","+y);
		if(dir==3 || dir==4){
			w.add(new Width(hx,x,y));
//			System.out.println("뱀이 y: "+y+"에서 x좌표를 :"+hx+" ~ "+x+"만큼 이동");
			hx = x;
		}
		else if(dir==1 || dir==2){
			h.add(new Height(hy,y,x));
//			System.out.println("뱀이 x: "+x+"에서 x좌표를 :"+hy+" ~ "+y+"만큼 이동");
			hy = y;
		}
//		System.out.print("방향이 : "+dir+" 에서" );
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
//		System.out.println(dir+"로 바뀜");
//		System.out.println("현재 머리 좌표 : " + hx+","+hy);
		return false;
	}
	public static void go(){
		while(true){
			if(dir==1)//상
				++hy;
			else if(dir==2)//하
				--hy;
			else if(dir==3)//좌
				--hx;
			else if(dir==4)//우
				++hx;
			++time;
			if(die(hx,hy)) break;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		
		long x = 0;
		boolean finish = false;
		for(int i=0; i<k; i++){
			long t = in.nextLong();
			String temp = in.next();
			finish = go(t,temp.charAt(0));
			if(finish) break;
		}
		if(finish)
			System.out.println(time);
		
		else{
			go();
			System.out.println(time);
		}
		in.close();
	}
	public static class Width{
		//가로
		long sx,ex,y;
		public Width(long sx, long ex, long y){
			this.sx = sx;
			this.ex = ex;
			this.y = y;
		}
	}
	public static class Height{
		//세로
		long sy,ey,x;
		public Height(long sy, long ey, long x){
			this.sy = sy;
			this.ey = ey;
			this.x =x;
		}
	}
}

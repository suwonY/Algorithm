package week_11th_mon;

import java.util.*;
public class Baek1074 {

	/*Z
	 * 
	 * Z순서대로 탐색할때 주어진 점이 몇번째 탐색되는가
	 * n 
	 * r 
	 * c 
	 * 
	 * n일 때 2*2의 개수는 4*(n-1)개가 됨
	 * 2개 짜리 1로 시작 4로 끝남(나중에 1 빼면됨)
	 * 
	 * 맨 처음 어디에 포함되는지 확인을 해보자
	 * n이 주어졌을 때 한 변의 길이는 2^n
	 * 1사분면은 
	 * x 0 ~ 2^(n-1)-1
	 * y 0 ~ 2^(n-1)-1까지
	 * 2사분면은
	 * x 2^(n-1) ~ 2^n-1
	 * y 2^(n-1) ~ 2^n-1 
	 * */
	static long x,y;
	static int n;
	public static void go(long x, long y, int n, long start){
		long size = (long)Math.pow(2, n);
		if(size==1){
			System.out.println(start);
			return;
		}
		
		if(x<size/2 && 0<=y && y<size/2){
			//시작점의 좌표 0,0
//			System.out.println("1사분면");
//			System.out.println("x좌표 : " + x + " y좌표 : " + y);
		}
		else if(0<=x && x<size/2 && size/2<=y && y<size){
//			System.out.println("2사분면");
			//시작점의 좌표 0,2^(2n-2)
			y = Math.abs((long)Math.pow(2,n-1) - y);
//			System.out.println("x좌표 : " + x + " y좌표 : " + y);
			start += Math.pow(2, 2*n-2);
		}
		else if(size/2<=x && x<size && 0<=y && y<size/2){
//			System.out.println("3사분면");
			//시작점의 좌표 2^(2n-1),0
			x = Math.abs((long)Math.pow(2,n-1) - x);
//			System.out.println("x좌표 : " + x + " y좌표 : " + y);
			start += Math.pow(2, 2*n-1);
		}
		else{
//			System.out.println("4사분면");
			//시작점의 좌표 2^(2n-1), 2^(2n-1)
			x = Math.abs(x - (long)Math.pow(2,n-1));
			y = Math.abs((long)Math.pow(2,n-1) - y);
//			System.out.println("x좌표 : " + x + " y좌표 : " + y);
			start += 3 * Math.pow(2, 2*n-2);
		}
		go(x,y,n-1,start);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		x = in.nextInt();
		y = in.nextInt();
		go(x,y,n,0);
		in.close();
	}
}

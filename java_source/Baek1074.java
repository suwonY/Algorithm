package week_11th_mon;

import java.util.*;
public class Baek1074 {

	/*Z
	 * 
	 * Z������� Ž���Ҷ� �־��� ���� ���° Ž���Ǵ°�
	 * n 
	 * r 
	 * c 
	 * 
	 * n�� �� 2*2�� ������ 4*(n-1)���� ��
	 * 2�� ¥�� 1�� ���� 4�� ����(���߿� 1 �����)
	 * 
	 * �� ó�� ��� ���ԵǴ��� Ȯ���� �غ���
	 * n�� �־����� �� �� ���� ���̴� 2^n
	 * 1��и��� 
	 * x 0 ~ 2^(n-1)-1
	 * y 0 ~ 2^(n-1)-1����
	 * 2��и���
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
			//�������� ��ǥ 0,0
//			System.out.println("1��и�");
//			System.out.println("x��ǥ : " + x + " y��ǥ : " + y);
		}
		else if(0<=x && x<size/2 && size/2<=y && y<size){
//			System.out.println("2��и�");
			//�������� ��ǥ 0,2^(2n-2)
			y = Math.abs((long)Math.pow(2,n-1) - y);
//			System.out.println("x��ǥ : " + x + " y��ǥ : " + y);
			start += Math.pow(2, 2*n-2);
		}
		else if(size/2<=x && x<size && 0<=y && y<size/2){
//			System.out.println("3��и�");
			//�������� ��ǥ 2^(2n-1),0
			x = Math.abs((long)Math.pow(2,n-1) - x);
//			System.out.println("x��ǥ : " + x + " y��ǥ : " + y);
			start += Math.pow(2, 2*n-1);
		}
		else{
//			System.out.println("4��и�");
			//�������� ��ǥ 2^(2n-1), 2^(2n-1)
			x = Math.abs(x - (long)Math.pow(2,n-1));
			y = Math.abs((long)Math.pow(2,n-1) - y);
//			System.out.println("x��ǥ : " + x + " y��ǥ : " + y);
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

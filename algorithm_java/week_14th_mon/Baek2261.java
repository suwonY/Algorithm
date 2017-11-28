package week_14th_mon;

import java.util.*;
public class Baek2261 {

	/*가장 가까운 두 점
	 * 
	 * n개의 점이 주어진다
	 * 이 점들 중 가장 가까운 두 점을 구하는 프로그램을 작성하시오*/
	static int n;
	static long ans = 987654321;
	static Point[] a = new Point[100001];
	static boolean[] c = new boolean[100001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++){
			int x = in.nextInt();
			int y = in.nextInt();
			a[i] = new Point(x,y);
		}
		long ans = 1000000000;
		long temp = 1000000000;
		for(int i=0; i<n; i++){
			for(int j=i+1; j<n; j++){
				temp = Math.min(temp, Math.abs(a[i].x-a[j].x) + Math.abs(a[i].y - a[j].y));
				ans = Math.min(temp, ans);
			}
		}
		
		System.out.println((int)Math.pow(ans, 2));
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

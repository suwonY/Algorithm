package week_14th_mon;

import java.util.*;
public class Baek2261_fix1 {

	/*가장 가까운 두 점
	 * 
	 * n개의 점이 주어진다
	 * 이 점들 중 가장 가까운 두 점을 구하는 프로그램을 작성하시오*/
	static int n;
	static long ans;
	static List<Point> a = new ArrayList<>();
	public static long dist(Point a, Point b){
		long x = Math.abs(a.x - b.x);
		long y = Math.abs(a.y - b.y);
		
		return (long) (x*x + y*y);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++){
			int x = in.nextInt();
			int y = in.nextInt();
			a.add(new Point(x,y));
		}
		
		Collections.sort(a);
		ans = dist(a.get(0),a.get(1));
		
		for(int i=2; i<n; i++){
			Point now = a.get(i);
			
			for(int j=0; j<2; j++){
				Point temp = a.get(j);
				int x = now.x - temp.x;
				if(x*x>ans){
					
				}
				else{
					long d = dist(now,temp);
					ans = Math.min(ans, d);
				}
			}
		}
		
		in.close();
	}
	public static class Point implements Comparable<Point>{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}
	}
}

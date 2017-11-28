package week_15th_mon;

import java.util.*;
public class kakao05 {

	//같은 열을 밟을 수 없다
	//
	public static class Point{
		int x, y, up;
		public Point(int x, int y, int up){
			this.x = x;
			this.y = y;
			this.up = up;
		}
	}
	static int[] dy = {-3,-2,-1,1,2,3};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[][] a = {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
		int n = a.length;
		System.out.println(n);
		
		int[][] d = new int[n][4];
		for(int i=0; i<4; i++)
			d[0][i] = a[0][i];

		for(int i=1; i<n; i++){
			for(int j=0; j<4; j++){
				for(int k=0; k<4; k++){
					if(j==k) continue;
					d[i][j] = Math.max(d[i][j],d[i-1][k]+a[i][j]);
				}
			}
		}
		
		for(int i=0; i<n; i++){
			for(int j=0; j<4; j++){
				System.out.print(d[i][j]+" ");
			}
			System.out.println();
		}
		
		int max = 0;
		for(int i=0; i<n; i++)
			max = Math.max(max, d[n-1][i]);
		System.out.println(max);
		
		
		in.close();
	}

}

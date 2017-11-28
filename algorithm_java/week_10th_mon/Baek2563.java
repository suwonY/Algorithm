package week_10th_mon;

import java.util.*;
public class Baek2563 {

	static boolean[][] a = new boolean[101][101];
	static int k, ans = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		k = in.nextInt();
		
		for(int t=0; t<k; t++){
			int y = in.nextInt();
			int x = in.nextInt();
			
			for(int i=x; i<x+10; i++){
				for(int j=y; j<y+10; j++){
					if(!a[i][j]){
						++ans;
						a[i][j]=true;
					}
				}
			}
		}
		System.out.println(ans);
		in.close();
	}
}
package Baek_SWPractice_1st;

import java.util.*;
public class Baek14499 {
	static int[] dice = {0,0,0,0,0,0,0};
	static int n, m, k;
	static int[][] a = new int[21][21];
	static int x, y;
	static int bottom = 3;
	public static void swap(int a, int b, int c, int d, int e, int f){
		int[] temp = new int[6];
		temp[0] = dice[a];
		temp[1] = dice[b];
		temp[2] = dice[c];
		temp[3] = dice[d];
		temp[4] = dice[e];
		temp[5] = dice[f];
		
		dice = temp.clone();
	}
	public static void check(){
		if(a[x][y]==0) a[x][y] = dice[0];
		else{
			dice[0] = a[x][y];
			a[x][y] = 0;
		}
	}
	public static void north(){
		swap(1,5,2,3,0,4);
		check();
		System.out.println(dice[5]);
	}
	public static void south(){
		swap(4,0,2,3,5,1);
		check();
		System.out.println(dice[5]);
	}
	public static void east(){
		swap(2,1,5,0,4,3);
		check();
		System.out.println(dice[5]);
	}
	public static void west(){
		swap(3,1,0,5,4,2);
		check();
		System.out.println(dice[5]);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt();
		m = in.nextInt();
		x = in.nextInt();
		y = in.nextInt();
		k = in.nextInt();
		for(int i=0; i<n; i++){
			for(int j=0 ; j<m; j++){
				a[i][j] = in.nextInt();
			}
		}
		for(int i=0; i<k; i++){
			int temp = in.nextInt();
			if(temp==1){
				if(y+1>=m)continue;
				++y;
				east();
			}
			else if(temp==2){
				if(y-1<0) continue;
				--y;
				west();
			}
			else if(temp==3){
				if(x-1<0) continue;
				--x;
				north();
			}
			else{
				if(x+1>=n) continue;
				++x;
				south();
			}
		}
		in.close();
	}
}

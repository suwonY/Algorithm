package pracAlgorithm;

import java.util.*;
public class Baek2580 {
	static int[][] a = new int[9][9];
	static boolean stop;
	static boolean impossible(int num, int x, int y) {
		int rx = x / 3 * 3, ry = y / 3 * 3;
		for (int i = 0; i < 9; i++) 
			if (a[x][i] == num || a[i][y] == num) return false;
		for (int i = rx; i < rx + 3; i++)
			for (int j = ry; j < ry + 3; j++) 
				if (a[i][j] == num) return false;
		return true;
	}
	static void go() {
		if (stop) return;
		int x = -1, y = -1;
		
		find:
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (a[i][j] == 0) {
					x = i; 
					y = j;
					break find;
				}
			}
		}
		
		if (x==-1) {
			stop = true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) 
					System.out.print(a[i][j]+" ");
				System.out.println();
			}
			return;
		}
		
		for (int i = 1; i < 10; i++) {
			if (impossible(i,x,y)) {
				a[x][y] = i;
				go();
				a[x][y] = 0;
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				a[i][j] = in.nextInt();
		go();
		in.close();
	}
}
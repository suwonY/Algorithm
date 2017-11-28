package Baek_SWPractice_1st;

import java.util.*;
public class Baek14502 {

	static int n,m;
	static int[][] a = new int[9][9];
	static boolean[][] c = new boolean[9][9];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				a[i][j] = in.nextInt();
		
		
		
		in.close();
	}

}

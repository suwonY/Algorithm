package Baek_SWPractice_1st;

import java.util.*;
public class Baek14503 {

	//·Îº¿Ã»¼Ò±â
	//
	static int n,m;
	static int[][] a = new int[51][51];
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

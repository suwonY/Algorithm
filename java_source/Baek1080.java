package Baek_SWPractice_2nd;

import java.util.*;
public class Baek1080 {
	
	/*За·Д
	 * 
	 * */
	static int n,m;
	static int[][] a = new int[51][51];
	static int[][] b = new int[51][51];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<m; j++){
				if(temp.charAt(j)=='0') a[i][j] = 0;
				else a[i][j] = 1;
			}
		}
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<m; j++){
				if(temp.charAt(j)=='0') b[i][j] = 0;
				else b[i][j] = 1;
			}
		}
		
		
		in.close();
	}
}
package Baek_SWPractice_1st;

import java.util.*;
public class Baek14501 {

	static int n;
	static int[] t = new int[101];
	static int[] p = new int[101];
	static int[] d = new int[201];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++){
			t[i] = in.nextInt();
			p[i] = in.nextInt();
		}
		for(int i=0; i<n; i++){
			d[i+1] = Math.max(d[i], d[i+1]);
			d[i+t[i]] = Math.max(d[i]+p[i], d[i+t[i]]);
		}
		System.out.println(d[n]);
		in.close();		
	}
}
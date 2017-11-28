package Baek_SWPractice_1st;

import java.util.*;
public class Baek13458 {
	static int n;//시헙장 개수
	static int[] a = new int[1000001];//시험장 응시자 수
	static int b,c;//총감독 , 부감독 감독자 수
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		b = in.nextInt();
		c = in.nextInt();
		
		long ans = 0;
		for(int i=0; i<n; i++){
			a[i] -= b;
			++ans;
			if(a[i]<=0) continue;
			int m = a[i]/c;
			ans += m;
			if(a[i]-(m*c)>0) ++ans;
		}
		System.out.println(ans);
		in.close();
	}
}
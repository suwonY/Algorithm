package week_15th_thu;

import java.util.*;
public class Baek1759 {

	static int n,m;
	static char[] c = new char[16];
	public static void go(int cnt, String now, int i){
		if(now.length()>cnt) return;
		if(cnt==4){
			System.out.println(now);
			return;
		}
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<m; i++)
			c[i] = in.next().charAt(0);
		
		
		in.close();
	}

}

package week_14th_mon;

import java.util.*;
public class Baek5525 {

	static int n, m;
	static String s = "IOI",a;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=1; i<n; i++)
			s+="OI";
		
		m = in.nextInt();
		a = in.next();
		int cnt = 0;
		while(a.contains(s)){
			a = a.replaceFirst(s,"I");
			++cnt;
		}
		System.out.println(cnt);
		in.close();
	}

}

package week_15th_mon;

import java.util.*;
public class kakao01 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String a = Integer.toString(n);
		char[] b = a.toCharArray();
		int ans = 0;
		for(int i=0; i<b.length; i++)
			ans += b[i]-48;
	}
}

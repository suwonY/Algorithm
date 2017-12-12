package pracAlgorithm;

import java.util.*;
public class Baek1120 {
	static String a,b;
	static int cnt = 0, ans = 987654321;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		a = in.next();
		b = in.next();
		for(int i=0; i<b.length()-a.length(); i++){
			cnt = 0;
			for(int j=0; j<a.length(); j++)
				if(a.charAt(j)!=b.charAt(j+1))++cnt;
			ans = Math.min(ans, cnt);
		}
		System.out.println(ans);
		in.close();
	}
}
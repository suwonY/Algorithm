package week_14th_mon;

import java.util.*;
public class Baek11399 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		Arrays.sort(a);
		int ans = 0;
		for(int i=0,j=n; i<n; i++,j--)
			ans += a[i]*j;
		System.out.println(ans);
		in.close();
	}
}

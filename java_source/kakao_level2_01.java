package week_15th_thu;

import java.util.*;
public class kakao_level2_01 {

	public static int go(int[] a, int[] b){
		int ans = 0;
		Arrays.sort(a);
		Arrays.sort(b);
		int n = a.length;
		for(int i=0; i<n; i++)
			ans += a[i]*b[n-i-1];
		return ans;
	}
	public static void main(String[] args) {
		int[] a = {1,2};
		int[] b = {3,4};
		System.out.println(go(a,b));
	}

}

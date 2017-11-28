package week_15th_mon;

import java.util.*;
public class kakao02 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		boolean[] c = new boolean[1000001];
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		
		for(int i=0; i<n; i++){
			if(c[a[i]]) {
				System.out.println("false");
				return;
			}
			c[a[i]]=true;
		}
		System.out.println("true");
		
		in.close();
	}

}

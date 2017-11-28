package Baek_SWPractice_2nd;

import java.util.*;
public class Baek13549 {

	static int n,k;
	static int[] d = new int[200001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		Arrays.fill(d, 987654321);
		
		for(int i=1; n*2*i<=k; i++) d[n*2*i]=0;

		for(int i=n; i<=k; i++){
			d[i] = Math.min(d[i], d[i+1]+1);
			d[i] = Math.min(d[i], d[i-1]+1);
			d[i] = Math.min(d[i], d[i*2]);
		}
		System.out.println(d[k]);
		in.close();
	}
}
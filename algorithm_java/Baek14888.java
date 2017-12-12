package pracAlgorithm;

import java.util.*;
public class Baek14888 {
	static int maxN = -100000000, minN = 100000000;
	static int n, p, s, m, d;
	static int[] a = new int [12];
	static void go(int index, int plus, int sub, int multi, int divi, int total) {
		if (index == n) {
			maxN = Math.max(maxN, total);
			minN = Math.min(minN, total);
			return;
		}
		if (plus < p)
			go(index + 1, plus + 1, sub, multi, divi, total + a[index]);
		if (sub < s)
			go(index + 1, plus, sub + 1, multi, divi, total - a[index]);
		if (multi < m)
			go(index + 1, plus, sub, multi + 1, divi, total * a[index]);
		if (divi < d)
			go(index + 1, plus, sub, multi, divi + 1, total / a[index]);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		p = in.nextInt();
		s = in.nextInt();
		m = in.nextInt();
		d = in.nextInt();
		go(1,0,0,0,0,a[0]);
		System.out.println(maxN);
		System.out.println(minN);
		in.close();
	}
}

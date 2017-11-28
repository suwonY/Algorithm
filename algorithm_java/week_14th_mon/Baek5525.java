package week_14th_mon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek5525_fix3 {
	static int n;
	static int m;
	static String s;
	static String a = "IOI";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine().trim());
		m = Integer.parseInt(br.readLine().trim());
		s = br.readLine();
		for(int i=1; i<n; i++)
			a += "OI";
		ArrayList<Integer> list = kmp(s, a);
		System.out.println(list.size());
	}
	public static int[] getPi(String pattern) {
		int m = pattern.length();
		int j = 0;
		char[] p = new char[m];
		int[] pi = new int[m];
		p = pattern.toCharArray();
		for (int i = 1; i < m; i++) {
			while (j > 0 && p[i] != p[j]) 
				j = pi[j - 1];
			if (p[i] == p[j]) 
				pi[i] = ++j;
		}
		return pi;
	}
	public static ArrayList<Integer> kmp(String str, String pattern) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int[] pi = getPi(pattern);
		int n = str.length(), m = pattern.length(), j = 0;
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		for (int i = 0; i < n; i++) {
			while (j > 0 && s[i] != p[j]) 
				j = pi[j - 1];
			
			if (s[i] == p[j]) {
				if (j == m - 1) {
					list.add(i - m + 1);
					j = pi[j];
				} else {
					j++;
				}
			}
		}
		return list;
	}
}

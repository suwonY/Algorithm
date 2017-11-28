package week_14th_mon;

import java.util.*;
public class Baek5525_fix1 {

	static int n, m;
	static String s = "IOI",a;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		a = in.next();
		for(int i=1; i<n; i++)
			s+="OI";
		
		ArrayList<Integer> list = kmp(a,s);
		System.out.println(list.size());
		
		in.close();
	}
	public static int[] getPi(String pattern){
		/*pi[i]는 주어진 문자열의 o~i 까지의 부분 문자열 중에서
		 * prefix==suffix가 될 수 있는 부분 문자열 중에서 가장 긴 것의 길이
		 * prefix==suffix 조건인 이뉴는
		 * 전체 문자열 비교 인덱스는 한번만 비교된다. 계속 한자리씩 증가하고 있다.
		 * 그렇기에 중간 단계를 점프할 때 점프 된 인덱스 전의 문자열에 포함되는 부분이
		 * 존재할 경우가 있기 때문
		 * */
		
		int m = pattern.length();
		int j = 0;
		char[] p = new char[m];
		int[] pi = new int[m];
		p = pattern.toCharArray();
		for(int i=1; i<m; i++){
			while(j>0 && p[i]!=p[j])
				j = pi[j-1];
			if(p[i]==p[j]) 
				pi[i] = ++j;
		}
		return pi;
	}
	public static ArrayList<Integer> kmp(String str, String pattern){
		ArrayList<Integer> list = new ArrayList<>();
		int[] pi = getPi(pattern);
		int n = str.length();
		int m = pattern.length();
		int j = 0;	//찾을 문자열의 비교 인덱스
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		
		for(int i=0; i<n; i++){
			while(j>0 && s[i]!=p[j])
				//중간 단계 뛰어넘기
				//pi배열을 이용하여 j인덱스를 변경시킴
				j = pi[j-1];
			
			if(s[i]==p[j]){
				if(j==m-1){
					list.add(i-m+1);
					j = pi[j];
				}
				else ++j;
			}
		}
		return list;
	}
}

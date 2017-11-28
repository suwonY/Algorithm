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
		/*pi[i]�� �־��� ���ڿ��� o~i ������ �κ� ���ڿ� �߿���
		 * prefix==suffix�� �� �� �ִ� �κ� ���ڿ� �߿��� ���� �� ���� ����
		 * prefix==suffix ������ �̴���
		 * ��ü ���ڿ� �� �ε����� �ѹ��� �񱳵ȴ�. ��� ���ڸ��� �����ϰ� �ִ�.
		 * �׷��⿡ �߰� �ܰ踦 ������ �� ���� �� �ε��� ���� ���ڿ��� ���ԵǴ� �κ���
		 * ������ ��찡 �ֱ� ����
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
		int j = 0;	//ã�� ���ڿ��� �� �ε���
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		
		for(int i=0; i<n; i++){
			while(j>0 && s[i]!=p[j])
				//�߰� �ܰ� �پ�ѱ�
				//pi�迭�� �̿��Ͽ� j�ε����� �����Ŵ
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

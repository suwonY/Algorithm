package Baek_SWPractice_1st;

import java.util.*;
public class Baek13458 {
	static int n;//������ ����
	static int[] a = new int[1000001];//������ ������ ��
	static int b,c;//�Ѱ��� , �ΰ��� ������ ��
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		b = in.nextInt();
		c = in.nextInt();
		
		long ans = 0;
		for(int i=0; i<n; i++){
			a[i] -= b;
			++ans;
			if(a[i]<=0) continue;
			int m = a[i]/c;
			ans += m;
			if(a[i]-(m*c)>0) ++ans;
		}
		System.out.println(ans);
		in.close();
	}
}
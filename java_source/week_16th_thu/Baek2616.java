package week_16th_thu;

import java.util.*;
public class Baek2616 {

	//���������
	//1 �ִ�� ���� �ִ� ������ ���� ���س��(��� ���� ���� �����ִ�)
	//2 �ִ�� �������
	//3 ���������� �̾��� ������ ����
	static int n,k;
	static int[] a = new int[50001];
	static int[] s = new int[50001];
	static int[] d = new int[50001];
	static boolean[] c = new boolean[50001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=1; i<=n; i++){
			a[i] = in.nextInt();
			s[i] = s[i-1] + a[i];
		}
		k = in.nextInt();
		
		in.close();
	}
}
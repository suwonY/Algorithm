package week_12th_thu;

import java.util.*;
public class Baek1592 {
	/*�����̿� ģ����
	 * ���� ���� ������
	 * �ѻ���� m�� ������ ������
	 * 
	 * ���� ���� ���� Ƚ���� 
	 * Ȧ���̸� ���������� l��° �ִ� �������
	 * ¦���̸� �������� l��° �ִ� ������� ���� ������
	 * */
	static int n,m,l;
	static int[] a = new int[1001];	//���� ������� ���� ���� Ƚ��
	public static int left(int num){
		return num-l<0 ? (num-l)+n : num-l; 
	}
	public static int right(int num){
		return (num+l)%n;
	}
	public static void go(int now, int cnt){
//		System.out.println("���� ���� ������ �ִ� ��� : " + now);
		//���� ��� ���� Ƚ�� 1 ����
		++a[now];
//		System.out.println("�̻���� ���� ���� Ƚ�� : " +a[now]);
		if(a[now]==m){
			System.out.println(cnt);
			return;
		}
		
		//���� ����� ¦���� �޾�����
		if(a[now]%2==0){
//			System.out.println("¦���� �޾Ƽ� �������� ����");
			go(left(now),cnt+1);
		}
		//��������� Ȧ���� �޾�����
		else{
//			System.out.println("Ȧ���� �޾Ƽ� ���������� ����");
			go(right(now),cnt+1);
		}
//		System.out.println();
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		l = in.nextInt();
		go(0,0);
		in.close();
	}
}

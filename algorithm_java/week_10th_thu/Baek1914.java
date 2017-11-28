package week_10th_thu;

import java.util.*;
public class Baek1914 {

	/*�ϳ��� ž
		1~n���� ������
		1~n-1���� b�� ���� n�� c�� �ű��
		1~n-2���� a�� ���� n-1�� c�� �ű��
		~
		1�� c�� �ű��
		
		�� �۰� �����غ��� 
		1~n-1�� b�� �ű�� ����� �����غ���
		-> 1~n-2�� c�� �ű� n-1�� b�� �ű��
		
		
		n==2�� ���(c�� ���������� �ű�µ�, b�� �̿��� ���)
		1. 1�� b�� ���� 2�� c�� ���´�(1->2, 1->3)
		2. 1�� c�� �ű��(2->3)
		(a-b a-c b-c)
		
		n==3�� ���
		2���� b�� �ű��� 3�� c�� �ű��
		1. a���� 2���� b�� �ű�� ����� (b�� ���������� �ű�µ�, c�� �̿��� ���)
		(a-c a-b c-b)
		2. 3�� c�� �ű��(a-c)
		3. 1���� a�� �ű��(b-a)
		4. 2�� c�� �ű��(b-c)
		5. 1�� c�� �ű��(a-c)
		
		n==4�� ���
		1.a���� 3���� b�� �ű��
		- 3���� b�� �ű��
		  - 2���� c�� �ű��
		  - 1
		
		1. a���� b���� n-1�� �ű��
		- n-2���� a���� c�� �ű��
		  - n-3���� a���� b�� �ű��
		  - n-4���� a���� c�� �ű��
		  - n-5���� a���� b�� �ű��
		  - n-6���� a���� c�� �ű�� ~~~~
		  - 1�� b�� c�� �ű��
		2. 
	 * */
	static int n, ans=0;
	static long ans1=0;
	//��� ���� ������ ����
	static int[][] a = new int[3000000][2];
	public static void go(int num, int from, int to, int spare){
		//�ᱹ
		//1. n-1���� from���� spare���� �����ְ�
		//2. n�� from���� to�� ������
		//3. n-1���� spare���� to�� ������
		if(num==1){
			//2.��ӳ���
			a[ans][0]=from;
			a[ans][1]=to;
			++ans;
		}
		else{
			//1.
			go(num-1,from,spare,to);
			a[ans][0]=from;
			a[ans][1]=to;
			++ans;
			//3.
			go(num-1,spare,to,from);
		}
	}
	public static void go1(int num, int from, int to, int spare){
		if(num==1)
			++ans1;
		else{
			go(num-1,from,spare,to);
			++ans1;
			go(num-1,spare,to,from);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		if(n<=20){
			go(n,1,3,2);
			System.out.println(ans);
			for(int i=0; i<ans; i++)
				System.out.println(a[i][0] + " " + a[i][1]);
		}
		else{
			go1(n,1,3,2);
			System.out.println(ans1);
		}
		in.close();
	}
}
package week_10th_thu;

import java.util.*;
public class Baek1254_fix {

	/*�Ӹ���� �����
	 ��ģ�κ� : ���ڸ� ���ڿ� �ڿ� �߰��Ͽ��� �ȴ�
	 �׷��� ���� ������ ���� �κ��� ���� ������ �ڿ� �߰����ִ� ������� ��������
	 permite�� �ִٰ� �� ��
	 p�� ���� �����Ƿ�
	 permitep|�� �����
	 ->ermite|�� �ִٰ� ��������
	 �׷��� e�� ���ĵ� e���� r�� ��ġ�� �����Ƿ�
	 ermite|re �� �����
	 |�տ��� ���ڿ��� �߰��� �� ���� ����
	 ermitere�� �ƴ�
	 mite�� ���Ҵ�
	 e�� �߰��̹Ƿ�
	 mitetim���� �߰��ϸ� �ȴ�
	 
	 �� �ٸ��� �����غ���
	 abb�� �ִ� bba�� �ڿ� ���δ�
	 ¦������ ���ڿ��� ���
	 abbb�� ���� ���
	 abbb|bbba
	 ���� ���ڿ��� �տ����� �ϳ��� ���ּ� ����
	 abbb|bba
	 abbb|ba
	 abbb|a���� Ȯ�����ϸ� ������ �Ӹ���ҹ��ڰ� �ǹ���
	 
	 abab|baba	O
	 abab|aba	O
	 abab|ba	X
	 abab|a		O
	 ���� ���� ���ڿ��� ã�´�
	 
	 */
	static String n;
	static char[] a = new char[2002];
	static int len;
	
	//�Ӹ�������� Ȯ���ϴ� ��
	public static boolean finish(int temp){
		for(int i=0; i<=temp/2-1; i++){
			//���� i  �ڴ� len-i-1
			if(a[i]!=a[temp-i-1])
				return false;
		}
		return true;
	}
	public static void go(int cnt){
//		System.out.println("���� len : " + len);
		int temp = len;
		
		for(int t=0; t<cnt; t++){
//			System.out.println("cnt : " + cnt + " len : " + len);
			
			for(int i=cnt+1; i<len; i++){
				if(a[i]==' ') break;
				a[i] = a[i+1];
			}
			--temp;
			
			/*System.out.println("temp : " + temp);
			
			for(int i=0; i<temp; i++){
				if(a[i]==' ') break;
				System.out.print(a[i]+" ");
			}
			System.out.println();*/
			
			if(finish(temp)) {
				len = Math.min(len, temp);
//				System.out.println("�پ��� . . . "+len);
			}
		}
		

	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.next();
		
		len = n.length();	

		for(int i=0; i<len; i++)
			a[i] = n.charAt(i);
		
		if(!finish(len)){
			for(int i=len-1, j=len; i>=0; i--, j++)
				a[j] = a[i];
			int cnt = len-1;
			len *= 2;
			go(cnt);
		}
		System.out.println(len);
		in.close();
	}
}

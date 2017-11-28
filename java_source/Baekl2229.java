package week_13th_mon;

import java.util.*;
public class Baekl2229 {

	/*��¥��
	 * ���ϴ� �л��� �߸��ϴ� �л����� �������� ���´�
	 * �Ƿ����̰� ���� ���� ���´�
	 * 
	 * 1. ���̼����� �����Ѵ�.
	 * 2. ������ �������
	 * 3. ���� ����ģ���� ����ģ���� ���� = �� ���� �� ¥�� ����
	 * 4. �Ѹ����� ������ ���� �� ¥���� ������ 0
	 * 
	 * ��ȭ���� �ѹ� ��������
	 * d[i]�� i���� ���� ����������� ������� �ϸ�
	 * �ʹ� �밡�ٰ�����
	 * ��...
	 * 
	 d[i]�� i���� ������� �� ¥���� ������ �ִ밪�̶���ϸ�
	 d[1] = 0
	 d[2] = d[1] + 0, a[2]-a[3]�� �ִ밪
	 d[3] = 
	  		d[2] + 0
	  		d[1] + abs(a[3]-a[2])
	  �� �ִ밪
	 d[4] = 
	 * 		d[3] + 0
	 * 		d[2] + a[4]-a[3]
	 * 		d[1] + 2~4�� �ִ밪
	 * 		d[0] + 1~4�� �ִ밪
	 * 
	 *  ~~
	 *  
	 d[n] = 
	 d[n-1] + 0
	 d[n-2] + a[n]-a[n-1]
	 d[n-3] + n-2~n���� �ִ밪-�ּҰ�
	 ~
	 ~
	 d[0] + 1~n������ �ִ밪-�ּҰ�
	  �� �ִ밪�� ���� �Ǵ»�Ȳ�� �߻��Ѵ�.
	  
	 * */
	static final int INF = 987654321;
	static int n;
	static int[] a;
	static int[] d;
	public static void go(){
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		d = new int[n];
		//���̼������ �־�����
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		
		
		/*
		d[n] = 
	 	d[n-1] + 0
	 	d[n-2] + a[n]-a[n-1]
	 	d[n-3] + n-2~n���� �ִ밪-�ּҰ�
	 	~
	 	~
	 	d[0] + 1~n������ �ִ밪-�ּҰ�
	  	�� �ִ밪�� ���� �Ǵ»�Ȳ�� �߻��Ѵ�.
	  	
	  	d[1] = 0
	 	d[2] = 
	 		d[1] + 0
	 		d[0] + a1~2 �� �ִ밪-�ּҰ�
	 	d[3] = 
	  	 d[2] + 0
	  	 d[1] + abs(a[3]-a[2])
	  	�� �ִ밪
	  	d[4] = 
	  	 d[3] + 0
	  	 d[2] + 3~4
	  	 d[1] + 2~4
	  	 d[0] + 1~4
		*/
		d[0] = 0;
		for(int i=1; i<n; i++){
			if(i==1){
				d[i] = Math.abs(a[1]-a[0]);
				continue;
			}
			int temp = 0, max = 0, min = INF;
//			System.out.println("d["+i+"] �� ��");
			for(int j=i; j>0; j--){
				max = Math.max(max, a[j]);
				min = Math.min(min, a[j]);
//				System.out.println("d["+j+"]"+ " = " + "d["+(j-1)+"] + " + "(max :" + max +" min :"+min + ")" + (max-min));
				temp = Math.max(temp, d[j-1]+(max-min));
			}
			d[i] = temp;
		}
		System.out.println(d[n-1]);
		in.close();
	}
}

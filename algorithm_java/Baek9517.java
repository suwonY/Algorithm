package week_10th_thu;

import java.util.*;
public class Baek9517 {

	/*���̷��� ũ�ξ�Ƽ��
	 * ��ź�� 3�� 30���Ŀ� ������
	 * ������ �����߰ų� �н��ϸ� ���� ������ Ǭ��
	 * ������ Ǯ�� ���� ������� �Ѱ���
	 * 
	 * ��ź�� ����ִ� ����� ��ȣ�� n���� ������ ����Ҷ����� �ɸ� �ð��� �־�����
	 * ��ź�� �Ͷ߸� ����� ��ȣ�� ���϶�
	 * ����� ������ T
	 * ����� Ʋ���� N
	 * ��ŵ������     P*/
	static int total = 210;	//�����½ð�
	static int ans,n;
	public static void change(){
		if(ans==8){
			ans=1;
			return;
		}
		++ans;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ans = in.nextInt();
		n = in.nextInt();
		for(int i=0; i<n; i++){
			int t = in.nextInt();
			String temp = in.next();
			char ans = temp.charAt(0);
			
			//���� �ð��� 
			if(t>total)
				break;
			
			total-=t;
			if(ans=='T')
				change();
		}
		System.out.println(ans);
		in.close();
	}
}

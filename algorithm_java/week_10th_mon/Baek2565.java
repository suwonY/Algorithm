package week_10th_mon;

import java.util.*;
public class Baek2565 {

	/*������
	 * a-b�� �մ� ������������ ��(������ 100����)
	 * �ּҰ����� ���� �������� �ʰ� �����
	 * 
	 * ���� �� �� �� �ִ� �������� �ִ밪�� ���ϴ� ����
	 * �ִ� ���������� ���ϴ� ����
	 * 
	 * */
	//�������� ������ b�� ���� �����Ѵ�
	//a�� ���� Ŀ���� ���� b�� ���� �۾����� �Ұ����ϴٴ� ���̴�
	//�ִ� ���������� ũ�⸦ l����ϸ�
	//�������Ǽ�(n) - l = �߶���� �������� ���´�.
	static int[] a = new int[501];
	static int n, max = 0, ans = 987654321, maxN = 0;
	public static void go(int start, int max){
		//�������� ������ �н�
		if(a[start]==0) return;
		int num = a[start];
		for(int i=start+1; i<=maxN; i++){
			if(a[i]==0) continue;
			if(num<a[i]){
				go(i,max+1);
			}
		}
		
		ans = Math.min(ans, n-max);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++){
			int x = in.nextInt();
			int y = in.nextInt();
			a[x] = y;
			maxN = Math.max(maxN, Math.max(x, y));
		}
		for(int i=0; i<501; i++)
			go(i,1);
		
		System.out.println(ans);
		in.close();
	}
}

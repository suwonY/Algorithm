package week_10th_mon;

import java.util.*;
public class Baek2565_fix {
	/*������
	 * a-b�� �մ� ������������ ��(������ 100����)
	 * �ּҰ����� ���� �������� �ʰ� �����
	 * 
	 * ���� �� �� �� �ִ� �������� �ִ밪�� ���ϴ� ����
	 * �ִ� ���������� ���ϴ� ����
	 * */
	//�������� ������ b�� ���� �����Ѵ�
	//a�� ���� Ŀ���� ���� b�� ���� �۾����� �Ұ����ϴٴ� ���̴�
	//�ִ� ���������� ũ�⸦ l����ϸ�
	//�������Ǽ�(n) - l = �߶���� �������� ���´�.
	
	//�ð��ʰ��� ����
	//LIS�� �ð��� �ٿ��߰ڴ�
	//d[i]�� i��°������ �������� �� �ִ��� �ϸ�
	static int[] a = new int[501];
	static int n, ans = 0, maxN = 0;
	public static void LIS(int[] arr, int len){
		int[] d = new int[len];
		for(int i=0; i<len; i++){
			if(arr[i]==0) continue;
			d[i]=1;
			for(int j=0; j<i; j++){
				if(arr[j]==0) continue;
				if(arr[i]>arr[j]){
					d[i] = Math.max(d[j]+1, d[i]);
				}
			}
		}
		for(int i=0; i<len; i++){
			ans = Math.max(d[i], ans);
		}
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
		LIS(a,maxN+1);
		System.out.println(n-ans);
		in.close();
	}
}

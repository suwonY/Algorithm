package week_14th_mon;

import java.util.*;
public class Baek1309 {
	/*������
	 * n*2�츮
	 * ���ڴ� ���η� ���η� �پ����� �� ����
	 * 
	 * ������ 0 �������� 1�̶�� �ϸ�
	 * n�� 1�� ����
	 * �����ʿ� �ְų�, ���ʿ��ְų�, �ƹ����� ���ų� 3������ ���´�
	 * d[n][0] �� d[n-1][0] + d[n-1][1] + d[n-1][2] 
	 * d[n][1] �� d[n-1][0] + d[n-1][2]
	 * d[n][2] �� d[n-1][0] + d[n-1][1]
	 * �� �ȴ�
	 * */
	static int n;
	static int[][] a = new int[100001][2];
	static int[][] d = new int[100001][3];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		//n�� 1�� ���� ����, ������, ���� �� ����
		d[1][0] = 1;
		d[1][1] = 1;
		d[1][2] = 1;
		
		for(int i=2; i<=n; i++){
			d[i][0] = (d[i-1][0] + d[i-1][1] + d[i-1][2])%9901;
			d[i][1] = (d[i-1][0] + d[i-1][2])%9901;
			d[i][2] = (d[i-1][0] + d[i-1][1])%9901;
		}
		System.out.println(d[n][0] + d[n][1] + d[n][2]);
		in.close();
	}
}
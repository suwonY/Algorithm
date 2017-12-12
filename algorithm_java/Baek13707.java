package week_15th_thu;

import java.util.*;
public class Baek13707 {

	/*d[k][n] 
	 * k���� ���ڷ� ���� n
	 * 
	 * k-1���� ���ڷ� ������ ���� �׷��� �������� l�̶�� �ϸ� n-l�� �Ǵϱ�
	 * d[k-1][n-l]�� �ȴ�(l�� 0~n)
	 * 
	 * �ٵ� �׸��� �׷�����
	 * d[k][n] = d[k][n-1] + d[k-1][n]���� ǥ���� �� ����
	 * */
	static final int mod = 1000000000;
	static long[][] d = new long[5001][5001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();

		d[0][0] = 1;
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j <= n; j++) {
				d[i][j] = d[i-1][j];
				if(j-1>=0) d[i][j] += d[i][j-1];
				d[i][j]%=mod;
			}
		}
		System.out.println(d[k][n]);
		in.close();
	}
}
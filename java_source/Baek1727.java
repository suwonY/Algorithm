package week_12th_mon;

import java.util.*;
public class Baek1727_again2 {

	/*Ŀ�ø����
	 * d[1][a]�� 1�� ���ڰ� a�� ���ڸ� ������ ��켺���� �ּҰ� ��
	 * 1-a�� ��� */
	//n�� �׻� ������ m�� �׻� ū��
	static int n,m;
	static int[] a = new int[1001];
	static int[] b = new int[1001];
	static int[][] d = new int[1001][1001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=1; i<=n; i++)
			a[i] = in.nextInt();
		for(int i=1; i<=m; i++)
			b[i] = in.nextInt();
		Arrays.sort(a);
		Arrays.sort(b);

		for(int i=1; i<=n; i++){
			for(int j=1; j<=m; j++){
				d[i][j] = d[i-1][j-1] + Math.abs(a[i]-b[i]);
				if(i>j && d[i][j]>d[i-1][j])
					d[i][j] = d[i-1][j];
				else if(i<j && d[i][j]>d[i][j-1])
					d[i][j] = d[i][j-1];
			}
		}
		System.out.println(d[n][m]);
		in.close();
	}
}
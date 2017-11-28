package week_12th_mon;

import java.util.*;
public class Baek1727_again {

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
		//n�� �׻� ������
		if(n>m){
			int temp = n;
			n = m;
			m = temp;
		}
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		for(int i=0; i<m; i++)
			b[i] = in.nextInt();
		Arrays.sort(a);
		Arrays.sort(b);
		
		for(int i=0; i<n; i++){
			if(i==0){
				d[0][0] = Math.abs(a[0]-b[0]);
				for(int j=1; j<m-(n-i-1); j++)
					d[i][j] = Math.min(d[i][j-1], Math.abs(a[0]-b[j]));
			}
			else{
				for(int j=i; j<j-(m-i-1); j++){
					if(j==i) 
						d[i][j] = d[i-1][j-1] + Math.abs(a[i]-b[j]);
					else
						d[i][j] = Math.min(d[i-1][j-1]+Math.abs(a[i]-b[j]),d[i][j-1]);
				}
			}
		}
		System.out.println(d[n-1][m-1]);
		in.close();
	}
}
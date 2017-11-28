package week_11th_thu;

import java.util.*;
public class Baek1727_dp {

	/*Ŀ�ø����
	 * �ֳ� n
	 * �ֿ� m
	 * �ִ��� ���� Ŀ���� �����, Ŀ���� �̷�� �� ����� ���������� �ּҰ� �ǵ����Ѵ�
	 * 1. ���� ���� Ŀ���� ���� ��
	 * 2. ������ �ּҰ��� ���Ѵ�
	  
	  ���� d[i][j]�� i�����ڰ� j�� ���ڸ� �������� ���� ���̶�� �Ѵ�
	 
	 
	 *  */
	static int n,m, max, ans=987654321;
	static int sum = 0;
	static int[] mc;
	static int[] nc;
	static int[][] a = new int[1001][1001];
	public static void go(int start, int cnt, int total){
		if(cnt==n){
			ans = Math.min(ans, Math.abs(total-sum));
			return;
		}
		
		for(int i=start; i<m; i++)
			go(i+1,cnt+1,total+mc[i]);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		if(n>m){
			int temp = n;
			n = m;
			m = temp;
		}
		
		nc = new int[n+1];
		mc = new int[m+1];
		
		for(int i=0; i<n; i++){
			nc[i] = in.nextInt();
			sum+=nc[i];
		}
		for(int i=0; i<m; i++)
			mc[i] = in.nextInt();
		
		for(int i=0; i<m-n; i++){
			go(i,0,0);
		}
		System.out.println(ans);
		in.close();
	}
}

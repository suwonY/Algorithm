package week_11th_thu;

import java.util.*;
public class Baek1727_dfs {

	/*Ŀ�ø����
	 * �ֳ� n
	 * �ֿ� m
	 * �ִ��� ���� Ŀ���� �����, Ŀ���� �̷�� �� ����� ���������� �ּҰ� �ǵ����Ѵ�
	 * 1. ���� ���� Ŀ���� ���� ��
	 * 2. ������ �ּҰ��� ���Ѵ�
	 * 
	 * �Ҽ��ִ� Įġ�� ���ߴ� . �׳� �̰ŷ� �ð��ʰ����� ��Ǫ�� ������
	 *  */
	static final int INF = 987654321;
	static int n,m,ans=INF;
	static int sum = 0;
	static boolean finish = false;
	static int[] mc;
	public static void go(int start, int cnt, int total){
		if(cnt==n){
			if(ans != INF){
				if(total>ans){
					finish = true;
					return;
				}
			}
			ans = Math.min(ans, Math.abs(sum-total));
			return;
		}
		for(int i=start+1; i<=m-n+cnt; i++){
			go(i,cnt+1,total+mc[i]);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		//m�� �׻� ū��
		//n�� �׻� ������
		//�ִ�Ŀ���� m���� ��
		//nc���� 4���� �κ������� ���ؾߵ�
		if(n>m){
			int temp = n;
			n = m;
			m = temp;
		}
		mc = new int[m];
		
		for(int i=0; i<n; i++){
			sum += in.nextInt();
		}
		for(int i=0; i<m; i++)
			mc[i] = in.nextInt();
		
		Arrays.sort(mc);
		
		for(int i=0; i<=mc.length-n; i++){
			go(i,1,mc[i]);
			if(finish) break;
		}
		System.out.println(ans);
		in.close();
	}
}

package week_12th_mon;

import java.util.*;
public class Baek2515_2nd {
	/*������
	 * 
	 * �׸����� ���̰� �ٸ���
	 * ���α��̰� S�̻��� �׸��� �������� ������ ���̰� ��� �ȴ�
	 * ���̴� ���κκ��� S�̻��� �׸��� �ǸŰ� �����ϴ�
	 * �׸� ���̿� ������ �־��� �� ������ ���� �ִ밡 �ǰ� �����
	 * 
	 * dp�����ϱ� ��ȭ���� �����غ���
	 d[n]�� n���� �׸����� ���� �� �ִ� �ִ��� ����
	 d[n] = max(d[n-1] , d[k] + c[n])
	 (k�� ���� ���̰� �� �տ� ������ ���մ� ģ��)
	 Treemap�� �ߺ����� �ȵǼ� ��Ÿ�ӿ������� �Ƴ�...
	 * */
	static int n,s;//�׸��� ���� , ����
	static long[] d = new long[20000001];
	static Map<Integer,Integer> m = new TreeMap<>();
	static List<Pic> a = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		s = in.nextInt();
		for(int i=0; i<n; i++){
			int h = in.nextInt();
			int c = in.nextInt();
			m.put(h, c);
		}
		
		a.add(new Pic(0,0));
		for(int h : m.keySet())
			a.add(new Pic(h,m.get(h)));
		
		for(int i=1,j=0; i<=n; i++){
			//�� �տ����߿� ���� ���� �� �ִ� �ָ� ã�´�
			while(a.get(j+1).h<=a.get(i).h-s) ++j;
			d[i] = Math.max(d[i-1], d[j]+a.get(i).c);
		}
		
		System.out.println(d[n]);
		in.close();
	}
	public static class Pic{
		int h, c;
		public Pic(int h, int c){
			this.h = h;
			this.c = c;
		}
	}
}
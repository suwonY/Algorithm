package week_12th_mon;

import java.util.*;
public class Baek2515_2nd_fix {
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
	 * */
	static int n,s;//�׸��� ���� , ����
	static int[] d;
	static List<Pic> a = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		s = in.nextInt();
		d = new int[n+1];
		a.add(new Pic(0,0));
		for(int i=0; i<n; i++){
			int h = in.nextInt();
			int c = in.nextInt();
			a.add(new Pic(h,c));
		}
		
		Comparator<Pic> com = new Comparator<Pic>() {
			@Override
			public int compare(Pic o1, Pic o2) {
				if(o1.h>o2.h) return 1;
				else if(o2.h>o1.h)return -1;
				return 0;
			}
		};
		
		Collections.sort(a,com);
		
		for(int i=1,j=0; i<=n; i++){
			//�� �տ����߿� ���� ���� �� �ִ� �ָ� ã�´�
			while(a.get(j+1).h<=a.get(i).h-s) ++j;
//			System.out.println("i�� " + i + " �� ��  // " + "�տ� �� �� �մ� ģ�� : " + j);
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
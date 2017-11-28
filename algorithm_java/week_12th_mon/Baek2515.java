package week_12th_mon;

import java.util.*;
public class Baek2515 {

	/*������
	 * 
	 * �׸����� ���̰� �ٸ���
	 * ���α��̰� S�̻��� �׸��� �������� ������ ���̰� ��� �ȴ�
	 * ���̴� ���κκ��� S�̻��� �׸��� �ǸŰ� �����ϴ�
	 * �׸� ���̿� ������ �־��� �� ������ ���� �ִ밡 �ǰ� �����
	 * 
	 * dp�����ϱ� ��ȭ���� �����غ���
	 d[i]�� i��° �׸��� �������� �� ����� �ִ밪�̶�� �ϸ�
	 d[1]�� 
	 
	 
	 
	 * */
	static int n,s,max=0;//�׸��� ���� , ����
	static int[] d = new int[300001];
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
		
		//Treemap�� �̿��ؼ� ������ list�� ����
		for(int h : m.keySet())
			a.add(new Pic(h,m.get(h)));
		
		max = a.get(n-1).h;	//�ִ� ����
		
		int index = 0;
		for(int i=s; i<=max; i++){
			d[i] = d[i-1];
			if(a.get(index).h==i){
				d[i] = Math.max(d[i], d[i-s]+a.get(index).c);
				++index;
			}
			if(index<n && a.get(index).h==i){
				while(a.get(index).h==i) ++index;
			}
		}
		System.out.println(d[a.get(n-1).h]);
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
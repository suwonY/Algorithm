package week_19th_mon;

import java.util.*;
public class swExpert_2383 {

	/*
	����� ��� p ��� �Ա� s
	pr,pc �� p�� ����, ���� ��ġ
	sr,sc �� s�� ����, ���� ��ġ 
	�̵��ð� : |pr - sr| + |pc - sc|
	 
	����� �������� �ð�
	- ��� �Ա��� ������ �ĺ��� ����� ������ ������ �������� �ð�
	- ��� �Ա��� �����ϸ� 1�� �� �Ʒ�ĭ���� ������ �� �ִ�
	- ��� ������ ���ÿ� �ִ� 3������� �ö� ���� �� �ִ�.
	- �̹� 3���� �������� �ִ� ���, ���� �Ѹ��� ������ ������������ �Ա����� ���
	- ��ܸ��� ����k�� �־�����, ��ܿ� �ö� �� ������ �������µ� k�ʰ� �ɸ���
	
	
	�������:
	4<=n<=10
	1<=k<=10
	��� �Ա��� 2��  2<=length<=10
	 * */
	static int n,k,num=0,ans=0;
	static int[][] a;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static Hole[] hole;
	static List<Person> person;
	static List<String> all;
//	static Queue<Person> q;
	public static void all(String now){
		if(now.length()==num){
			all.add(now);
			return;
		}
		//11 22 12 21 -> 4���� ���
		all(now+"1");
		all(now+"2");
	}
	public static void go(){
		List<Person> first;
		List<Person> second;
		for(String t : all){
			first = new ArrayList<>();
			second = new ArrayList<>();
			//1�� �������� 2���������� �׸��� �������� �̵��� �Ŀ� 
			for(int i=0; i<t.length(); i++){
				//1�� ������� �̵��� ���
				if(t.charAt(i)=='1'){
					person.get(i).hole = 1;	//1�� �������� �̵�����
					person.get(i).time = Math.abs(hole[0].x - person.get(i).x) 
							+ Math.abs(hole[0].y - person.get(i).y);//�̵��� ���� �ð�
					first.add(person.get(i));
				}
				//2�� ������� �̵��� ���
				else{
					person.get(i).hole = 2;
					person.get(i).time = Math.abs(hole[1].x - person.get(i).x) 
							+ Math.abs(hole[1].y - person.get(i).y);
					second.add(person.get(i));
				}
			}
			Collections.sort(first);
			Collections.sort(second);
			System.out.println(first.size());
			//���� 1�� �������� �̵��� �ֵ��� fisrt�� ����� �ְ�
			//2��°�� �̵��� �ֵ��� second�� ����ִ�
			
			int time1 = first.get(0).time+1;
			int index = 0;
			int cnt1 = 0;
			Deque<Person> q = new ArrayDeque<>();
			while(true){
				System.out.println("���� �ð� : " + time1);
				//ó���� �ְ� ���� ���
				if(first.size()==0) break;
					
				//�ִ� �ο���ŭ ���ۿ� �ִ� 3�� ����ֱ�
				if(hole[0].cnt<3 && index<first.size())
					while(true){
						if(first.get(index).time>time1) break;
						System.out.println(index+"��° �ְ� ����� �������� ����");
						Person tt = first.get(index++);
						tt.length = 0;
						q.addLast(tt);
						++hole[0].cnt;
						if(hole[0].cnt==3) break;
						if(index==first.size()) break;
					}
				
				if(q.isEmpty()) break; //���̻� ó���� �� ������ ������
				
				//��� �������� ����ŭ �����ֱ�
				for(int i=0; i<q.size(); i++){
					Person te = q.removeFirst();
					te.length++;
					if(te.length == hole[0].cnt){
						++cnt1;
						System.out.println(cnt1+"���� ����� ������");
						continue;
					}
					q.addLast(te);
				}
				
				++time1;
			}
			System.out.println(time1);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			System.out.println("#"+t+" ");
		}*/
		
		n = in.nextInt();
		a = new int[n][n];
		hole = new Hole[2];
		person = new ArrayList<>();
		all = new ArrayList<>();
		int index = 0;
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++){
				a[i][j] = in.nextInt();
				if(a[i][j]==1) {
					++num;
					person.add(new Person(i,j));
				}
				if(a[i][j]!=0 && a[i][j]!=1)
					hole[index++] = new Hole(i,j,a[i][j]);
			}
		
		all.add("111111");
		go();
		
		in.close();
	}
	public static class Hole{
		int x, y, length, cnt, finish;
		List<Person> per;
		public Hole(int x, int y, int length){
			this.x = x;
			this.y = y;
			this.length = length;
			cnt = finish = 0;
			per = new ArrayList<>();
		}
	}
	public static class Person implements Comparable<Person>{
		int x, y, time, length, hole;
		public Person(int x, int y){
			this.x = x;
			this.y = y;
			length = hole = -10;
		}
		@Override
		public int compareTo(Person o) {
			return this.time - o.time;
		}
	}
}
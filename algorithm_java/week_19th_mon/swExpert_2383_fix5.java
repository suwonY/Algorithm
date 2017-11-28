package week_19th_mon;

import java.util.*;
public class swExpert_2383_fix5 {
	static int n,k,num=0,ans=987654321;
	static int[][] a;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static Hole[] hole;
	static List<Person> person;
	static List<String> all;
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
//			System.out.println("\n���� : " + t);
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
			//���� 1�� �������� �̵��� �ֵ��� fisrt�� ����� �ְ�
			//2��°�� �̵��� �ֵ��� second�� ����ִ�
			Deque<Person> q = new ArrayDeque<>();	//�Ա��� ������� �ֵ�
			Deque<Person> move = new ArrayDeque<>();//��ܿ� �ö�� �ֵ�
			int index = 0, cnt = 0;
			int time1 = 0;
			
			if(first.size()!=0){
				time1 = first.get(0).time;	//ù�� ° ģ���� �Ա��� ����
				finish:
				while(true){
					//������ �ֵ��� �Ա��� ��� ������
					while(true){
						if(index>=first.size()) break;
						Person temp = first.get(index);
						if(temp.time>time1) break;
						temp.length = -1; //�Ա��� ����� -1 �� �����
						q.addLast(temp);
						++index;
						temp.index = index;
//						System.out.println(index+"�ְ� �Ա��� ������");
					}
						
					//�Ա��� ������ �ֵ� �߿��� ������ �ֵ��� �������
					while(hole[0].cnt<3){
						if(q.isEmpty()) break;//�̵���ų ģ�� ������ ������
//						System.out.println(q.getFirst().index+"��°�� �������� ����");
						move.addLast(q.removeFirst());
					}
					
					for(int i=0; i<move.size(); i++){
						Person temp = move.removeFirst();
						++temp.length;
						if(temp.length == hole[0].length){
							++cnt;
							if(cnt==first.size()) break finish;
							--hole[0].cnt;
							if(q.isEmpty()) continue;
							//���࿡ �Ա��� ������� ģ���� �ִٸ� �������� �������� �ٲ��� ->0
							Person next = q.remove();
							next.length = 0;
							if(next.time ==time1) next.length = -1;
							move.add(next);
							++hole[0].cnt;
							continue;
						}
						move.addLast(temp);
					}
					++time1;
				}
			}
			q.clear(); move.clear();
			index = cnt = 0;
			int time2 = 0;
			
			if(second.size()!=0){
				time2 = second.get(0).time;	//ù�� ° ģ���� �Ա��� ����
				finish:
				while(true){
					//������ �ֵ��� �Ա��� ��� ������
					while(true){
						if(index>=second.size()) break;
						Person temp = second.get(index);
						if(temp.time>time2) break;
						temp.length = -1; //�Ա��� ����� -1 �� �����
						q.addLast(temp);
						++index;
						temp.index = index;
//							System.out.println(index+"�ְ� �Ա��� ������");
					}
						
					//�Ա��� ������ �ֵ� �߿��� ������ �ֵ��� �������
					while(hole[1].cnt<3){
						if(q.isEmpty()) break;//�̵���ų ģ�� ������ ������
//							System.out.println(q.getFirst().index+"��°�� �������� ����");
						move.addLast(q.removeFirst());
					}
					
					for(int i=0; i<move.size(); i++){
						Person temp = move.removeFirst();
						++temp.length;
						if(temp.length == hole[1].length){
							++cnt;
							if(cnt==second.size()) break finish;
							--hole[0].cnt;
							if(q.isEmpty()) continue;
							//���࿡ �Ա��� ������� ģ���� �ִٸ� �������� �������� �ٲ��� ->0
							Person next = q.remove();
							next.length = 0;
							if(next.time ==time2) next.length = -1;
							next.length = 0;
							move.add(next);
							++hole[1].cnt;
							continue;
						}
						move.addLast(temp);
					}
					++time2;
				}
			}
			
			int temp = Math.max(time1, time2);
//			System.out.println(time1 + " " + time2);
			ans = Math.min(ans, temp);
			
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			a = new int[n][n];
			hole = new Hole[2];
			person = new ArrayList<>();
			all = new ArrayList<>();
			num = 0;
			ans = 987654321;
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
			
			all("");
//			all.add("111111");
//			all.add("111112");
			go();
			System.out.println("#"+t+" "+ans);
		}
		
		in.close();
	}
	public static class Hole{
		int x, y, length, cnt;
		public Hole(int x, int y, int length){
			this.x = x;
			this.y = y;
			this.length = length;
			cnt = 0;
		}
	}
	public static class Person implements Comparable<Person>{
		int x, y, time, length, hole, index;
		public Person(int x, int y){
			this.x = x;
			this.y = y;
			length = -1;
			hole = -10;
		}
		@Override
		public int compareTo(Person o) {
			return this.time - o.time;
		}
	}
}
package week_19th_mon;

import java.util.*;
public class swExpert_2383_fix3 {
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
			Deque<Person> q = new ArrayDeque<>();
			Deque<Person> qt = new ArrayDeque<>();
			int index = 0, cnt = 0;
			int time1 = 0;
			if(first.size()!=0){
				time1 = first.get(0).time;
				while(true){
					if(hole[0].cnt<3 && index<first.size()){
						while(true){
							if(first.get(index).time>time1) break;
							Person tt = first.get(index++);
							tt.length = -1;
							q.addLast(tt);
							++hole[0].cnt;
							if(hole[0].cnt==3) break;
							if(index==first.size()) break;
						}
					}
					
					//�׻��̿� �����Ѿֵ��� ������ ����
					if(index<first.size()){
						while(true){
							if(first.get(index).time>time1) break;
							if(hole[0].cnt==3) break;
							Person tt = first.get(index++);
							tt.length = 0;
							qt.addLast(tt);
							if(index==first.size()) break;
						}
					}
					
					//�̹� ������ �ֵ��� ��������
					for(int i=0; i<q.size(); i++){
						Person tt = q.removeFirst();
						tt.length++;
						if(tt.length == hole[0].length){
							++cnt;
							--hole[0].cnt;
							continue;
						}
						q.addLast(tt);
					}
					if(cnt==first.size()) break;
					
					//��� ���� ������ ģ���� �ִٸ� �Ա��� ������ �ֵ��� ��������
					if(hole[0].cnt<3 && !qt.isEmpty()){
						while(true){
							Person tt = qt.remove();
							tt.length = 1;
							q.add(tt);
							++hole[0].cnt;
							if(hole[0].cnt==3) break;
							break;
						}
					}
					++time1;
				}
			}
//			time1 = time1+1;
//			System.out.println("1��° ���� �ð� : " + time1);
			
			q.clear();
			qt.clear();
			index = cnt = 0;
			int time2 = 0;
			if(second.size()!=0){
//				System.out.println("\n2�� ��Ȳ \n");
				time2 = second.get(0).time;
//				System.out.println("2�� ũ�� : " +hole[1].length);
//				System.out.println("���۽ð� : " + time2);
				while(true){
//					System.out.println("\n���� �ð� : " + time2);
//					System.out.println("���� ��� �������� ģ������ �� : " + hole[1].cnt);
//					System.out.println("�� �ο� �� : " + second.size());
//					System.out.println("�� ������ �ο� �� : " + cnt);
					if(hole[1].cnt<3 && index<second.size()){
						while(true){
							if(second.get(index).time>time2) break;
							Person tt = second.get(index++);
							tt.length = -1;
							q.addLast(tt);
							++hole[1].cnt;
//							System.out.println(index+"��° �ְ� ����� �������� ����");
							if(hole[1].cnt==3) break;
							if(index==second.size()) break;
						}
					}
					
					//�׻��̿� �����Ѿֵ��� ������ ����
					if(index<second.size()){
						while(true){
							if(second.get(index).time>time2) break;
							if(hole[1].cnt==3) break;
							Person tt = second.get(index++);
							tt.length = 0;
							qt.addLast(tt);
//							System.out.println(index+"��° ģ���� ����Ա��� ����");
							if(index==second.size()) break;
						}
					}
					
					//�̹� ������ �ֵ��� ��������
					for(int i=0; i<q.size(); i++){
						Person tt = q.removeFirst();
						tt.length++;
						if(tt.length == hole[1].length){
							++cnt;
//							System.out.println(cnt+"���� ����� ������");
							--hole[1].cnt;
							continue;
						}
						q.addLast(tt);
					}
					if(cnt==second.size()) break;
					
					//��� ���� ������ ģ���� �ִٸ� �Ա��� ������ �ֵ��� ��������
					if(hole[1].cnt<3 && !qt.isEmpty()){
						while(true){
							Person tt = qt.remove();
							tt.length = 1;
							q.add(tt);
							++hole[1].cnt;
							if(hole[1].cnt==3) break;
							break;
						}
					}
					++time2;
				}
			}
//			time2 = time2 + 1;
//			System.out.println("2��° ���� �ð� : " + time2);
//			System.out.println(time1+" "+time2);
			int cc = Math.max(time1, time2);
			ans = Math.min(cc,ans);
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
//			all.add("111222");
			go();
			System.out.println("#"+t+" "+ans);
		}
		
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
		int x, y, time, length, hole, start, end;
		public Person(int x, int y){
			this.x = x;
			this.y = y;
			length = hole = -10;
			start = end = 0;
		}
		@Override
		public int compareTo(Person o) {
			return this.time - o.time;
		}
	}
}
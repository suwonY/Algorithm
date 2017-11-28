package week_19th_mon;

import java.util.*;
public class swExpert_2383_fix1 {
	static int n,k,num=0,ans=0;
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
		//11 22 12 21 -> 4가지 경우
		all(now+"1");
		all(now+"2");
	}
	public static void go(){
		List<Person> first;
		List<Person> second;
		for(String t : all){
			first = new ArrayList<>();
			second = new ArrayList<>();
			//1번 구멍인지 2번구멍인지 그리고 구멍으로 이동한 후에 
			for(int i=0; i<t.length(); i++){
				//1번 계단으로 이동할 경우
				if(t.charAt(i)=='1'){
					person.get(i).hole = 1;	//1번 구멍으로 이동했음
					person.get(i).time = Math.abs(hole[0].x - person.get(i).x) 
							+ Math.abs(hole[0].y - person.get(i).y);//이동한 후의 시간
					first.add(person.get(i));
				}
				//2번 계단으로 이동할 경우
				else{
					person.get(i).hole = 2;
					person.get(i).time = Math.abs(hole[1].x - person.get(i).x) 
							+ Math.abs(hole[1].y - person.get(i).y);
					second.add(person.get(i));
				}
			}
			Collections.sort(first);
			Collections.sort(second);
			//현재 1번 구멍으로 이동한 애들은 fisrt에 담겨져 있고
			//2번째로 이동한 애들은 second에 담겨있다
			
			int time1 = first.get(0).time+1;
			int index = 0, cnt = 0;
			Deque<Person> q = new ArrayDeque<>();
			while(true){
				System.out.println("현재 시간 : " + time1);
				System.out.println("현재 계단 내려가는 친구들의 수 : " + hole[0].cnt);
				System.out.println("총 인원 수 : " + first.size());
				System.out.println("총 내려간 인원 수 : " + cnt);
				//있는 인원만큼 구멍에 최대 3명 집어넣기
				if(hole[0].cnt<3 && index<first.size())
					while(true){
						if(first.get(index).time>time1) break;
						Person tt = first.get(index++);
						tt.length = 0;
						q.addLast(tt);
						++hole[0].cnt;
						System.out.println(index+"번째 애가 계단을 내려가기 시작");
						if(hole[0].cnt==3) break;
						if(index==first.size()) break;
					}
				
				if(q.size()==0) break;
				
				/*if(index==first.size()) break;
				if(hole[0].cnt==3){
					hole[0].cnt = 0;
					time1+=hole[0].length;
					continue;
				}*/
				if(time1==5){
					System.out.println();
				}
				for(int i=0; i<q.size(); i++){
					Person tt = q.removeFirst();
					tt.length++;
					if(tt.length == hole[0].length){
						++cnt;
						System.out.println(cnt+"명이 계단을 내려감");
						--hole[0].cnt;
						continue;
					}
					q.addLast(tt);
				}
				if(cnt==first.size()) break;
				++time1;
				System.out.println();
			}
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
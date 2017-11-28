package week_19th_mon;

import java.util.*;
public class swExpert_2383_fix2 {
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
		//11 22 12 21 -> 4가지 경우
		all(now+"1");
		all(now+"2");
	}
	public static void go(){
		List<Person> first;
		List<Person> second;
		for(String t : all){
			System.out.println("\n현재 : " + t);
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
//					System.out.println("1에 들어간 친구 : " + person.get(i).x + " " + person.get(i).y + " 구멍 과의 거리 : " + person.get(i).time);
				}
				//2번 계단으로 이동할 경우
				else{
					person.get(i).hole = 2;
					person.get(i).time = Math.abs(hole[1].x - person.get(i).x) 
							+ Math.abs(hole[1].y - person.get(i).y);
					second.add(person.get(i));
//					System.out.println("2에 들어간 친구 : " + person.get(i).x + " " + person.get(i).y + " 구멍 과의 거리 : " + person.get(i).time);
				}
			}
			Collections.sort(first);
			Collections.sort(second);
			//현재 1번 구멍으로 이동한 애들은 fisrt에 담겨져 있고
			//2번째로 이동한 애들은 second에 담겨있다
			Deque<Person> q = new ArrayDeque<>();
			int index = 0, cnt = 0;
			int time1 = 0, plus = 0;
			boolean f = true;
			if(first.size()!=0){
//				System.out.println("\n1번 상황 \n");
				time1 = first.get(0).time+1;
//				System.out.println("시작시간 : " + time1);
				while(true){
//					System.out.println("현재 시간 : " + time1);
//					System.out.println("현재 계단 내려가는 친구들의 수 : " + hole[0].cnt);
//					System.out.println("총 인원 수 : " + first.size());
//					System.out.println("총 내려간 인원 수 : " + cnt);
					//있는 인원만큼 구멍에 최대 3명 집어넣기
					if(hole[0].cnt<3 && index<first.size()){
						while(true){
							if(first.get(index).time>time1) break;
							Person tt = first.get(index++);
							tt.length = 0;
							q.addLast(tt);
							++hole[0].cnt;
							f = false;
//							System.out.println(index+"번째 애가 계단을 내려가기 시작");
							if(hole[0].cnt==3) break;
							if(index==first.size()) break;
						}
					}
					for(int i=0; i<q.size(); i++){
						Person tt = q.removeFirst();
						tt.length++;
						if(tt.length == hole[0].length){
							++cnt;
//							System.out.println(cnt+"명이 계단을 내려감");
							--hole[0].cnt;
							continue;
						}
						q.addLast(tt);
					}
					if(cnt==first.size()) break;
					if(f && q.isEmpty()) {
						f = false;
						++plus;
					}
					++time1;
				}
			}
			time1 = time1 + plus +1;
//			System.out.println("1번째 최종 시간 : " + time1);
			
			q.clear();
			index = cnt = 0;
			int time2 = 0;
			plus = 0;
			f = true;
			if(second.size()!=0){
//				System.out.println("\n2번 상황 \n");
				time2 = second.get(0).time+1;
				System.out.println("2번 크기 : " +hole[1].length);
				System.out.println("2번 구멍 위치 : " + hole[1].x + " " + hole[1].y);
				System.out.println("시작시간 : " + time2);
				while(true){
					System.out.println("\n현재 시간 : " + time2);
					System.out.println("현재 계단 내려가는 친구들의 수 : " + hole[1].cnt);
					System.out.println("총 인원 수 : " + second.size());
					System.out.println("총 내려간 인원 수 : " + cnt);
					if(hole[1].cnt<3 && index<second.size()){
						while(true){
							if(second.get(index).time>time2) break;
							Person tt = second.get(index++);
							tt.length = 0;
							q.addLast(tt);
							++hole[1].cnt;
							System.out.println(index+"번째 애가 계단을 내려가기 시작");
							f = true;
							if(hole[1].cnt==3) break;
							if(index==second.size()) break;
						}
					}
						
					for(int i=0; i<q.size(); i++){
						Person tt = q.removeFirst();
						tt.length++;
						if(tt.length == hole[1].length){
							++cnt;
							System.out.println(cnt+"명이 계단을 내려감");
							--hole[1].cnt;
							continue;
						}
						q.addLast(tt);
					}
					if(cnt==second.size()) break;
					if(f && q.isEmpty()) {
						f = false;
						++plus;
					}
					++time2;
				}
			}
			time2 = time2 + plus + 1;
//			System.out.println("2번째 최종 시간 : " + time2);
			System.out.println(time1+" "+time2);
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
			
//			all("");
			all.add("1122122");
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
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
		//11 22 12 21 -> 4가지 경우
		all(now+"1");
		all(now+"2");
	}
	public static void go(){
		List<Person> first;
		List<Person> second;
		for(String t : all){
//			System.out.println("\n현재 : " + t);
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
					
					//그사이에 도착한애들이 있으면 저장
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
					
					//이미 도착한 애들은 내려보냄
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
					
					//계단 끝에 도달한 친구가 있다면 입구에 도착한 애들을 대기시켜줌
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
//			System.out.println("1번째 최종 시간 : " + time1);
			
			q.clear();
			qt.clear();
			index = cnt = 0;
			int time2 = 0;
			if(second.size()!=0){
//				System.out.println("\n2번 상황 \n");
				time2 = second.get(0).time;
//				System.out.println("2번 크기 : " +hole[1].length);
//				System.out.println("시작시간 : " + time2);
				while(true){
//					System.out.println("\n현재 시간 : " + time2);
//					System.out.println("현재 계단 내려가는 친구들의 수 : " + hole[1].cnt);
//					System.out.println("총 인원 수 : " + second.size());
//					System.out.println("총 내려간 인원 수 : " + cnt);
					if(hole[1].cnt<3 && index<second.size()){
						while(true){
							if(second.get(index).time>time2) break;
							Person tt = second.get(index++);
							tt.length = -1;
							q.addLast(tt);
							++hole[1].cnt;
//							System.out.println(index+"번째 애가 계단을 내려가기 시작");
							if(hole[1].cnt==3) break;
							if(index==second.size()) break;
						}
					}
					
					//그사이에 도착한애들이 있으면 저장
					if(index<second.size()){
						while(true){
							if(second.get(index).time>time2) break;
							if(hole[1].cnt==3) break;
							Person tt = second.get(index++);
							tt.length = 0;
							qt.addLast(tt);
//							System.out.println(index+"번째 친구가 계단입구에 도착");
							if(index==second.size()) break;
						}
					}
					
					//이미 도착한 애들은 내려보냄
					for(int i=0; i<q.size(); i++){
						Person tt = q.removeFirst();
						tt.length++;
						if(tt.length == hole[1].length){
							++cnt;
//							System.out.println(cnt+"명이 계단을 내려감");
							--hole[1].cnt;
							continue;
						}
						q.addLast(tt);
					}
					if(cnt==second.size()) break;
					
					//계단 끝에 도달한 친구가 있다면 입구에 도착한 애들을 대기시켜줌
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
//			System.out.println("2번째 최종 시간 : " + time2);
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
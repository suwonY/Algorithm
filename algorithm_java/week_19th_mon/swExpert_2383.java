package week_19th_mon;

import java.util.*;
public class swExpert_2383 {

	/*
	방안의 사람 p 계단 입구 s
	pr,pc 는 p의 세로, 가로 위치
	sr,sc 는 s의 세로, 가로 위치 
	이동시간 : |pr - sr| + |pc - sc|
	 
	계단을 내려가는 시간
	- 계단 입구에 도착한 후부터 계단을 완전히 내려갈 때까지의 시간
	- 계단 입구에 도착하면 1초 후 아래칸으로 내려갈 수 있다
	- 계단 위에는 동시에 최대 3명까지만 올라가 있을 수 있다.
	- 이미 3명이 내려가고 있는 경우, 그중 한명이 완전히 내려갈때까지 입구에서 대기
	- 계단마다 길이k가 주어지며, 계단에 올라간 후 완전히 내려가는데 k초가 걸린다
	
	
	제약사항:
	4<=n<=10
	1<=k<=10
	계단 입구는 2개  2<=length<=10
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
			System.out.println(first.size());
			//현재 1번 구멍으로 이동한 애들은 fisrt에 담겨져 있고
			//2번째로 이동한 애들은 second에 담겨있다
			
			int time1 = first.get(0).time+1;
			int index = 0;
			int cnt1 = 0;
			Deque<Person> q = new ArrayDeque<>();
			while(true){
				System.out.println("현재 시간 : " + time1);
				//처리할 애가 없는 경우
				if(first.size()==0) break;
					
				//있는 인원만큼 구멍에 최대 3명 집어넣기
				if(hole[0].cnt<3 && index<first.size())
					while(true){
						if(first.get(index).time>time1) break;
						System.out.println(index+"번째 애가 계단을 내려가기 시작");
						Person tt = first.get(index++);
						tt.length = 0;
						q.addLast(tt);
						++hole[0].cnt;
						if(hole[0].cnt==3) break;
						if(index==first.size()) break;
					}
				
				if(q.isEmpty()) break; //더이상 처리할 게 없으면 끝내기
				
				//계단 내려가는 수만큼 더해주기
				for(int i=0; i<q.size(); i++){
					Person te = q.removeFirst();
					te.length++;
					if(te.length == hole[0].cnt){
						++cnt1;
						System.out.println(cnt1+"명이 계단을 내려감");
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
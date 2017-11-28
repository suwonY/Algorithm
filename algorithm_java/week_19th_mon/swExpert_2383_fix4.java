package week_19th_mon;

import java.util.*;
public class swExpert_2383_fix4 {
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
			Deque<Person> move = new ArrayDeque<>();
			int index = 0, cnt = 0;
			int time1 = 0;
			if(t=="111112"){
				System.out.println();
			}
			if(first.size()!=0){
				time1 = first.get(0).time;	//첫번 째 친구가 입구에 도착
				
				while(true){
//					System.out.println("\n현재 시간 : " + time1);
//					이미 도착한 애들 있으면 움직일 애들로 만들기
					while(!q.isEmpty()){
						if(hole[0].cnt==3) break;
						Person temp = q.removeFirst();
						temp.length = -1;
						move.addLast(temp);
						++hole[0].cnt;
					}
					
					//이 시간에 입구에 도착한 친구가 있다면 q에 넣기
					for(Person temp : first){
						if(temp.time>time1) break;
						if(temp.time == time1){
							Person arrive = first.get(index++);
							arrive.index = index;
//							System.out.println(index+"번째 친구 입구에 도착");
							q.addLast(arrive);
						}
					}
					
					//움직일 애들은 한칸 씩 움직이기
					for(int i=0; i<move.size(); i++){
						Person temp = move.removeFirst();
						temp.length++;
//						System.out.println(temp.index+"번 친구 계단 현재 : " + temp.length +" 만큼 내려감");
						if(temp.length==hole[0].length){//다 움직였다면
							--hole[0].cnt;//다 움직였다는 것을 카운트해주고
							++cnt;
//							System.out.println(temp.index+"번 친구 계단 다 내려감");
							if(!q.isEmpty()){
								Person next = q.removeFirst();
								next.length = 0;
								//만약에 도착하자마자 다 끝나서 움직여야되는상황이면
								if(next.time == time1) next.length = -1;
//								System.out.println(next.index+"번 친구 계단 현재 : " + next.length+" 만큼 내려감");
								++hole[0].cnt;
								move.addLast(next);
							}
							continue;
						}
						move.addLast(temp);
					}
					if(cnt==first.size()) break;
					++time1;
				}
//				System.out.println(time1);
			}
			q.clear(); move.clear();
			index = cnt = 0;
			int time2 = 0;
			if(second.size()!=0){
				time2 = second.get(0).time;	//첫번 째 친구가 입구에 도착
				
				while(true){
//					System.out.println("\n현재 시간 : " + time2);
					//이미 도착한 애들 있으면 움직일 애들로 만들기
					while(!q.isEmpty()){
						if(hole[1].cnt==3) break;
						Person temp = q.removeFirst();
						temp.length = -1;
						move.addLast(temp);
						++hole[1].cnt;
					}
					
					//이 시간에 입구에 도착한 친구가 있다면 q에 넣기
					for(Person temp : second){
						if(temp.time>time2) break;
						if(temp.time == time2){
							Person arrive = second.get(index++);
							arrive.index = index;
//							System.out.println(index+"번째 친구 입구에 도착");
							q.addLast(arrive);
						}
					}
					
					//움직일 애들은 한칸 씩 움직이기
					for(int i=0; i<move.size(); i++){
						Person temp = move.removeFirst();
						temp.length++;
//						System.out.println(temp.index+"번 친구 계단 현재 : " + temp.length +" 만큼 내려감");
						if(temp.length==hole[1].length){//다 움직였다면
							--hole[1].cnt;//다 움직였다는 것을 카운트해주고
							++cnt;
//							System.out.println(temp.index+"번 친구 계단 다 내려감");
							if(!q.isEmpty()){
								Person next = q.removeFirst();
								next.length = 0;
								//만약에 도착하자마자 다 끝나서 움직여야되는상황이면
								if(next.time == time2) next.length = -1;
//								System.out.println(next.index+"번 친구 계단 현재 : " + next.length+" 만큼 내려감");
								++hole[1].cnt;
								move.addLast(next);
							}
							continue;
						}
						move.addLast(temp);
					}
					if(cnt==second.size()) break;
					++time2;
				}
//				System.out.println(time1 + " " + time2);
				int temp = Math.max(time1, time2);
				ans = Math.min(ans, temp);
			}
			
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
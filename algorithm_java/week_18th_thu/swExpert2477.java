package week_18th_thu;
import java.util.*;
public class swExpert2477 {
	/*
	 n개의 접수 창구와 m개의 정비 창구
	 접수창구에서 고장을 접수하고 정비창구에서 차량을 정비받는다
	 접수창구에서는 처리시간 a
	 정비창구 처리시간 b
	 
	 빈 창구가 있으면 접수하고 아니면 기다린다
	 빈 창구가 있으면 정비하고 아니면 기다린다
	 
	 접수창구의 우선순위는 아래와 같다
	 1. 여러 고객이 기다리는 경우, 번호가 낮은 순서대로 우선 접수
	 2. 빈 창구가 여러 곳인 경우 창구번호가 낮은 곳으로 간다.
	 
	 1. 먼저 기다리는 고객이 우선한다.
	 2. 동시에 같은 창구로 오면 창구번호 낮은 순서가 먼저 받는다
	 3. 빈 곳이 여러 곳일 경우 창구번호가 작은 곳으로 간다
	 
	 -> 도착 시간, 창구 처리시간이 주어질 때 지갑을 분실한 고객과 같은 
	 창구를 이용한 고개 번호의 합을 구하라
	 * */
	static int n,m,k,endA,endB,cnt,ans,max,min=987654321;
	static int[] a,b,time, rec, fix;
	static int[] situationA, situationB;
	static int[] c1,c2;
	static Person[] cus, finish;
	public static void go(){
		Deque<Person> wait1 = new ArrayDeque<>();
		Deque<Person> wait2 = new ArrayDeque<>();
		int total = 0;
		int index = 0;//몇번째 사람인지 체크
		int now = min-1;
		boolean fin = false;
		while(!fin){
			System.out.println("현재 시간 : " + now);
			System.out.println("고객 등장 시간 : " + time[index]);
			//끝나는 애들부터 계산
			for(int i=0; i<k; i++){
				if(situationB[i]==0){//정비시간까지 끝났다면
					System.out.println(i+"번 고객 완료");
					finish[i] = cus[i];
					++total;
				}
			}
			
			if(total==k){
				System.out.println("종료");
				break;
			}
			
			//고객이 오면 고객을 만든다
			if(time[index]==now){
				System.out.println(index+" 번 고객 등장");
				cus[index] = new Person();
				cus[index].setStart(index);//등록번호를 넣어준다
				wait1.addLast(cus[index]);//점수대기자로 집어넣는다
				++index;
			}
			
			//접수창구를 기다리는 사람이 있다면
			while(!wait1.isEmpty()){
				boolean next = true;
				Person t;
				for(int i=0; i<n; i++){ //접수창구 검색
					if(c1[i]==0){ 		//접수창구가 비어있다면
						System.out.println(wait1.getFirst().start+"번째 손님 :" + i +" 번째 창구로 이동");
						t = wait1.removeFirst();
						situationA[t.start]=a[i];//기다리는 시간을 갱신
						t.setAnum(i);//점수창구번호 입력
						cus[t.start] = t; //고객 정보를 갱신
						c1[i] = a[i];//손님이 들어갔으니깐 창구 시간을 채워줌
						next = false;
						break;
					}
				}
				if(next) break;	//빈 창구가 없음
			}
			//접수업무가 끝난 사람을 wait2에 집어넣어야 된다
			//접수를 이미 했고, 접수 가 완료된 거를 채크해야된다
			for(int i=0; i<k; i++){
				if(cus[i]!=null && situationA[i]==0){	//점수를 했었고, 접수업무가 끝났으면
					wait2.addLast(cus[i]);
					situationA[i] = -1;
				}
			}
			while(!wait2.isEmpty()){
				boolean next = true;
				Person t;
				for(int i=0; i<m; i++){
					if(c2[i]==0){
						System.out.println(wait2.getFirst().start+"번째 손님: "+i+" 번째 창구로 이동");
						t = wait2.removeFirst();
						situationB[t.start]=a[i];
						t.setBnum(i);
						cus[t.start] = t;
						c2[i] = a[i];
						next = false;
						break;
					}
				}
				if(next) break;
			}
			
			++now;//시간증가
			for(int i=0; i<n; i++){
				--situationA[i];
				--c1[i];
			}
			for(int i=0; i<m; i++){
				--situationB[i];
				--c2[i];
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		int endA = in.nextInt()-1;
		int endB = in.nextInt()-1;
		
		int[] a = new int[n];
		int[] rec = new int[n];
		int[] c1 = new int[n];
		int[] b = new int[m];
		int[] fix = new int[m];
		int[] c2 = new int[m];
		Person[] cus = new Person[k+1];
		Person[] finish = new Person[k+1];
		int[] situationA = new int[21];
		int[] situationB = new int[21];
		int[] time = new int[k];
		Arrays.fill(situationA, -1);
		Arrays.fill(situationB, -1);
		ans = 1;
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		for(int i=0; i<m; i++)
			b[i] = in.nextInt();
		for(int i=0; i<k; i++) {
			time[i] = in.nextInt();
			min = Math.min(time[i], min);
		}
		Arrays.sort(time);
		
		Deque<Person> wait1 = new ArrayDeque<>();
		Deque<Person> wait2 = new ArrayDeque<>();
		int total = 0;
		int index = 0;//몇번째 사람인지 체크
		int now = min;
		boolean fin = false;
		while(!fin){
			System.out.println("현재 시간 : " + now);
			//끝나는 애들부터 계산
			for(int i=0; i<k; i++){
				if(situationB[i]==0){//정비시간까지 끝났다면
					++total;
					System.out.println(total+"명의 고객 완료");
					finish[i] = cus[i];
				}
			}
			
			if(total==k){
				System.out.println("종료");
				break;
			}
			
			//고객이 오면 고객을 만든다
			if(index<k && time[index]==now){
				System.out.println(index+" 번 고객 등장");
				cus[index] = new Person();
				cus[index].setStart(index);//등록번호를 넣어준다
				wait1.addLast(cus[index]);//점수대기자로 집어넣는다
				++index;
			}
			
			//접수창구를 기다리는 사람이 있다면
			while(!wait1.isEmpty()){
				boolean next = true;
				Person t;
				for(int i=0; i<n; i++){ //접수창구 검색
					if(c1[i]==0){ 		//접수창구가 비어있다면
						System.out.println(wait1.getFirst().start+"번째 손님 :" + i +" 번째 접수창구로 이동");
						t = wait1.removeFirst();
						situationA[i]=a[i];//기다리는 시간을 갱신
						t.setAnum(i);//점수창구번호 입력
						cus[t.start] = t; //고객 정보를 갱신
						c1[i] = a[i];//손님이 들어갔으니깐 창구 시간을 채워줌
						next = false;
						break;
					}
				}
				if(next) break;	//빈 창구가 없음
			}
			//접수업무가 끝난 사람을 wait2에 집어넣어야 된다
			//접수를 이미 했고, 접수 가 완료된 거를 채크해야된다
			for(int i=0; i<k; i++){
				if(cus[i]!=null && situationA[i]==0){	//점수를 했었고, 접수업무가 끝났으면
					wait2.addLast(cus[i]);
					situationA[i] = -1;
				}
			}
			
			while(!wait2.isEmpty()){
				boolean next = true;
				Person t;
				for(int i=0; i<m; i++){
					if(c2[i]==0){
						System.out.println(wait2.getFirst().start+"번째 손님: "+i+" 번째 정비창구로 이동");
						t = wait2.removeFirst();
						situationB[i]=b[i];
						t.setBnum(i);
						cus[t.start] = t;
						c2[i] = b[i];
						next = false;
						break;
					}
				}
				if(next) break;
			}
			
			++now;//시간증가
			for(int i=0; i<n; i++){
				if(situationA[i]>0) --situationA[i];
				if(c1[i]>0) --c1[i];
			}
			for(int i=0; i<m; i++){
				if(situationB[i]>0) --situationB[i];
				if(c2[i]>0) --c2[i];
			}
		}
		for(int i=0; i<k; i++){
			Person tt = cus[i];
			System.out.println(tt.start+"번째 고객 : " +" 접수번호 : " + tt.anum + " 정비번호 : " + tt.bnum);
		}
		in.close();
	}
	public static class Person{
		int start, anum, bnum;
		public Person(){
			start = anum = bnum = -10;
		}
		public void setStart(int num){
			start = num;
		}
		public void setAnum(int num){
			anum = num;
		}
		public void setBnum(int num){
			bnum = num;
		}
	}
}
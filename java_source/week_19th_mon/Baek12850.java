package week_19th_mon;

import java.util.*;
public class Baek12850 {
	/*
	 0학생회관 	- 1 3
	 1형남	- 0 2
	 2한경직	- 1 3 5
	 3진리 	- 0 2 4
	 4신앙	- 2 3 5 6
	 5미래	- 2 4 6 7
	 6전산	- 4 5 7
	 7정보	- 5 6
	 * */	
	static final int mod = 1000000007;
	static long ans = 0, time = 0;
	public static void go(long start, long cnt){
		if(cnt==time){
			if(start!=7)return;
			++ans;
			ans %= mod;
			return;
		}
		if(start==0){
			go(1,cnt+1);
			go(3,cnt+1);
		}
		if(start==1){
			go(0,cnt+1);
			go(2,cnt+1);
		}
		if(start==2){
			go(1,cnt+1);
			go(3,cnt+1);
			go(5,cnt+1);
		}
		if(start==3){
			go(0,cnt+1);
			go(2,cnt+1);
			go(4,cnt+1);
		}
		if(start==4){
			go(2,cnt+1);
			go(3,cnt+1);
			go(5,cnt+1);
			go(6,cnt+1);
		}
		if(start==5){
			go(2,cnt+1);
			go(4,cnt+1);
			go(6,cnt+1);
			go(7,cnt+1);
		}
		if(start==6){
			go(4,cnt+1);
			go(5,cnt+1);
			go(7,cnt+1);
		}
		if(start==7){
			go(5,cnt+1);
			go(6,cnt+1);
		}
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		time = in.nextInt();
		go(7,0);
		System.out.println(ans);
		in.close();
	}
}
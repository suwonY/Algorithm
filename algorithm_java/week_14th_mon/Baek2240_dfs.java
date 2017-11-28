package week_14th_mon;

import java.util.*;
public class Baek2240_dfs {
	static int t,w,ans=0;
	static int[] a = new int[1001];
	public static int change(int num){
		return num==1?2:1;
	}
	public static void go(int cnt, int time, int total, int tree){
		if(time==6){
			ans = Math.max(ans, total);
			return;
		}
		if(cnt==w){	//움직인 횟수가 다 끝났다면
			for(int i=time; i<t; i++)
				if(a[i]==tree)
					++total;
			ans = Math.max(ans, total);
			return;
		}
		
		if(a[time]==tree)
			++total;
		++time;
		
		go(cnt,time,total,tree);
		go(cnt+1,time,total,change(tree));
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		t = in.nextInt();
		w = in.nextInt();
		for(int i=0; i<t; i++)
			a[i] = in.nextInt();
		
		go(0,0,0,1);
		System.out.println(ans);
		in.close();
	}
}

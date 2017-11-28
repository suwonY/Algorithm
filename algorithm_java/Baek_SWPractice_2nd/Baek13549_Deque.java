package Baek_SWPractice_2nd;

import java.util.*;
public class Baek13549_Deque {
	static int n,k;
	static int[] d = new int[1000001];
	static boolean[] c = new boolean[1000001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		Deque<Integer> q = new ArrayDeque<>();
		q.add(n);
		d[n]=0;
		c[n] = true;
		while(!q.isEmpty()){
			int now = q.remove();
			if(now==k){
				q.clear();
				break;
			}
			for(int next : new int[]{now*2, now-1, now+1}){
				if(next<0 || next>=1000001) continue;
				if(c[next]) continue;
				if(now*2==next){
					q.addFirst(next);
					d[next] = d[now];
					c[next] = true;
				}
				else{
					q.addLast(next);
					d[next] = d[now]+1;
					c[next] = true;
				}
			}
		}
		System.out.println(d[k]);
		in.close();
	}
}
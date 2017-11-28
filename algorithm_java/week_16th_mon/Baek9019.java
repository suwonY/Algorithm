package week_16th_mon;

import java.util.*;
public class Baek9019 {

	// DSLR
	// 풀어보기
	static int[] d;
	static char[] from;
	static int[] to;
	static boolean[] c;
	//n을 2배로 바꾼다
	public static int D(int n) {
		return (2*n)%10000;
	}
	public static int S(int n) {
		return n==0?9999:n-1;
	}
	public static int L(int n) {
		return n%1000*10+n/1000;
	}
	public static int R(int n) {
		return n%10*1000+n/10;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int tc = in.nextInt();
		for (int t = 0; t < tc; t++) {
			int n = in.nextInt();
			int m = in.nextInt();
			d = new int[10001];
			from = new char[10001];
			to = new int[10001];
			c = new boolean[10001];
			Queue<Integer> q = new LinkedList<>();
			q.add(n);
			d[n] = 0;
			c[n] = true;
			while(!q.isEmpty()){
				int now = q.remove();
				
				int next = D(now);
				if(!c[next]){
					q.add(next);
					d[next] = d[now]+1;
					from[next] = 'D';
					to[next] = now;
					c[next] = true;
				}
				
				next = S(now);
				if(!c[next]){
					q.add(next);
					d[next] = d[now] + 1;
					from[next] = 'S';
					to[next] = now;
					c[next] = true;
				}
				
				next = L(now);
				if(!c[next]){
					q.add(next);
					d[next] = d[now]+1;
					from[next] = 'L';
					to[next] = now;
					c[next] = true;
				}
				
				next = R(now);
				if(!c[next]){
					q.add(next);
					d[next] = d[now]+1;
					from[next] = 'R';
					to[next] = now;
					c[next] = true;
				}
			}
			
			String ans = "";
			while(m!=n){
				ans += from[m];
				m = to[m];
			}
			
			System.out.println(ans);
		}
		 
		
		
		in.close();
	}
}
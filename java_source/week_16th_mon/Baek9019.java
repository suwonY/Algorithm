package week_16th_mon;
import java.util.*;
public class Baek9019_fix1{
	static int n,m;
	static boolean[] c;
	static char[] from;
	static int[] to;
	public static void print(int n, int m){
		if(n==m) return;
		print(n,to[m]);
		System.out.print(from[m]);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for (int t = 0; t < tc; t++) {
			n = in.nextInt();
			m = in.nextInt();
			from = new char[10001];
			to = new int[10001];
			c = new boolean[10001];
			
			Queue<Integer> q = new LinkedList<>();
			q.add(n);
			c[n] = true;
			while(!q.isEmpty()){
				int now = q.remove();
				
				int next = (2*now)%10000;
				if(!c[next]){
					q.add(next);
					from[next] = 'D';
					to[next] = now;
					c[next] = true;
				}

				next = (now==0?9999:now-1);
				if(!c[next]){
					q.add(next);
					from[next] = 'S';
					to[next] = now;
					c[next] = true;
				}
				
				next = now%1000*10+now/1000;
				if(!c[next]){
					q.add(next);
					from[next] = 'L';
					to[next] = now;
					c[next] = true;
				}
				
				next = now%10*1000+now/10;
				if(!c[next]){
					q.add(next);
					from[next] = 'R';
					to[next] = now;
					c[next] = true;
				}
			}
			print(n,m);
			System.out.println();
		}
		in.close();
	}
}
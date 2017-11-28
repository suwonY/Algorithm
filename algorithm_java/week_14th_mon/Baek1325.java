package week_14th_mon;

import java.util.*;
public class Baek1325 {
	/*효율적인 해킹
	 * 신뢰할 경우 A해킹을 하면 B도 해킹할 수 있음*/
	static int n,m;
	static long max = 0;
	static ArrayList<ArrayList<Integer>> a = new ArrayList<>();
	static long[] d = new long[10001];
	public static int go(int start){
		int cnt = 0;
		if(a.get(start).size()==0)
			return 1;
		for(int i : a.get(start))
			cnt += go(i);
		
		return cnt+1;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<=n; i++)
			a.add(new ArrayList<Integer>());
		
		for(int i=0; i<m; i++){
			int x = in.nextInt();
			int y = in.nextInt();
			a.get(y).add(x);
		}

		for(int i=1; i<=n; i++){
			d[i] = go(i);
			max = Math.max(max, d[i]);
		}
		
		for(int i=1; i<=n; i++)
			if(d[i]==max)
				System.out.print(i+" ");
		
		in.close();
	}
	public static class Edge{
		int x, y;
		public Edge(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}

package week_13th_mon;

import java.util.*;
public class Baek11657_fix {

	/*타임머신
	 * n개의 도시가 있다
	 * m개의 버스가 잇다
	 * .*/
	static final int INF = 987654321;
	static int n,m;
	static List<Edge> e = new ArrayList<>();
	static int[][] a = new int[501][501];
	static int[] d = new int[501];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<m; i++){
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			int c = in.nextInt();
			e.add(new Edge(x,y,c));
		}
		Arrays.fill(d, INF);
		d[0] = 0;
		
		boolean cycle = false;
		for(int i=0; i<n; i++){
			for(Edge t : e){
				if(d[t.to]>d[t.from] + t.cost){
					d[t.to] = d[t.from] + t.cost;
					if(i==n-1) cycle=true;
				}
			}
			
		}
		if(cycle) System.out.println(-1);
		else
			for(int i=1; i<n; i++)
				System.out.println(d[i]==INF?-1:d[i]);
			
		in.close();
	}
	public static class Edge {
		int from, to, cost;
		public Edge(int from, int to, int cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
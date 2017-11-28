package week_13th_mon;

import java.util.*;
public class Baek11657 {

	/*타임머신
	 * n개의 도시가 있다
	 * m개의 버스가 잇다
	 * .*/
	static final int INF = 987654321;
	static int n,m;
	static Edge[] e = new Edge[501];
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
			e[i] = new Edge(x,y,c);
		}
		Arrays.fill(d, INF);
		d[0] = 0;
		
		boolean cycle = false;
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(d[e[j].to] > d[e[j].from] + e[j].cost){
					d[e[j].to] = d[e[j].from] + e[j].cost;
					if(i==n-1) cycle = true;
				}
			}
			
		}

		if(cycle) System.out.println(-1);
		else
			for(int i=1; i<n; i++)
				System.out.println(d[i]==INF?-1:d[i]);
			
		in.close();
	}
	public static class Edge{
		int from, to, cost;
		public Edge(int from, int to, int cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}

package week_12th_thu;

import java.util.*;
public class Baek1753 {

	static final int INF = 987654321;
	static int n, v, s;
	static ArrayList<Node>[] a = (ArrayList<Node>[]) new ArrayList[20001];
	static int[] d = new int[20001];
	static boolean[] c = new boolean[20001];
	public static void go(){
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(s,0));
		d[s] = 0;
		
		while(!pq.isEmpty()){
			Node now = pq.remove(); // 큐에 들어있는 간선중 가장 가중치가 낮은 것 찾음.
            if (c[now.to]) continue;
            c[now.to] = true;
            for (Node next : a[now.to]) {
                if (c[next.to]) continue;
                d[next.to] = Math.min(d[next.to], d[now.to] + next.cost);
                pq.add(new Node(next.to, d[next.to]));
                
            }
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		v = in.nextInt();
		s = in.nextInt();
		
		for(int i=1; i<=n; i++){
			d[i] = INF;
			a[i] = new ArrayList<Node>();
		}
		
		
		for(int i=0; i<v; i++){
			int x = in.nextInt();
			int y = in.nextInt();
			int w = in.nextInt();
			
			a[x].add(new Node(y,w));
		}
		
		go();
		for(int i=1; i<=n; i++){
			if(d[i]==INF) System.out.println("INF");
			else System.out.println(d[i]);
		}
		
		in.close();
	}
	public static class Node implements Comparable<Node>{
		int to, cost;
		public Node(int to, int cost){
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost<o.cost?-1:1;
		}
	}
}

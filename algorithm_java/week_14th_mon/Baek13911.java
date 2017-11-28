package week_14th_mon;

import java.util.*;
public class Baek13911_fix2 {
	
	/*집 구하기
	 * 맥세권 : 맥도날드와 집 사이 거리 최단거리가 x이하
	 * 스세권 : 스타벅스와 집 사이 거리 최단거리 y이하
	 * 맥세권과 스세권을 만족하는 집중 합의 최소 
	 * 
	 * 모든 맥도날드에서의 다익스트라, 스타벅스에서의 다익스트라를 돌려
	 * 최소값을 찾기에는 시간이 너무 많이 오바되는 듯하다
	 * 
	 * 답을 보니깐 임이의 점을 만들어서 모든 맥도날드까지의 거리가 0 이게 만들어 
	 * 맥도날드를 하나로 만든다
	 * 스타벅스도 마찬가지다.
	 * 
	 * 그리고나서 답을 구하면 된다
	 * 
	 * */
	static int n,v;
	static final int INF = 987654321;	
	static int m,s,mx,sy,ans=INF;
	static int[] md = new int[10005];
	static int[] sd = new int[10005];
	static boolean[] mc = new boolean[10005];
	static boolean[] sc = new boolean[10005];
	static boolean[] c = new boolean[10005];
	static int[] d = new int[10005];
	static ArrayList<Node>[] a = (ArrayList<Node>[]) new ArrayList[10005];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();	//정점의 개수
		v = in.nextInt();
		
		for(int i=1; i<=n+2; i++)
			a[i] = new ArrayList<Node>();
		
		for(int i=0; i<v; i++){
			int from = in.nextInt();
			int to = in.nextInt();
			int cost = in.nextInt();
			a[from].add(new Node(to,cost));
			a[to].add(new Node(from,cost));
			
		}
		//n개의 정점이 있다고 했으니깐 나는
		//맥도날드를 n+1이라고 두고 스타벅스를 n+2라고 두겠다
		
		Arrays.fill(md, INF);
		Arrays.fill(sd, INF);
		
		//맥도날드 입력
		m = in.nextInt();
		mx = in.nextInt();//맥세권 범위
		for(int i=0; i<m; i++){
			int num = in.nextInt();
			a[n+1].add(new Node(num,0));
			c[num] = true;
		}
		
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(n+1,0));
		md[n+1]=0;
		while(!q.isEmpty()){
			Node now = q.remove();
			if(mc[now.to]) continue;
			mc[now.to]=true;
			for(Node next : a[now.to]){
				if(mc[next.to]) continue;
				md[next.to] = Math.min(md[next.to], md[now.to]+next.cost);
				q.add(new Node(next.to, md[next.to]));
			}
		}
		
		//스타벅스 입력
		s = in.nextInt();
		sy = in.nextInt();//스세권 범위
		for(int i=0; i<s; i++){
			int num = in.nextInt();
			a[n+2].add(new Node(num,0));
			c[num] = true;
		}
		
		q = new PriorityQueue<>();
		q.add(new Node(n+2,0));
		sd[n+2]=0;
		while(!q.isEmpty()){
			Node now = q.remove();
			if(sc[now.to]) continue;
			sc[now.to]=true;
			for(Node next : a[now.to]){
				if(sc[next.to]) continue;
				sd[next.to] = Math.min(sd[next.to], sd[now.to]+next.cost);
				q.add(new Node(next.to, sd[next.to]));
			}
		}

		for(int i=1; i<=n; i++){
			if(c[i]) continue;
			if(md[i]>mx || sd[i]>sy) continue;
			ans = Math.min(ans, md[i]+sd[i]);
		}
		System.out.println(ans==INF?-1:ans);
		in.close();
	}
	public static class Node implements Comparable<Node>{
		int to, cost;
		public Node(int to, int cost){
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o){
			return this.cost - o.cost;
		}
	}
}

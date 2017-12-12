package week_14th_mon;

import java.util.*;
public class Baek13911_fix2 {
	
	/*�� ���ϱ�
	 * �Ƽ��� : �Ƶ������ �� ���� �Ÿ� �ִܰŸ��� x����
	 * ������ : ��Ÿ������ �� ���� �Ÿ� �ִܰŸ� y����
	 * �Ƽ��ǰ� �������� �����ϴ� ���� ���� �ּ� 
	 * 
	 * ��� �Ƶ����忡���� ���ͽ�Ʈ��, ��Ÿ���������� ���ͽ�Ʈ�� ����
	 * �ּҰ��� ã�⿡�� �ð��� �ʹ� ���� ���ٵǴ� ���ϴ�
	 * 
	 * ���� ���ϱ� ������ ���� ���� ��� �Ƶ���������� �Ÿ��� 0 �̰� ����� 
	 * �Ƶ����带 �ϳ��� �����
	 * ��Ÿ������ ����������.
	 * 
	 * �׸����� ���� ���ϸ� �ȴ�
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
		n = in.nextInt();	//������ ����
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
		//n���� ������ �ִٰ� �����ϱ� ����
		//�Ƶ����带 n+1�̶�� �ΰ� ��Ÿ������ n+2��� �ΰڴ�
		
		Arrays.fill(md, INF);
		Arrays.fill(sd, INF);
		
		//�Ƶ����� �Է�
		m = in.nextInt();
		mx = in.nextInt();//�Ƽ��� ����
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
		
		//��Ÿ���� �Է�
		s = in.nextInt();
		sy = in.nextInt();//������ ����
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

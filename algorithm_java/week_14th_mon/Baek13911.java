package week_14th_mon;

import java.util.*;
public class Baek13911 {
	
	/*�� ���ϱ�
	 * �Ƽ��� : �Ƶ������ �� ���� �Ÿ� �ִܰŸ��� x����
	 * ������ : ��Ÿ������ �� ���� �Ÿ� �ִܰŸ� y����
	 * �Ƽ��ǰ� �������� �����ϴ� ���� ���� �ּ� */
	static int n,v;
	static final int INF = 987654321;	
	static int m,s,mx,sy;
	static int[] md = new int[10002];
	static int[] sd = new int[10002];
	static int[] d = new int[10002];
	static List<Edge> a = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		v = in.nextInt();
		for(int i=0; i<v; i++){
			int from = in.nextInt();
			int to = in.nextInt();
			int cost = in.nextInt();
			a.add(new Edge(from,to,cost));
		}
		
		Arrays.fill(md, INF);
		Arrays.fill(sd, INF);
		
		Queue<Integer> M = new LinkedList<>();
		m = in.nextInt();
		mx = in.nextInt();//�Ƽ��� ����
		
		for(int i=0; i<m; i++){
			int num = in.nextInt();
			M.add(num);
			md[num] = 0;
		}
		
		Queue<Integer> S = new LinkedList<>();
		s = in.nextInt();
		sy = in.nextInt();//������ ����
		for(int i=0; i<s; i++){
			int num = in.nextInt();
			S.add(num);
			sd[num] = 0;
		}
		
		//�Ƶ����� ���ͽ�Ʈ��
		while(!M.isEmpty()){
			M.remove();
			for(int i=0; i<n-1; i++){
				for(Edge e: a){
					if(md[e.to]>md[e.from]+e.cost){
						md[e.to] = md[e.from]+e.cost;
					}
				}
			}
		}
		
		//��Ÿ���� ���ͽ�Ʈ��
		while(!S.isEmpty()){
			S.remove();
			for(int i=0; i<n-1; i++){
				for(Edge e: a){
					if(sd[e.to]>sd[e.from]+e.cost){
						sd[e.to] = sd[e.from]+e.cost;
					}
				}
			}
		}
		
		int ans = INF;
		for(int i=1; i<=n; i++){
			if(md[i]==INF || sd[i]==INF) continue;
			if(md[i]>mx || sd[i]>sy) continue;
			ans = Math.min(ans, md[i]+sd[i]);
		}
		
		System.out.println(ans==INF?-1:ans);
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

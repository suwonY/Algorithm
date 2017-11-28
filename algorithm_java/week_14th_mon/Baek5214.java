package week_14th_mon;

import java.util.*;
public class Baek5214_fix1 {
	/*환승
	 * 하이퍼튜브하나는 역 k개를 서로 연결한다
	 * 1번역에서 n번역으로 가는데 방문하는 최소 역의 수는 몇 개일까?*/
	static final int INF = 987654321;
	static int n,k,m;
	static List<List<Integer>> a = new ArrayList<>();
	static int[] d = new int[101005];
	static boolean[] c = new boolean[101005];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();	//역의 수
		k = in.nextInt();	//하이퍼튜브가 연결하는 역의 개수
		m = in.nextInt();	//하이퍼튜브의 개수
		
		for(int i=0; i<=n+m; i++)
			a.add(new ArrayList<Integer>());
		
		//역의 수 + 1
		for(int i=0; i<m; i++){
			for(int j=0; j<k; j++){
				int num = in.nextInt();
				a.get(n+1+i).add(num);
				a.get(num).add(n+1+i);
			}
		}
		Arrays.fill(d, INF);
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		c[1] = true;
		d[1] = 1;
		
		while(!q.isEmpty()){
			int now = q.remove();
			if(now==n){
				q.clear();
				break;
			}
			
			for(int x : a.get(now)){
				if(c[x]) continue;
				if(d[x]<=d[now]+1) continue;
				
				q.add(x);
				c[x] = true;
				d[x] = d[now]+1;
			}
		}
		System.out.println(d[n]==INF?-1:(d[n]+1)/2);
		in.close();
	}
	public static class Edge{
		int x,y;
		public Edge(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
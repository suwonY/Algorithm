package week_14th_mon;

import java.util.*;
public class Baek5214 {
	/*환승
	 * 하이퍼튜브하나는 역 k개를 서로 연결한다
	 * 1번역에서 n번역으로 가는데 방문하는 최소 역의 수는 몇 개일까?*/
	static final int INF = 987654321;
	static int n,k,m;
	static List<List<Integer>> a = new ArrayList<>();
	static int[] d = new int[100001];
	static boolean[] c = new boolean[100001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();	//역의 수
		k = in.nextInt();	//하이퍼튜브가 연결하는 역의 개수
		m = in.nextInt();	//하이퍼튜브의 개수
		for(int i=0; i<m; i++){
			List<Integer> temp = new ArrayList<>();
			for(int j=0; j<k; j++){
				int num = in.nextInt();
				temp.add(num);
			}
			a.add(temp);
		}
		Arrays.fill(d, INF);
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		d[1] = 1;
		c[1] = true;
		
		finish:
		while(!q.isEmpty()){
			int now = q.remove();
			if(now==n)
				break;
			
			for(List<Integer> temp : a){
				if(temp.contains(now)){//포함된 애가 있다면
					for(int close : temp){
						if(c[close]) continue;
						d[close] = d[now]+1;
						if(close==n){
							q.clear();
							break finish;
						}
						c[close] = true;
						q.add(close);
					}
				}
			}
		}
		
		System.out.println(d[n]==INF?-1:d[n]);
		in.close();
	}
}
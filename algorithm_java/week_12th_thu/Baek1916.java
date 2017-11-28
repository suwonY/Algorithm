package week_12th_thu;

import java.util.*;
public class Baek1916_fix {

	/*최소비용구하기*/
	static final int INF = 987654321;
	static int n,m,s,e;
	static int[][] a = new int[1001][1001];
	static int[] d = new int[1001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		
		for(int i=0; i<n; i++){
			d[i] = INF;
			for(int j=0; j<n; j++){
				a[i][j]=INF;
			}
		}
		
		for(int i=0; i<m; i++){
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			int c = in.nextInt();
			
			a[x][y] = Math.min(a[x][y], c);
		}
		
		s = in.nextInt()-1;
		e = in.nextInt()-1;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		d[s] = 0;
		
		while(!q.isEmpty()){
			int now = q.remove();
			for(int next=0; next<n; next++){
				if(d[next]>a[now][next]+d[now]){
					d[next] = a[now][next]+d[now];
					q.add(next);
				}
			}
		}
		System.out.println(d[e]);
		in.close();
	}
}
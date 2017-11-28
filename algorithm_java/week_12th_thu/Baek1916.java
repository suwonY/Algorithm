package week_12th_thu;

import java.util.*;
public class Baek1916 {

	/*최소비용구하기*/
	static final int INF = 987654321;
	static int n,m;
	static int[][] a = new int[1001][1001];
	static int[] d = new int[1001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<m; i++){
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			int c = in.nextInt()-1;
			a[x][y] = c;
		}
		int start = in.nextInt()-1;
		int end = in.nextInt()-1;
		Arrays.fill(d, INF);
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start,0));
		d[start] = 0;
		
		while(!q.isEmpty()){
			Node now = q.remove();
			int x = now.x;
			int cnt = now.cnt;
			if(x==end){
				d[end] = Math.min(d[end], cnt);
				continue;
			}
			for(int i=0; i<n; i++){
				if(i==x) continue;
				if(a[x][i]==0) continue;
				
				d[i] = Math.min(d[i], d[x]+a[x][i]);
				q.add(new Node(i,d[i]));
			}
		}
		System.out.println(d[end]);
		in.close();
	}
	public static class Node{
		int x, cnt;
		public Node(int x, int cnt){
			this.x = x;
			this.cnt = cnt;
		}
	}

}

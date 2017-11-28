package week_12th_mon;

import java.util.*;
public class co김씨만_fix2 {
	public static int rev(int n){
		if(n==1) return 2;
		return 1;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] a = new int[n+1][n+1];
			int[] d = new int[n+1];
			boolean[] c = new boolean[n+1];
			
			int min = 987654321;
			for(int i=1; i<=m; i++){
				int x = in.nextInt();
				int y = in.nextInt();
				a[x][y] = a[y][x] = 1;
				min = Math.min(min, Math.min(x, y));
			}
			
			Queue<Integer> q = new LinkedList<>();
			q.add(min);
			d[min]=1;
			
			int ans = 1;
			while(!q.isEmpty()){
				int now = q.remove();
				c[now]=true;
				for(int i=1; i<=n; i++){
					//인접하면서 같은 가문이 지배중이면 0을 출력
					if(a[now][i]==1){
						if(d[now]==d[i]){
							ans = 0;
							q.clear();
							break;
						}
						if(!c[i]){
							q.add(i);
							d[i] = rev(d[now]);
						}
					}
				}
			}
			System.out.println("Case #"+t);
			System.out.println(ans);
		}
		in.close();
	}
}
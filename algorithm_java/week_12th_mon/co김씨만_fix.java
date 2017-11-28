package week_12th_mon;

import java.util.*;
public class co김씨만_fix {
	static int n, m;
	static int[][] a;
	static int[] d;
	public static int rev(int n){
		if(n==1) return 2;
		return 1;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			m = in.nextInt();
			a = new int[n+1][n+1];
			d = new int[n+1];
			
			for(int i=0; i<m; i++){
				int x = in.nextInt();
				int y = in.nextInt();
				a[x][y]= a[y][x] = 1;
			}

			int ans = 1;
			finish:
			for(int i=1; i<=n; i++){
				for(int j=1; j<=n; j++){
					if(i==j) continue;
					//인접한 존재라면
					if(a[i][j]==1){
						//둘다 지배가 없다면
						if(d[i]==0 && d[j]==0){
							d[i]=1;
							d[j]=2;
							continue;
						}
						if(d[i]!=0 && d[j]==0){
							d[j] = rev(d[i]);
							continue;
						}
						if(d[i]==0 && d[j]!=0){
							d[i] = rev(d[j]);
							continue;
						}
						if(d[i]!=0 && d[j]!=0){
							if(d[i]==d[j]){
								ans = 0;
								break finish;
							}
							if(d[i]!=d[j]){
								continue;
							}
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
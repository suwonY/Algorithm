package pracAlgorithm;

import java.util.*;
public class Baek1915 {
	static int n,m,ans;
	static String s;
	static int[][] a = new int[1001][1001], d = new int [1001][1001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++){
			s = in.next();
			for(int j=0; j<m; j++){
				a[i][j] = s.charAt(j)-48;
				if(a[i][j]==1){
					d[i][j] = 1;
					ans = 1;
				}
			}
		}
		for(int i=1; i<n; i++){
			for(int j=1; j<m; j++){
				if(a[i-1][j]==1 && a[i-1][j-1]==1 && a[i][j-1]==1){
					d[i][j] = Math.min(d[i-1][j], d[i-1][j-1]);
					d[i][j] = Math.min(d[i][j],  d[i][j-1]) + 1;
				}
				ans = Math.max(d[i][j], ans);
			}
		}
		System.out.println(ans*ans);
		in.close();
	}
}
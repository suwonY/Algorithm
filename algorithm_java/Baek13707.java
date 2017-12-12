package week_15th_thu;

import java.util.*;
public class Baek13707 {

	/*d[k][n] 
	 * k개의 숫자로 만든 n
	 * 
	 * k-1개의 숫자로 나오는 수는 그러면 마지막이 l이라고 하면 n-l이 되니깐
	 * d[k-1][n-l]이 된다(l은 0~n)
	 * 
	 * 근데 그림을 그려보면
	 * d[k][n] = d[k][n-1] + d[k-1][n]으로 표현할 수 있음
	 * */
	static final int mod = 1000000000;
	static long[][] d = new long[5001][5001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();

		d[0][0] = 1;
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j <= n; j++) {
				d[i][j] = d[i-1][j];
				if(j-1>=0) d[i][j] += d[i][j-1];
				d[i][j]%=mod;
			}
		}
		System.out.println(d[k][n]);
		in.close();
	}
}
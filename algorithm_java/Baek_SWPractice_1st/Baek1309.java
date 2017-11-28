package Baek_SWPractice_1st;

import java.util.*;
public class Baek1309 {
	static final int mod = 9901;
	static int n;
	static int[][] a = new int[100001][2];
	static int[][] d = new int[100001][3];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		//n이 1일 때는 왼쪽, 오른쪽, 없을 때 가능
		d[1][0] = 1;
		d[1][1] = 1;
		d[1][2] = 1;
		
		for(int i=2; i<=n; i++){
			d[i][0] = (d[i-1][0] + d[i-1][1] + d[i-1][2])%mod;
			d[i][1] = (d[i-1][0] + d[i-1][2])%mod;
			d[i][2] = (d[i-1][0] + d[i-1][1])%mod;
		}
		System.out.println((d[n][0] + d[n][1] + d[n][2])%mod);
		in.close();
	}
}

package week_14th_mon;

import java.util.*;
public class Baek1309 {
	/*동물원
	 * n*2우리
	 * 사자는 가로로 세로로 붙어있을 수 없음
	 * 
	 * 왼쪽을 0 오른쪽을 1이라고 하면
	 * n이 1일 때는
	 * 오른쪽에 있거나, 왼쪽에있거나, 아무데도 없거나 3가지가 나온다
	 * d[n][0] 은 d[n-1][0] + d[n-1][1] + d[n-1][2] 
	 * d[n][1] 은 d[n-1][0] + d[n-1][2]
	 * d[n][2] 은 d[n-1][0] + d[n-1][1]
	 * 이 된다
	 * */
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
			d[i][0] = (d[i-1][0] + d[i-1][1] + d[i-1][2])%9901;
			d[i][1] = (d[i-1][0] + d[i-1][2])%9901;
			d[i][2] = (d[i-1][0] + d[i-1][1])%9901;
		}
		System.out.println(d[n][0] + d[n][1] + d[n][2]);
		in.close();
	}
}
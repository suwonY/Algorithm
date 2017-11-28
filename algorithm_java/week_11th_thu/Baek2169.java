package week_11th_thu;

import java.util.*;
public class Baek2169 {

	/*로봇 조종하기
	 * (1,1) -> (n,m)까지 가는 최대치
	 * 움직이는 방향은 왼/오/아래 (한번 간 곳은 다시 갈 수 없다)
	 * 
	 * 마지막 n,m에서 생각해보면 
	 * 1. 위에서 오는 경우
	 * d[i][j][0]
	 * max( d[i-1][j][0]  / d[i-1][j][1] / d[i-1][j][2] ) + a[i][j]
	 * 2. 왼쪽에서 오는 경우
	 * d[i][j][1]
	 * max( d[i][j-1][0] / d[i][j-1][1] / d[i][j-1][2] ) + a[i][j]
	 * 3. 오른쪽에서 오는 경우
	 * d[i][j][2]
	 * max( d[i][j+1][0] / d[i][j+1][2] ) + a[i][j]
	 * 
	 * 3개의경우중에서의 가장 큰 값을 고르면 됨
	 * */
	static final int inf = 987654321;
	static int max(int a, int b){
		if(a>b) return a;
		else return b;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[][][] d = new int[1002][1002][3];
		int[][] a = new int[1002][1002];
		
		for(int i=1; i<=n; i++)
			for(int j=1; j<=m; j++)
				a[i][j] = in.nextInt();
		
		//맨 윗줄은 오른쪽에서 
		
		for(int i=0; i<1002; i++)
			for(int j=0; j<1002; j++)
				for(int k=0; k<3; k++)
					d[i][j][k] = -inf;
		
		//첫 시작점의 d는 그냥 그 값
		d[1][1][1] = a[1][1];
		//첫번째 줄에 있는 애들은 오른쪽으로만 이동할 수 있음
		for(int i=2; i<=m; i++)
			d[1][i][1] = d[1][i-1][1] + a[1][i];
		
		for(int i=2; i<=n; i++){
			for(int j=1; j<=m; j++){
				d[i][j][0] = Math.max(d[i-1][j][0], Math.max(d[i-1][j][1], d[i-1][j][2])) + a[i][j];
				d[i][j][1] = Math.max(d[i][j-1][0], Math.max(d[i][j-1][1], d[i][j-1][2])) + a[i][j];
			}
			for (int j=m; j>=1; j--) {
                d[i][j][2] = Math.max(d[i][j+1][0], d[i][j+1][2]) + a[i][j];
            }
		}
		System.out.println(Math.max(d[n][m][0], Math.max(d[n][m][1], d[n][m][2])));
	}
}
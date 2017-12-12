package Baek_SWPractice_1st;
import java.util.*;
public class Baek2169 {
	static final int INF = 987654321;
	static int max(int a, int b){
		return a>b?a:b;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[][][] d = new int[1002][1002][3];
		int[][] a = new int[1002][1002];
		
		for(int i=1; i<=n; i++)
			for(int j=1; j<=m; j++){
				a[i][j] = in.nextInt();
				for(int k=0; k<3; k++)
					d[i][j][k] = -INF;
			}
		
		d[1][1][1] = a[1][1];
		for(int i=2; i<=m; i++)
			d[1][i][1] = d[1][i-1][1] + a[1][i];
		
		for(int i=2; i<=n; i++){
			for(int j=1; j<=m; j++){
				d[i][j][0] = max(d[i-1][j][0], max(d[i-1][j][1], d[i-1][j][2])) + a[i][j];
				d[i][j][1] = max(d[i][j-1][0], max(d[i][j-1][1], d[i][j-1][2])) + a[i][j];
			}
			for (int j=m; j>=1; j--) {
                d[i][j][2] = max(d[i][j+1][0], d[i][j+1][2]) + a[i][j];
            }
		}
		System.out.println(max(d[n][m][0], max(d[n][m][1], d[n][m][2])));
		
		in.close();
	}
}
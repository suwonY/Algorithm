package week_16th_mon;

import java.util.*;
public class Baek1915 {
	static final int INF = 987654321;
	static int n,m,ans=0,min=INF;
	static int[][] a = new int[1001][1001];
	static int[][] d = new int[1001][1001];
	static int[] dx = {-1,0,-1};
	static int[] dy = {0,-1,-1};
	public static void go(){
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(a[i][j]==0) continue;
				if(i==0 || j==0){
					ans = 1;
					if(a[i][j]==1) d[i][j]=1;
					continue;
				}
				min = INF;
				boolean next = true;
				for(int k=0; k<3; k++){
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if(a[nx][ny]==0) {
						next = false;
						break;
					}
					min = Math.min(min, d[nx][ny]);
				}
				if(!next) d[i][j] = 1;
				if(next) d[i][j] = min + 1;
				ans = Math.max(ans, d[i][j]);
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++){
			String temp = in.next();
			for(int j=0; j<m; j++){
				a[i][j] = temp.charAt(j)-48;
			}
		}
		go();
		System.out.println(ans*ans);
		in.close();
	}
}
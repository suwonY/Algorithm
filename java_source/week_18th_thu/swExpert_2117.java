package week_18th_thu;

import java.util.*;
public class swExpert_2117 {
	static int n,m,max,ans=0;
	static int[][] a = new int[n][n];
	public static int cc(int size){
		int cnt = 1;
		//위 아래로 가면서 확인
		for(int i=1; i<size; i++){
			cnt+=4;
			for(int j=1; j<size-i; j++) cnt+=4;
		}
		return cnt;
	}
	public static int count(int x, int y, int size){
		int cnt = 0;
		if(a[x][y]==1) ++cnt;
		//가로로 먼저 확인
		for(int i=1; i<size; i++){
			int left = y - i;
			int right = y + i;
			if(left>=0 && a[x][left]==1) ++cnt;
			if(right<n && a[x][right]==1) ++cnt;
		}
		//위 아래로 가면서 확인
		for(int i=1; i<size; i++){
			int up = x - i;
			int down = x + i;
			if(up>=0 && a[up][y]==1) ++cnt;
			if(down<n && a[down][y]==1) ++cnt;
			
			for(int j=1; j<size-i; j++){
				int left = y - j;
				int right = y + j;
				if(up>=0){
					if(left>=0 && a[up][left]==1) ++cnt;
					if(right<n && a[up][right]==1) ++cnt;
				}
				if(down<n){
					if(left>=0 && a[down][left]==1) ++cnt;
					if(right<n && a[down][right]==1) ++cnt;
				}
			}
		}
		return cnt;
	}
	public static void go(){
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				for(int k=1; k<=n*2; k++){
					int cnt = count(i,j,k);
					int pay = cnt*m;
					int company = k*k + (k-1)*(k-1);
					if(pay>=company) ans = Math.max(cnt, ans);
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int tc=1; tc<=t; tc++){
			n = in.nextInt();
			m = in.nextInt();
			ans = 0;
			a = new int[n][n];
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++){
					a[i][j] = in.nextInt();
					if(a[i][j]==1 && max==0) max = m;
				}
			go();
			System.out.println("#"+tc+" "+ans);
		}
		in.close();
	}
}
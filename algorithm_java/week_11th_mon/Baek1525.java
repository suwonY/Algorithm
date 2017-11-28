package week_11th_mon;

import java.util.*;
public class Baek1525 {

	static int[][] c = new int[4][4];
	static int[][] a = new int[4][4];
	static boolean[][] check = new boolean[4][4];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int zx,zy,ans=987654321;
	public static boolean finish(int[][] a){
		for(int i=1; i<=3; i++){
			for(int j=1; j<=3; j++){
				if(i==3 && j==3) continue;
				if(a[i][j]!=c[i][j])
					return false;
			}
		}
		return true;
	}
	public static void go(int x, int y, int cnt, int[][] a){
		if(finish(a)){
			ans = Math.min(cnt, ans);
			return;
		}
		
		int[][] temp = new int[4][4];
		for(int i=1; i<=3; i++){
			for(int j=1; j<=3; j++){
				temp[i][j] = a[i][j];
			}
		}
		
		System.out.println("cnt: "+cnt);
		for(int i=1; i<=3; i++){
			for(int j=1; j<=3; j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		for(int k=0; k<4; k++){
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(nx<1 || nx>3 || ny<1 || ny>3) continue;
			if(check[nx][ny]) continue;//0이 있었던 곳으로 또가면 무한루프 도니깐 제외
			
			//두개를 스왑 (0은 x,y -> nx,ny로 옮겨짐)
			int n = a[nx][ny];
			a[nx][ny] = a[x][y];
			a[x][y] = n;
			check[nx][ny] = true;
			go(nx,ny,cnt+1,a);

			//다시 원래대로 돌려주기
			n = a[nx][ny];
			a[nx][ny] = a[x][y];
			a[x][y] = n;
			check[nx][ny] = false;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int k=1;
		for(int i=1; i<=3; i++){
			for(int j=1; j<=3; j++){
				a[i][j] = in.nextInt();
				c[i][j]=(k++);
				if(a[i][j]==0){
					zx=i;
					zy=j;
					check[i][j] = true;
				}
			}
		}
		go(zx,zy,0,a);
		if(ans==987654321) System.out.println(-1);
		else System.out.println(ans);
		
		in.close();
	}
}

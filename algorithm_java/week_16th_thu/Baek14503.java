package week_16th_thu;
import java.util.*;
public class Baek14503 {
	/*
	1. 왼쪽방향이 청소가 안됐으면 왼쪽방향을 청소한다.(항상 왼쪽방향부터 청소행됨)
	2. 왼쪽 방향이 청소가 됐으면 왼쪽으로 방향을 바꾸고 다시 왼쪽을 확인한다
	3. 네방향이 모두 청소가 되있거나 벽인경우, 후진하여 2번을 한다
	4. 네방향이 모두 청소가 되있거나 벽인경우, 뒤쪽이 벽이면 작동을 멈춘다.
	0은 빈칸 1은 벽이다
	0 상 
	1 우
	2 하
	3 좌
	 * */
	static int n,m;
	static int[][] a = new int[51][51];
	static boolean[][] c = new boolean[51][51];
	static int ans = 0;
	static int sx,sy,dir;
	public static void left(){
		if(dir==3) dir=0;
		else dir++;
	}
	public static boolean first(int x, int y){
		if(!c[x][y]){
			++ans;
			c[x][y]=true;
		}
		//왼쪽방향이 벽이거나, 맵 밖이거나 청소가 된 경우 -1을 반환
		if(dir==0){//상
//			System.out.println("위에서 왼쪽으로바꿈");
			if(y-1<0 || a[x][y-1]==1 || c[x][y-1]) return false;
			sy=y-1;
		}
		else if(dir==1){//좌
//			System.out.println("왼쪽에서 아래로바꿈");
			if(x+1>=n || a[x+1][y]==1 || c[x+1][y]) return false;
			sx=x+1;
		}
		else if(dir==2){//하
//			System.out.println("아레에서 우로바꿈");
			if(y+1>=m || a[x][y+1]==1 || c[x][y+1]) return false;
			sy=y+1;
		}
		else{//우
//			System.out.println("우에서 위로바꿈");
			if(x-1<0 || a[x-1][y]==1 || c[x-1][y]) return false;
			sx=x-1;
		}

		c[sx][sy]=true;
		++ans;
		return true;
	}
	public static boolean checkBack(int x, int y){
		if(dir==0){
			if(x+1>=n || a[x+1][y]==1) return false;
			sx=x+1;
		}
		else if(dir==1){
			if(y+1>=m || a[x][y+1]==1) return false;
			sy=y+1;
		}
		else if(dir==2){
			if(x-1<0 || a[x-1][y]==1) return false;
			sx=x-1;
		}
		else{
			if(y-1<0 || a[x][y-1]==1) return false;
			sy=y-1;
		}
		if(!c[sx][sy]){
			++ans;
			c[sx][sy]=true;
		}

//		System.out.println("게임끝");
		return true;
	}
	public static void go(){
		while(true){
			int cnt = 0;
			while(cnt!=4){
				boolean temp = first(sx,sy);
				left();
				if(!temp) ++cnt;
				else cnt=0;
			}
			if(!checkBack(sx,sy))break;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		sx = in.nextInt();
		sy = in.nextInt();
		dir = in.nextInt();
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				a[i][j] = in.nextInt();
		
		go();
		System.out.println(ans);
		in.close();
	}
}
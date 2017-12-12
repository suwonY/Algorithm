package week_20th_thu;

import java.util.*;
public class swExpert1210 {

	static int ans;
	static int[][] a;
	static int sx,sy;
	static int[] dx = {-1,0,0}, dy = {0,-1,1};
	public static int turn(int x, int y){
		if(y-1>=0) if(a[x][y-1]==1) return 1;
		if(y+1<100) if(a[x][y+1]==1) return 2;
		return 0;
	}
	public static void next(int dir){
		if(dir==1)
			while(true){
				if(sy-1<0) break;
				if(a[sx][sy-1]==0) break;
				--sy;
			}
		if(dir==2)
			while(true){
				if(sy+1>=100) break;
				if(a[sx][sy-1]==0) break;
				++sy;
			}
	}
	
	public static void go(){
		while(true){
			//0행에 도착하면 끝
			if(sx==0){
				ans = sy;
				break;
			}
			//다음 좌표로 이동하기
			if(turn(sx,sy)==0){
				--sx;
				continue;
			}
			if(turn(sx,sy)==1){
				next(1);
				continue;
			}
			if(turn(sx,sy)==2){
				next(2);
				continue;
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int t=1; t<=100; t++){
			int tc = in.nextInt();
			a = new int[100][100];
			ans = 0;
			for(int i=0; i<100; i++)
				for(int j=0; j<100; j++){
					a[i][j] = in.nextInt();
					if(a[i][j]==2){
						sx=i; sy=j;
					}
				}
			
			go();
			System.out.print("#"+tc+" ");
			System.out.println(ans);
		}
		in.close();
	}
}
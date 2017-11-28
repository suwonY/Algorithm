package week_14th_mon;

import java.util.*;
public class Baek2240_dp1 {
	
	/*dfs로는 안되나보다
	 * 그럼 dp로 생각해보자
	 * d[t][w] t초에 w번 이동했을 때 먹은 자두의 개수
	 * */
	static int t,w,ans=0;
	static int[] a = new int[1001];
	static int[][] d = new int[1001][31];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		t = in.nextInt();
		w = in.nextInt();
		
		//d[t][w] t초 후 w번 이동했을 때 먹은 자두의 개수
		for(int i=0; i<t; i++)
			a[i] = in.nextInt();
		
		//0초에 자두가 1에 있으면 먹은거 아니면 못먹은거
		if(a[0]==1) d[1][0] = 1;
		else d[1][1]=1;
		
		for(int i=1; i<t; i++){
			for(int j=0; j<=w; j++){
				//i초 후 j번 이동했을 때
				if(j==0){
					//한번도 움직이지 않았다면
					if(a[i]==1) d[i][0]=d[i-1][0]+1;	//1번나무면 +1
					else d[i][0]=d[i-1][0];				//2번 나무면 그대로
					continue;
				}
				if(j%2==0){	//짝수번 이동했을 때는 1번에 위치
					if(a[i]==1)	//1번나무면 +1
						d[i][j] = Math.max(d[i-1][j-1], d[i-1][j])+1;
					else			//2번나무면 그대로
						d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]);
				}
				else{ 	//홀수번 이동했을 때는 2번에 위치
					if(a[i]==2)	//2번나무면 +1
						d[i][j] = Math.max(d[i-1][j-1], d[i-1][j])+1;
					else			//1번나무면 그대로
						d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]);
				}
			}
		}
		for(int i=0; i<=w; i++)
			ans = Math.max(d[t-1][i], ans);
		System.out.println(ans);
		in.close();
	}
}

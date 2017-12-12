package week_17th_thu;

import java.util.*;
public class swExpert1952 {

	//d[i][0] 은 i번째 달에 1일짜리 쓸 경우
	//d[i][0] = d[i-1][0] + a[0]*d[i]
	//		  = d[i-1][1] + a[0]*d[i]
	//		  = d[i-3][2] + a[0]*d[i]
	
	//d[i][0] 은 i번째 달에 1달짜리 쓸 경우
	//d[i][0] = d[i-1][0] + a[0]
	//		  = d[i-1][1] + a[1]
	//		  = d[i-3][2] + a[2]
	
	//d[i][0] 은 i번째 달에 3달짜리 쓸 경우
	//d[i][0] = d[i-1][0] + a[0]
	//		  = d[i-1][1] + a[1]
	//		  = d[i-3][2] + a[2]
	static final int INF = 987654321;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			System.out.println("#"+t+" "+ans);
		}*/
		int ans = INF;
		int[][] d = new int[12][3];
		int[] a = new int[4];
		int[] day = new int[12];
		for(int i=0; i<4; i++)
			a[i] = in.nextInt();
		for(int i=0; i<12; i++)
			day[i] = in.nextInt();
			
		ans = a[3];
		
		//1월 경우
		d[0][0] = a[0]*day[0];
		d[0][1] = a[1];
		d[0][2] = a[2];
		
		//2월 의 경우
		d[1][0] = Math.min(d[0][0], d[0][1])+a[0]*day[1];
		d[1][1] = Math.min(d[0][0], d[0][1])+a[1];
		d[1][2] = Math.min(d[0][0], d[0][1])+a[2];
		
		//3월의 경우
		d[2][0] = Math.min(d[1][0], d[1][1])+a[0]*day[2];
		d[2][1] = Math.min(d[1][0], d[1][1])+a[1];
		d[2][2] = Math.min(d[1][0], d[1][1])+a[2];
		
		//i날짜에 1일권으로 끊을 경우
		for(int i=3; i<12; i++){
			d[i][0] = Math.min(d[i-1][0], Math.min(d[i-1][1], d[i-3][2]))+a[0]*day[i];
			d[i][1] = Math.min(d[i-1][0], Math.min(d[i-1][1], d[i-3][2]))+a[1];
			d[i][2] = Math.min(d[i-1][0], Math.min(d[i-1][1], d[i-3][2]))+a[2];
		}
		
		for(int i=0; i<3; i++){
			for(int j=0; j<12; j++){
				System.out.print(d[j][i]+"\t");
			}
			System.out.println();
		}
		for(int i=0; i<3; i++)
			ans = Math.min(ans, d[11][i]);
		ans = Math.min(ans, d[9][2]);	//3달전꺼에서 최소값이 생길 수도 있음
		
		System.out.println(ans);
		in.close();
	}
}
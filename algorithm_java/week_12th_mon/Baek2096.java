package week_12th_mon;

import java.util.*;
public class Baek2096 {

	/*내려가기
	 * 세개의 숫자를 골라서 시작한다
	 * 다음이동하는 방법은 바로 아래로 이동하거나
	 * 바로아래의 인접한 애로이동하는 방법이 있다.*/
	static final int INF = 987654321;
	static int n;
	static int[][] minD = new int[100001][3];
	static int[][] maxD = new int[100001][3];
	static long min=INF,max=0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		boolean first = true;
		int x,y,z;
		for(int i=0; i<n; i++){
			x = in.nextInt();
			y = in.nextInt();
			z = in.nextInt();
			if(first){
				minD[i][0] = maxD[i][0] = x;
				minD[i][1] = maxD[i][1] = y;
				minD[i][2] = maxD[i][2] = z;
				first = false;
				continue;
			}
			
			minD[i][0] = Math.min(minD[i-1][0], minD[i-1][1])+x;
			minD[i][1] = Math.min(minD[i-1][0], Math.min(minD[i-1][1], minD[i-1][2]))+y;
			minD[i][2] = Math.min(minD[i-1][1], minD[i-1][2])+z;
			
			maxD[i][0] = Math.max(maxD[i-1][0], maxD[i-1][1])+x;
			maxD[i][1] = Math.max(maxD[i-1][0], Math.max(maxD[i-1][1], maxD[i-1][2]))+y;
			maxD[i][2] = Math.max(maxD[i-1][1], maxD[i-1][2])+z;
			
		}
		
		for(int i=0; i<3; i++){
			max = Math.max(max, maxD[n-1][i]);
			min = Math.min(min, minD[n-1][i]);
		}
		
		System.out.println(max + " " + min);
		in.close();
	}
}

package week_11th_thu;

import java.util.*;
public class Baek1727_dp {

	/*커플만들기
	 * 솔남 n
	 * 솔여 m
	 * 최대한 많은 커플을 만들고, 커플을 이루는 두 사람의 성격의합이 최소가 되도록한다
	 * 1. 가장 많은 커플을 만든 후
	 * 2. 성격의 최소값을 구한다
	  
	  만약 d[i][j]를 i번남자가 j번 여자를 선택했을 때의 값이라고 한다
	 
	 
	 *  */
	static int n,m, max, ans=987654321;
	static int sum = 0;
	static int[] mc;
	static int[] nc;
	static int[][] a = new int[1001][1001];
	public static void go(int start, int cnt, int total){
		if(cnt==n){
			ans = Math.min(ans, Math.abs(total-sum));
			return;
		}
		
		for(int i=start; i<m; i++)
			go(i+1,cnt+1,total+mc[i]);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		if(n>m){
			int temp = n;
			n = m;
			m = temp;
		}
		
		nc = new int[n+1];
		mc = new int[m+1];
		
		for(int i=0; i<n; i++){
			nc[i] = in.nextInt();
			sum+=nc[i];
		}
		for(int i=0; i<m; i++)
			mc[i] = in.nextInt();
		
		for(int i=0; i<m-n; i++){
			go(i,0,0);
		}
		System.out.println(ans);
		in.close();
	}
}

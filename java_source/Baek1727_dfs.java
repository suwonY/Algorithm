package week_11th_thu;

import java.util.*;
public class Baek1727_dfs {

	/*커플만들기
	 * 솔남 n
	 * 솔여 m
	 * 최대한 많은 커플을 만들고, 커플을 이루는 두 사람의 성격의합이 최소가 되도록한다
	 * 1. 가장 많은 커플을 만든 후
	 * 2. 성격의 최소값을 구한다
	 * 
	 * 할수있는 칼치기 다했다 . 그냥 이거론 시간초과나서 못푸는 문제다
	 *  */
	static final int INF = 987654321;
	static int n,m,ans=INF;
	static int sum = 0;
	static boolean finish = false;
	static int[] mc;
	public static void go(int start, int cnt, int total){
		if(cnt==n){
			if(ans != INF){
				if(total>ans){
					finish = true;
					return;
				}
			}
			ans = Math.min(ans, Math.abs(sum-total));
			return;
		}
		for(int i=start+1; i<=m-n+cnt; i++){
			go(i,cnt+1,total+mc[i]);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		//m이 항상 큰거
		//n이 항상 작은거
		//최대커플은 m명이 됨
		//nc에서 4명의 부분집합을 구해야됨
		if(n>m){
			int temp = n;
			n = m;
			m = temp;
		}
		mc = new int[m];
		
		for(int i=0; i<n; i++){
			sum += in.nextInt();
		}
		for(int i=0; i<m; i++)
			mc[i] = in.nextInt();
		
		Arrays.sort(mc);
		
		for(int i=0; i<=mc.length-n; i++){
			go(i,1,mc[i]);
			if(finish) break;
		}
		System.out.println(ans);
		in.close();
	}
}

package week_15th_thu;

import java.util.*;

public class Baek9019 {

	// DSLR
	// 풀어보기
	static int[] d = new int[10001];
	static boolean[] c = new boolean[10001];
	public static int L(int n) {
		return n/10 + n%1000*10;
	}
	public static int S(int n) {

		return n;
	}
	public static int D(int n) {

		return n;
	}
	public static int R(int n) {

		return n;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*
		 * int tc = in.nextInt(); for(int t=0 ;t<tc; t++){
		 * 
		 * }
		 */

		int n = in.nextInt();
		int m = in.nextInt();
		Queue<Integer> q = new LinkedList<>();
		
		int cnt = 0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0 ;i<size; i++){
				if(n==m){
					q.clear();
					break;
				}
			}
			++cnt;
			
		}
		System.out.println(cnt);
		in.close();
	}
}
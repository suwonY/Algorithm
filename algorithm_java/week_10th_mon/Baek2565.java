package week_10th_mon;

import java.util.*;
public class Baek2565 {

	/*전깃줄
	 * a-b를 잇는 전기줄이있을 때(전깃줄 100이하)
	 * 최소개수를 끊어 교차되지 않게 만들기
	 * 
	 * 줄을 잘 설 수 있는 전깃줄의 최대값을 구하는 문제
	 * 최대 증가수열을 구하는 문제
	 * 
	 * */
	//전깃줄이 있을때 b의 값을 저장한다
	//a의 값이 커짐에 따라 b의 값이 작아지면 불가능하다는 뜻이다
	//최대 증가수열의 크기를 l라고하면
	//전기줄의수(n) - l = 잘라야할 전깃줄이 나온다.
	static int[] a = new int[501];
	static int n, max = 0, ans = 987654321, maxN = 0;
	public static void go(int start, int max){
		//전깃줄이 없으면 패스
		if(a[start]==0) return;
		int num = a[start];
		for(int i=start+1; i<=maxN; i++){
			if(a[i]==0) continue;
			if(num<a[i]){
				go(i,max+1);
			}
		}
		
		ans = Math.min(ans, n-max);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++){
			int x = in.nextInt();
			int y = in.nextInt();
			a[x] = y;
			maxN = Math.max(maxN, Math.max(x, y));
		}
		for(int i=0; i<501; i++)
			go(i,1);
		
		System.out.println(ans);
		in.close();
	}
}

package week_10th_mon;

import java.util.*;
public class Baek2565_fix {
	/*전깃줄
	 * a-b를 잇는 전기줄이있을 때(전깃줄 100이하)
	 * 최소개수를 끊어 교차되지 않게 만들기
	 * 
	 * 줄을 잘 설 수 있는 전깃줄의 최대값을 구하는 문제
	 * 최대 증가수열을 구하는 문제
	 * */
	//전깃줄이 있을때 b의 값을 저장한다
	//a의 값이 커짐에 따라 b의 값이 작아지면 불가능하다는 뜻이다
	//최대 증가수열의 크기를 l라고하면
	//전기줄의수(n) - l = 잘라야할 전깃줄이 나온다.
	
	//시간초과가 낫다
	//LIS를 시간을 줄여야겠다
	//d[i]를 i번째까지의 증가수열 중 최대라고 하면
	static int[] a = new int[501];
	static int n, ans = 0, maxN = 0;
	public static void LIS(int[] arr, int len){
		int[] d = new int[len];
		for(int i=0; i<len; i++){
			if(arr[i]==0) continue;
			d[i]=1;
			for(int j=0; j<i; j++){
				if(arr[j]==0) continue;
				if(arr[i]>arr[j]){
					d[i] = Math.max(d[j]+1, d[i]);
				}
			}
		}
		for(int i=0; i<len; i++){
			ans = Math.max(d[i], ans);
		}
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
		LIS(a,maxN+1);
		System.out.println(n-ans);
		in.close();
	}
}

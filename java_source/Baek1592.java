package week_12th_thu;

import java.util.*;
public class Baek1592 {
	/*영식이와 친구들
	 * 서로 공을 던진다
	 * 한사람이 m번 받으면 끝난다
	 * 
	 * 현재 공을 받은 횟수가 
	 * 홀수이면 오른쪽으로 l번째 있는 사람에게
	 * 짝수이면 왼쪽으로 l번째 있는 사람에게 공을 던진다
	 * */
	static int n,m,l;
	static int[] a = new int[1001];	//현재 사람마다 받은 공의 횟수
	public static int left(int num){
		return num-l<0 ? (num-l)+n : num-l; 
	}
	public static int right(int num){
		return (num+l)%n;
	}
	public static void go(int now, int cnt){
//		System.out.println("현재 공을 가지고 있는 사람 : " + now);
		//받은 사람 받은 횟수 1 증가
		++a[now];
//		System.out.println("이사람이 공을 받은 횟수 : " +a[now]);
		if(a[now]==m){
			System.out.println(cnt);
			return;
		}
		
		//받은 사람이 짝수번 받았으면
		if(a[now]%2==0){
//			System.out.println("짝수번 받아서 왼쪽으로 보냄");
			go(left(now),cnt+1);
		}
		//받은사람이 홀수번 받았으면
		else{
//			System.out.println("홀수번 받아서 오른쪽으로 보냄");
			go(right(now),cnt+1);
		}
//		System.out.println();
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		l = in.nextInt();
		go(0,0);
		in.close();
	}
}

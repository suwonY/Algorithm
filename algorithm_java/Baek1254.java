package week_10th_thu;

import java.util.*;
public class Baek1254_fix {

	/*팰린드롬 만들기
	 놓친부분 : 문자를 문자열 뒤에 추가하여야 된다
	 그러면 같지 않으면 같은 부분이 나올 때까지 뒤에 추가해주는 방법으로 생각하자
	 permite가 있다고 할 때
	 p가 같지 않으므로
	 permitep|를 만든다
	 ->ermite|가 있다고 생각하자
	 그런데 e가 겹쳐도 e다음 r이 겹치지 않으므로
	 ermite|re 를 만든다
	 |앞에는 문자열을 추가할 수 없기 때문
	 ermitere가 됐다
	 mite가 남았다
	 e가 중간이므로
	 mitetim까지 추가하면 된다
	 
	 좀 다르게 생각해보면
	 abb가 있다 bba를 뒤에 붙인다
	 짝수개의 문자열일 경우
	 abbb와 같은 경우
	 abbb|bbba
	 붙인 문자열의 앞에부터 하나씩 없애서 보면
	 abbb|bba
	 abbb|ba
	 abbb|a까지 확인을하면 세개다 팰린드롬문자가 되버림
	 
	 abab|baba	O
	 abab|aba	O
	 abab|ba	X
	 abab|a		O
	 가작 적은 문자열을 찾는다
	 
	 */
	static String n;
	static char[] a = new char[2002];
	static int len;
	
	//팰린드롬인지 확인하는 거
	public static boolean finish(int temp){
		for(int i=0; i<=temp/2-1; i++){
			//앞은 i  뒤는 len-i-1
			if(a[i]!=a[temp-i-1])
				return false;
		}
		return true;
	}
	public static void go(int cnt){
//		System.out.println("현재 len : " + len);
		int temp = len;
		
		for(int t=0; t<cnt; t++){
//			System.out.println("cnt : " + cnt + " len : " + len);
			
			for(int i=cnt+1; i<len; i++){
				if(a[i]==' ') break;
				a[i] = a[i+1];
			}
			--temp;
			
			/*System.out.println("temp : " + temp);
			
			for(int i=0; i<temp; i++){
				if(a[i]==' ') break;
				System.out.print(a[i]+" ");
			}
			System.out.println();*/
			
			if(finish(temp)) {
				len = Math.min(len, temp);
//				System.out.println("줄어들다 . . . "+len);
			}
		}
		

	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.next();
		
		len = n.length();	

		for(int i=0; i<len; i++)
			a[i] = n.charAt(i);
		
		if(!finish(len)){
			for(int i=len-1, j=len; i>=0; i--, j++)
				a[j] = a[i];
			int cnt = len-1;
			len *= 2;
			go(cnt);
		}
		System.out.println(len);
		in.close();
	}
}

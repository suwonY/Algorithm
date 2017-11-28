package week_10th_thu;

import java.util.*;
public class Baek1254 {

	/*팰린드롬 만들기
	 * 앞에서 읽으나 뒤에서 읽으나 같은 문자열
	 * 문자열이 주어졌을 때 
	 * 팰린드롬을 만들 수 있는 문자열의 최소 길이를 만들기*/
	static String n;
	static char[] a = new char[2002];
	static int len, max=0;
	public static boolean finish(){
		for(int i=0; i<len/2-1; i++){
			//앞은 i  뒤는 len-i-1
			if(a[i]!=a[len-i-1])
				return false;
		}
		return true;
	}
	public static void push(int num, char c){
		for(int i=len; i>=num; i--)
			a[i] = a[i-1];
		
		a[num] = c;
		++len;
		for(int i=0; i<len; i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
	public static void go(int start){
		System.out.println("start : " + start);
		System.out.println("len : " + len);
		//앞 뒤가 같지 않다면
		if(a[start]!=a[len-start-1]){
			System.out.println(len-start+"위치에 "+ a[start]+"가 들어가야 됨");
			push(len-start,a[start]);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.next();
		len = max = n.length();	

		for(int i=0; i<len; i++)
			a[i] = n.charAt(i);
		
		for(int i=0; i<max; i++)
			go(i);
		
		in.close();
	}

}

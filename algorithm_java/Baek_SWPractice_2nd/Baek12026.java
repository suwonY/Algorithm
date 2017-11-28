package Baek_SWPractice_2nd;

import java.util.*;
public class Baek12026 {

	static int n,ans;
	static String s;
	static char[] c = {'B','O','J'};
	static int[] d = new int[1001];
	static char[] a = new char[1001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();	//링크가 있는 곳
		s = in.next();		//문자열
		
		int index = 0;
		a = s.toCharArray();
		Queue<Integer> q = new LinkedList<>();
		for(int i=0; i<s.length(); i++){
			if(a[i]=='B'){
				index = i;
				break;
			}
		}
		
		
		in.close();
	}
}
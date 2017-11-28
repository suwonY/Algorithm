package Baek_SWPractice_2nd;

import java.util.Scanner;
import java.util.Stack;
public class Baek9012 {

	//얘 stack이용해서 풀어보기
	public static void main(String[] args) {
		Scanner in =new Scanner(System.in);
		int t = in.nextInt();
		Stack<Character> q = new Stack<>();
		for(int i=0; i<t; i++){
			String s = in.next();
			for(int j=0; j<s.length(); j++){
				if(s.charAt(j)=='(') q.push(s.charAt(j));
				else q.pop();
				
			}
		}
		in.close();
	}

}
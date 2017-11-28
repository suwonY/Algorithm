package pracAlgorithm;

import java.util.*;
public class Baek2661 {
	static int n;
	static boolean finish;
	static boolean ok(String num) {
	    int len = num.length();
	    int start = len - 1;
	    int end = len;
	    for (int i = 1; i <= len/2; i++) {
	    	String first = num.substring(start-i, end-i);
	    	String second = num.substring(start,end);
	        if (first.equals(second)) {
	            return false;
	        }
	        --start;
	    }
	    return true;
	}
	static void go(int len, String s){
		if(finish) return;
		if(!ok(s)) return;
		if(len==n){
			finish = true;
			System.out.println(s);
			return;
		}
		for(int i=1; i<=3; i++)
			go(len+1, s+i);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		go(1,"1");
		in.close();
	}
}
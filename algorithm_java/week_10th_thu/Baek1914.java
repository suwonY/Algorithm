package week_10th_thu;

import java.math.BigInteger;
import java.util.*;
public class Baek1914_fix2 {
	static int n;
	static BigInteger ans = new BigInteger("1");
	static BigInteger mul = new BigInteger("2");
	static BigInteger sub = new BigInteger("1");
	static int[][] a = new int[3000000][2];
	public static void go(int num, int from, int to, int spare){
		if(num==1){
			System.out.println(from+" "+to);
		}
		else{
			go(num-1,from,spare,to);
			System.out.println(from+" "+to);
			go(num-1,spare,to,from);
		}
	}
	public static void count(int n){
		for(int i=0; i<n; i++)
			ans = ans.multiply(mul);
		ans = ans.subtract(sub);
		System.out.println(ans);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		count(n);
		if(n<=20)
			go(n,1,3,2);
		in.close();
	}
}
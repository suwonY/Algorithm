package week_16th_thu;

import java.util.*;
public class Baek2616 {

	//소형기관차
	//1 최대로 끌수 있는 객차의 수를 정해논다(모두 같은 수를 끌수있다)
	//2 최대로 끌어야함
	//3 연속적으로 이어진 객차를 끈다
	static int n,k;
	static int[] a = new int[50001];
	static int[] s = new int[50001];
	static int[] d = new int[50001];
	static boolean[] c = new boolean[50001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=1; i<=n; i++){
			a[i] = in.nextInt();
			s[i] = s[i-1] + a[i];
		}
		k = in.nextInt();
		
		in.close();
	}
}
package pracAlgorithm;

import java.util.*;
public class Baek1759 {
	static char[] a;
	static int L, C;
	static void go(int index, int cnt, int mo, int ja, String s){
		if(cnt==L){
			if(mo>=1 && ja>=2) System.out.println(s);
			return;
		}
		if(index==C) return;
		if (a[index] == 'a' || a[index] == 'e' || a[index] == 'i' || a[index] == 'o' || a[index] == 'u')
			go(index + 1, cnt + 1, mo + 1, ja, s + a[index]);
		else
			go(index + 1, cnt + 1, mo, ja + 1, s + a[index]);
		go(index+1, cnt, mo, ja, s);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		L = in.nextInt();
		C = in.nextInt();
		a = new char[C];
		for(int i=0; i<C; i++)
			a[i] = in.next().charAt(0);
		Arrays.sort(a);
		go(0,0,0,0,"");
		in.close();
	}
}
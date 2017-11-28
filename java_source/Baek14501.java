package pracAlgorithm;
import java.util.*;
public class Baek14501 {
	static int n, ans;
	static int[] p = new int[16], t = new int[16];
	static void go(int day, int total){
		if(day==n){
			ans = Math.max(ans, total);
			return;
		}
		go(day+1, total);
		if(day+t[day]<=n)
			go(day+t[day], total+p[day]);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++){
			t[i] = in.nextInt();
			p[i] = in.nextInt();
		}
		go(0,0);
		System.out.println(ans);
		in.close();
	}
}

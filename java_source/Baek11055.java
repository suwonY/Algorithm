import java.util.*;
public class Baek11055 {
	static int n, ans;
	static int[] a = new int[1001], d = new int[1001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
			d[i] = a[i];
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j])
					d[i] = Math.max(d[j] + a[i], d[i]);
			}
			ans = Math.max(d[i], ans);
		}
		System.out.println(ans);
		in.close();
	}
}
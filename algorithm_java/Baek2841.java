import java.util.*;
public class Baek2841 {
	static Stack<Integer>[] a = new Stack[7];
	static int n, m, ans;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=1; i<=6; i++)
			a[i] = new Stack<>();
		for (int i = 0; i < n; i++) {
			int x = in.nextInt(), y = in.nextInt();
			
			if (a[x].size() == 0) {
				++ans;
				a[x].push(y);
				continue;
			}
			while (a[x].peek() > y) {
				++ans;
				a[x].pop();
				if (a[x].size() == 0) break;
			}
			if (a[x].size()!=0 && a[x].peek() == y) continue;
			++ans;
			a[x].push(y);
		}
		System.out.println(ans);
		in.close();
	}
}
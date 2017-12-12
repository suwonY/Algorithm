import java.util.*;
public class Baek2644_DFS {
	static int n, m, start, end, ans;
	static int[][] a = new int[101][101];
	static void go(int end, int now, int before, int cnt){
		if(end == now){
			ans = cnt;
			return;
		}
		for(int i=1; i<=n; i++){
			if(a[now][i]==0) continue;
			if(i==before) continue;
			go(end, i, now, cnt+1);
		}
	}
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		start = in.nextInt();
		end = in.nextInt();
		m = in.nextInt();

		for (int i = 0; i < m; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			a[x][y] = a[x][y] = 1;
		}
		go(end, start, 0, 0);
		System.out.println(ans!=0?ans:-1);
		in.close();
	}
}
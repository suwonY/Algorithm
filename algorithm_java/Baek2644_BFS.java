import java.util.*;
public class Baek2644_BFS {
	static int n, m, start, end, ans;
	static int[][] a = new int[101][101];
	static int[] d = new int[101];
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		start = in.nextInt();
		end = in.nextInt();
		m = in.nextInt();

		for (int i = 0; i < m; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			a[x][y] = a[y][x] = 1;
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()){
			int now = q.remove();
			for(int i=1; i<=n; i++){
				if(a[now][i]==0 || d[i]!=0) continue;
				d[i] = d[now]+1;
				q.add(i);
			}
		}
		System.out.println(d[end]==0?-1:d[end]);
		in.close();
	}
}
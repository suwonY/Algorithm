import java.util.*;
public class Baek2210 {
	static Set<Integer> ans = new HashSet<>();
	static int[][] a = new int[5][5];
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static void go(int cnt, int x, int y, int num){
		if(cnt==6){
			ans.add(num);
			return;
		}
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=5 || ny<0 || ny>=5) continue;
			go(cnt+1, nx, ny, num*10+a[nx][ny]);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++)
				a[i][j] = in.nextInt();
		
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++)
				go(1,i,j,a[i][j]);
		
		System.out.println(ans.size());
		
		in.close();
	}
}
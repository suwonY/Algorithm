package week_13th_mon;
import java.util.*;
public class Baek2873_fix1 {
	/*롤러코스터
	 * 놀이공원은 야외에 있고 
	 * 직사각형에
	 * 각 칸에 탑승자가 얻을수 있는 기쁨을 나타낸 숫자가 적혀있다
	 * 런타임 에러 메모리 초과나는거같다...하... 씨바
	 * */
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int n,m,sum=0;
	static int[][] a;
	static boolean[][] c;
	static String ans = "";
	public static void print(String temp){
		for(int i=0; i<temp.length(); i++){
			if(temp.charAt(i)=='0') System.out.print("U");
			else if(temp.charAt(i)=='1') System.out.print("D");
			else if(temp.charAt(i)=='2') System.out.print("L");
			else if(temp.charAt(i)=='3') System.out.print("R");
		}
	}
	public static void go(int x, int y, String temp, int total){
		if(x==n-1 && y==m-1){
			if(total>=sum){
				sum = total;
				ans = temp;
				return;
			}
		}
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
			if(c[nx][ny]) continue;
			
			c[nx][ny] = true;
			go(nx,ny,temp+String.valueOf(i),total+a[nx][ny]);
			c[nx][ny] = false;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		a = new int[n][m];
		c = new boolean[n][m];
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				a[i][j] = in.nextInt();
		c[0][0] = true;
		go(0,0,"",0);
		print(ans);
		in.close();
	}
}
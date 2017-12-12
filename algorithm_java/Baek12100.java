import java.util.*;
public class Baek12100 {
	static int n,ans;
	static Queue<Integer> q = new LinkedList<>();
	public static int findMax(int[][] a){
		int max = 0;
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++){
				if(a[i][j]<=max) continue;
				max = Math.max(max, a[i][j]);
			}
		return max;
	}
	public static int[][] up(int[][] a){
		boolean[][] c = new boolean[n][n];
		for(int j=0; j<n; j++){
			for(int i=1; i<n; i++){
				if(a[i][j]==0) continue;
				int num = a[i][j];
				for(int k=i-1; k>=0; k--){
					if(a[k][j]==0) continue;
					if(a[k][j]!=num || c[k][j]) break;
					a[k][j]*=2;
					c[k][j]=true;
					a[i][j]=0;
					break;
				}
			}
		}
		for(int j=0; j<n; j++){
			for(int i=0; i<n; i++){
				if(a[i][j]==0) continue;
				q.add(a[i][j]);
				a[i][j]=0;
			}
			int index = 0;
			while(!q.isEmpty()) a[index++][j] = q.remove();
		}
		return a;
	}
	public static int[][] down(int[][] a){
		boolean[][] c = new boolean[n][n];
		for(int j=0; j<n; j++){
			for(int i=n-2; i>=0; i--){
				if(a[i][j]==0) continue;
				int num = a[i][j];
				for(int k=i+1; k<n; k++){
					if(a[k][j]==0) continue;
					if(a[k][j]!=num || c[k][j]) break;
					a[k][j]*=2;
					c[k][j]=true;
					a[i][j]=0;
				}
			}
		}
		for(int j=0; j<n; j++){
			for(int i=n-1; i>=0; i--){
				if(a[i][j]==0) continue;
				q.add(a[i][j]);
				a[i][j]=0;
			}
			int index = n-1;
			while(!q.isEmpty()) a[index--][j] = q.remove();
		}
		return a;
	}
	public static int[][] left(int[][] a){
		boolean[][] c = new boolean[n][n];
		for(int i=0; i<n; i++){
			for(int j=1; j<n; j++){
				if(a[i][j]==0) continue;
				int num = a[i][j];
				for(int k=j-1; k>=0; k--){
					if(a[i][k]==0) continue;
					if(a[i][k]!=num || c[i][k]) break;
					a[i][k]*=2;
					c[i][k]=true;
					a[i][j]=0;
					break;
				}
			}
		}
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(a[i][j]==0) continue;
				q.add(a[i][j]);
				a[i][j]=0;
			}
			int index = 0;
			while(!q.isEmpty()) a[i][index++] = q.remove();
		}
		return a;
	}
	public static int[][] right(int[][] a){
		boolean[][] c = new boolean[n][n];
		for(int i=0; i<n; i++){
			for(int j=n-2; j>=0; j--){
				if(a[i][j]==0) continue;
				int num = a[i][j];
				for(int k=j+1; k<n; k++){
					if(a[i][k]==0) continue;
					if(a[i][k]!=num || c[i][k]) break;
					a[i][k]*=2;
					c[i][k]=true;
					a[i][j]=0;
					break;
				}
			}
		}
		for(int i=0; i<n; i++){
			for(int j=n-1; j>=0; j--){
				if(a[i][j]==0) continue;
				q.add(a[i][j]);
				a[i][j]=0;
			}
			int index = n-1;
			while(!q.isEmpty()) a[i][index--] = q.remove();
		}
		return a;
	}
	public static void go(int cnt, int[][] a){
		if(cnt==5){
			ans = Math.max(ans, findMax(a));
			return;
		}
		int[][] map = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				map[i][j] = a[i][j];
		go(cnt+1,up(map));
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				map[i][j] = a[i][j];
		go(cnt+1,down(map));
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				map[i][j] = a[i][j];
		go(cnt+1,left(map));
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				map[i][j] = a[i][j];
		go(cnt+1,right(map));
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		int[][] a = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				a[i][j] = in.nextInt();
		go(0,a);
		System.out.println(ans);
		in.close();
	}
}
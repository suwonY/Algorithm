package week_15th_mon;

public class kakao04_fix1 {
	static final int INF = 987654321;
	static int[] dx = {-1,0,-1};
	static int[] dy = {0,-1,-1};
	public static int square(int[][] a){
		int ans = 0;
		int n = a.length;
		int m = a[0].length;
		int[][] d = new int[n][m];
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(a[i][j]==0) continue;
				if(i==0 || j==0){
					if(a[i][j]==1) d[i][j]=1;
					continue;
				}
				
				int min = INF;
				boolean next = true;
				for(int k=0; k<3; k++){
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if(a[nx][ny]==0) {
						next = false;
						break;
					}
					min = Math.min(min, d[nx][ny]);
				}
				if(!next) d[i][j] = 1;
				if(next) d[i][j] = min + 1;
				ans = Math.max(ans, d[i][j]);
			}
		}
		return ans*ans;
	}
	
	public static void main(String[] args) {
		int[][] a = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
		int[][] b = {{0,0,1,1},{1,1,1,1}};
		
		System.out.println(square(a));
		System.out.println(square(b));
		
	}
}

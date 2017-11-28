package week_15th_thu;

public class kakao_level4_01 {
	static final int INF = 987654321;
	static int[] dx = {-1,0,-1};
	static int[] dy = {0,-1,-1};
	public static int go(char[][] a ){
		int ans = 0;
		int n = a.length;
		int m = a[0].length;
		int[][] d = new int[n][m];
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(a[i][j]=='X') continue;
				if(i==0 || j==0){
					if(a[i][j]=='O') d[i][j]=1;
					continue;
				}
				
				int min = INF;
				boolean next = true;
				for(int k=0; k<3; k++){
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if(a[nx][ny]=='X') {
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
		char [][] a ={
				{'X','O','O','O','X'},
				{'X','O','O','O','O'},
				{'X','X','O','O','O'},
				{'X','X','O','O','O'},
				{'X','X','X','X','X'}};
		
		System.out.println(go(a));
		
	}

}

package week_15th_mon;

public class kakao04 {

	public static boolean ok(int[][] a,int x, int y, int max){
		for(int i=x; i<x+max; i++)
			for(int j=y; j<y+max; j++)
				if(a[i][j]==0) return false;
		return true;
	}
	public static int square(int[][] a){
		int n = a[0].length;
		int m = a.length;
		int maxX = n;
		int maxY = m;
		int max = 0;
		//일단 0이있는지 0이있으면 첫째줄에서는 max값이 작아져도됨
		for(int i=0; i<a.length; i++){
			if(a[0][i]==0)
				--maxX;
			if(a[i][0]==0)
				--maxY;
		}
		max = Math.max(maxX, maxY);
		
		while(true){
			boolean finish = false;
			
			again:
			for(int i=0; i<=n-max; i++)
				for(int j=0; j<=n-max; j++){
					if(a[i][j]==0) continue;
					finish = ok(a,i,j,max);
					if(finish) break again;
				}
			
			if(!finish) --max;
			if(finish) break;
		}
		return max*max;
	}
	
	public static void main(String[] args) {
		int[][] a = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
		int[][] b = {{0,0,1,1},{1,1,1,1}};
		
		System.out.println(square(a));
		System.out.println(square(b));
		
	}
}

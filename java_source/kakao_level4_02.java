package week_15th_thu;

public class kakao_level4_02 {

	public static int go(int start, int[][] a){
		int sum = 0;
		for(int i=0; i<a.length; i++){
			if(i==start) continue;
			int len = a[start][0] - a[i][0];
			sum += (len*a[i][1]);
		}
		return sum;
	}
	public static int go1(int n, int[][] a){
		int ans = 0;
		int min = 987654321;
		int[] d = new int[n];
		for(int i=0; i<n; i++){
			d[i] = go(i,a);
			min = Math.min(min, d[i]);
		}
		for(int i=0; i<n; i++){
			if(d[i]==min){
				ans = i;
				break;
			}
		}
		return ans+1;
	}
	public static void main(String[] args) {
		int[][] a = {{1,5},{2,2},{3,3}};
		
		System.out.println(go1(3,a)+1);
	}

}

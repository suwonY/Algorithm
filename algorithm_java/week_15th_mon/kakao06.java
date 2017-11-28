package week_15th_mon;
public class kakao06 {
	/*
	 	d[i][0]은 첫번째 스티커를 선택하지 않았을 때
	 	d[i][1]은 첫번째 스티커를 선택했을 때
	 	
	 	d[i]는 d[i-1]과 d[i-2]+a[i]의 최대값이 된다
	 	d[i][0]은 첫번째를 선택하지 않았기 때문에
	 	d[0][0] = 0;
	 	d[1][0] = a[1];
	 
	 	d[i][0] 는
	 	1~n-1까지
	 	
	 	d[i][1]은 첫번째를 선택했기 때문에
	 	d[1][0] = a[0]
	 	d[1][1] = Math.max(d[1][1], a[1]);
	 * */
	public static int go(int[] a){
		int n = a.length;
        int[][] d = new int[n][2];
		
		for(int i=0; i<n; i++){
			if(i==0){
				d[0][0] = 0;
		        d[0][1] = a[0];
		        continue;
			}
			if(i==1){
				d[i][0] = a[1];
				d[i][1] = Math.max(d[0][1], a[1]);
				continue;
			}
			if(i==n-1){
				d[i][0] = Math.max(d[i-2][0]+a[i], d[i-1][0]);
				continue;
			}
			d[i][0] = Math.max(d[i-2][0]+a[i], d[i-1][0]);
			d[i][1] = Math.max(d[i-2][1]+a[i], d[i-1][1]);
		}
		int max = Math.max(d[n-2][1], d[n-2][0]);
		max = Math.max(d[n-1][1],max);
		return Math.max(max, d[n-1][0]);
	}
	
	public static void main(String[] args) {
		int[] a = {14,6,5,11,3,9,2,10};
		int[] b = {1,3,2,5,4};
		
		System.out.println(go(a));
		System.out.println(go(b));
	}
}
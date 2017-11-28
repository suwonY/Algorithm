package week_15th_thu;

public class kakao_level6_01 {

	static final int mod = 100000;
	public static int go(int n){
		long[] d = new long[n+1];
		if(n==1) return 0;
		if(n==2) return 3;
		if(n==3) return 0;
		if(n==4) return 11;
		d[0] = 1;
		d[1] = 0;
		d[2] = 3;
		
		/*for(int i=4; i<=n; i++){
			if(i%2!=0) d[i] = 0;
			d[i]+=d[i-2]*3%mod;
			if(i%4==0) d[i]+=(d[i-4]*2%mod);
		}*/
		
		for(int i =4; i<=n; i++){
			d[i] = d[i-2]*3%mod;
			for(int j=4; j<=i; j+=2){
				d[i]+=(d[i-j]*2%mod);
			}
		}
		return (int) (d[n]%mod);
	}
	public static void main(String[] args) {
		System.out.println(go(786));
	}

}

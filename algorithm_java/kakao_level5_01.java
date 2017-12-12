package week_15th_thu;

public class kakao_level5_01 {

	static final int mod = 100000;
	public static int go(int n){
		long[] d = new long[n+1];
		if(n==0) return 0;
		if(n==1) return 1;
		if(n==2) return 2;
		d[0]=0;
		d[1]=1;
		d[2]=2;
		for(int i=3; i<=n; i++)
			d[i] = (d[i-2] + d[i-1])%mod;
		return (int) (d[n]%mod);
	}
	public static void main(String[] args) {
		System.out.println(go(268));
	}
}

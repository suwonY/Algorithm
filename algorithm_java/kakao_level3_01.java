package week_15th_thu;


public class kakao_level3_01 {
	public static int cnt(int a){
		int num = 0;
		while(a!=0){
			int max = 0;
			while((int)Math.pow(2, max) <= a)
				++max;
			a -= Math.pow(2, max-1);
			++num;
		}
		return num;
	}
	public static int go(int a){
		int ans = 0;
		int num = cnt(a);
		for(int i=a+1; i<=1000000; i++){
			if(cnt(i)==num){
				ans = i;
				break;
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		//1001110 = 78
		//1010011
		
		int n = 78;
		System.out.println(go(n));
//		go(n);
//		n = n - (int) Math.pow(2, cnt(n));
//		System.out.println(n);
//		n = n - (int) Math.pow(2, cnt(n));
//		System.out.println(n);
		
	}

}

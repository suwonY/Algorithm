package week_15th_thu;

public class kakao_level7_01_fix1 {

	static long max =  10000000;
	public static int[] go(long begin, long end){
		int last = (int) (end-begin+1);	//어차피 ans는 int형이기 때문에 뺀값은 int형 안에 나올거임
		int length = (int) last;
		int[] ans = new int[length];
		boolean[] c = new boolean[length];
		int index = 0;
		
		if(end<max)
			max = (int)end;
		
		while(index!=length){
			
			for(long i=begin; i<=end; i++){
				
				for(int j=1; j*max<=end; j++){
					if(i%j!=0) continue;
				}
			}
			
		}
		
		
		return ans;
	}
	public static void main(String[] args) {
		long a = 99999999999990L;
		long b = 100000000000000L;
		int length = (int) (b-a)+1;
		int[] ans = new int[length];
		ans = go(99999999999990L, 100000000000000L);
		for(int i=0; i<length; i++){
			System.out.print(ans[i]+" ");
		}
	}

}

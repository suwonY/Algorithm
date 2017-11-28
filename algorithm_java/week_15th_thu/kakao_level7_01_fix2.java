package week_15th_thu;

public class kakao_level7_01_fix2 {

	static int max =  10000000;
	public static int[] go(long begin, long end){
		 int length = (int) (end-begin)+1;
	      int[] ans = new int[length];
	      int index = 0;
	      for(long i = begin; i<=end; i++){
	        for(int j = (int) Math.sqrt(max); j>0; j--){
	          if(i%j!=0) continue;
	          ans[index++]=j;
	          break;
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

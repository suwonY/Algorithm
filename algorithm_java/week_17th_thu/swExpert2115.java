package week_17th_thu;
import java.util.*;
public class swExpert2115_fix1 {
	static int n,m,max;
	static long ans;
	static int[][] a;
	static boolean[][] c1;
	static boolean[][] c2;
	static int dx = 0, dy = 1;
	public static boolean next_permutation(int[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i])  i -= 1;
        if (i <= 0)  return false;
        int j = a.length-1;
        while (a[j] <= a[i-1])  j -= 1;
        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;
        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }
	public static long getMax(int[] w){
		int len1 = w.length;
		long ans = 0;
		do{
			long temp = 0;
			int index = 0;
			for(int i=0; i<len1; i++){
				if(temp+w[i]>max) break;
				temp+=w[i];
				++index;
			}
			temp = 0;
			for(int i=0; i<index; i++)
				temp += (w[i]*w[i]);
			ans = Math.max(temp, ans);
		}
		while(next_permutation(w));
		
		return ans;
	}
	public static void go(){
		int[] w1 = new int[m];
		int[] w2 = new int[m];
		c1 = new boolean[n][n];
		c2 = new boolean[n][n];
		//1번이 도는 동안 확인
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(j+m>n) continue;
//				System.out.println("1번이 : " + i + "," + j + "일 때");
				w1 = new int[m];
				c1[i][j] = true;

				for(int t=0; t<m; t++){
					c1[i][j+t] = true;
					w1[t]=a[i][j+t];
				}
				
				//2번을 돌면서 확인
				for(int k=0; k<n; k++){
					for(int l=0; l<n; l++){
						if(l+m>n) continue;
						if(c1[k][l]) continue;
						boolean im = false;
						for(int t=1; t<m; t++){
							if(c1[k][l+t]) {
								im = true;
								break;
							}
						}
						if(im) continue;
						w2 = new int[m];
//						System.out.println("2번이 : " + k + "," + l + "일 때");
						for(int t=0; t<m; t++) w2[t]=a[k][l+t];
						long num1 = getMax(w1);
						long num2 = getMax(w2);
//						System.out.println(num1+num2);
						ans = Math.max(ans, num1+num2);
						c2 = new boolean[n][n];
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			m = in.nextInt();
			max = in.nextInt();
			a = new int[n][n];
			ans = 0;
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					a[i][j] = in.nextInt();
			go();
			System.out.println("#"+t+" "+ans);
		}
		in.close();
	}
}
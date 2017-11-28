package week_13th_mon;

import java.util.*;
public class co_mt_fix1 {

	/*mt게임
	 * 
	 * 베스킨 라빈스 31 응용한 게임
	 * */
	static int n,k,c;
	static String ans;
	static int x,y;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			x = in.nextInt();
			y = in.nextInt();
			c = in.nextInt();
			ans = "";
			for(int i=0; i<c; i++){
				n = in.nextInt();
				k = in.nextInt();
				if(x*k==n-1){
					ans+='a';
					continue;
				}
				n = n- x + 1;
				while(true){
					n -=  (y*k+1);
					if(n<0){
						n += (y*k+1);
						break;
					}
				}
				if(n>x)
					ans+='a';
				else if(n<=x)
					ans+='b';
			}
			System.out.println("Case #"+t);
			System.out.println(ans);
		}
		in.close();
	}
}
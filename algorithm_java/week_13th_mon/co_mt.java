package week_13th_mon;

import java.util.*;
public class co_mt_fix2 {

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
				
				int a,b;
				int minA = x*1;
				int maxA = x*k;
				int minB = y*1;
				int maxB = y*k;
				
				int temp = n - minB;
				
				while(true){
					temp = temp - maxA + 1;
					temp = temp - minB;
					if(temp<=maxA){
						a = temp;
						break;
					}
					temp = temp - maxA + 1;
				}
				
				temp = n - minA;
				while(true){
					temp = temp - maxB + 1;
					temp = temp - minA;
					if(temp<=maxB){
						b = temp;
						break;
					}
					temp = temp - maxB + 1;
				}
				
				if(a<b)
					ans+='a';
				else
					ans+='b';
			}
			System.out.println("Case #"+t);
			System.out.println(ans);
		}
		in.close();
	}
}
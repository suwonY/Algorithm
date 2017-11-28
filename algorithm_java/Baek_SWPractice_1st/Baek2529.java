package Baek_SWPractice_1st;

import java.util.*;
public class Baek2529 {

	static final int INF=987654321;
	static int k,min=INF,max=0;
	static String minA, maxA;
	static boolean[] a = new boolean[11];
	static boolean[] c = new boolean[11];
	public static void go(int cnt,String num,int before){
		if(cnt==(k+1)){
			/*if(min>temp){
				min = temp;
				minA = num;
			}
			if(max<temp){
				max = temp;
				maxA = num;
			}*/
			return;
		}
		
		if(cnt==0)
			for(int i=0; i<=9; i++){
				c[i] = true;
				go(cnt+1,num+i,i);
				c[i] = false;
			}

		if(cnt!=0){
			//false면 < 큰값이 가야된다
			if(a[cnt-1]){
				for(int i=before+1; i<=9; i++){
					if(c[i]) continue;
					c[i] = true;
					go(cnt+1,num+i,i);
					c[i] = false;
				}
			}
			//true면 > 작은 값이 와야된다
			else{
				for(int i=before-1; i>=0; i--){
					if(c[i]) continue;
					c[i] = true;
					go(cnt+1,num+i,i);
					c[i] = false;
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*k = in.nextInt();
		for(int i=0; i<k; i++){
			String t = in.next();
			if(t.charAt(0)=='>') continue;
			a[i] = true;
		}
		go(0,"",0);
		System.out.println(minA);
		System.out.println(maxA);*/
		String a = "1";
		String b = "2";
		//크면 
		System.out.println(b.compareTo(a));
		in.close();
	}
}
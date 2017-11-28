package week_16th_thu;

import java.util.*;
public class Baek2529_fix1 {

	static final int INF=987654321;
	static int k;
	static String min="9999999999", max="0";
	static boolean[] a = new boolean[11];
	static boolean[] c = new boolean[11];
	public static void go(int cnt,String num,int before){
		if(cnt==(k+1)){
			//����ũ�� 1 �ڰ�ũ�� -1
			if(min.compareTo(num)>0) min = num;
			//max�� �� �������
			if(max.compareTo(num)<0) max = num;
			return;
		}
		
		if(cnt==0)
			for(int i=0; i<=9; i++){
				c[i] = true;
				go(cnt+1,num+i,i);
				c[i] = false;
			}

		if(cnt!=0){
			//false�� < ū���� ���ߵȴ�
			if(a[cnt-1]){
				for(int i=before+1; i<=9; i++){
					if(c[i]) continue;
					c[i] = true;
					go(cnt+1,num+i,i);
					c[i] = false;
				}
			}
			//true�� > ���� ���� �;ߵȴ�
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
		k = in.nextInt();
		for(int i=0; i<k; i++){
			String t = in.next();
			if(t.charAt(0)=='>') continue;
			a[i] = true;
		}
		go(0,"",0);
		System.out.println(max);
		System.out.println(min);
		in.close();
	}
}
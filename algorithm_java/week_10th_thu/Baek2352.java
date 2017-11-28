package week_10th_thu;

import java.util.*;
public class Baek2352 {

	/*�ݵ�ü ����
	 * n���� ����(1~40000)
	 * 1����Ʈ�� ����Ǿ�� �ϴ� ��Ʈ��ȣ,~
	 * */
	static int n, ans=0;
	static int[] a = new int[40001];
	static int[] d = new int[40001];
	public static void lis(){
		for(int i=1; i<=n; i++){
			if(d[i]==0) d[i]=1;
			for(int j=1; j<i; j++){
				if(a[i]>a[j]){
					d[i] = Math.max(d[i], d[j]+1);
					ans = Math.max(d[i], ans);
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=1; i<=n; i++){
			a[i] = in.nextInt();
		}
		lis();
		System.out.println(ans);
		in.close();
	}
}

package week_12th_thu;

import java.util.*;
public class Baek1102 {
	/*������
	 * 
	 * �����̴� �����̰� ������ ���� �����Ҹ� ��ġ�� ���ϸ� ©����
	 * �����Ҹ� ��ġ�� ����� 
	 * ���峪�� ���� �����Ҹ� �̿��Ͽ� ���峭 �����Ҹ� ������Ѵ�
	 * � �����Ҹ� ������ϳĿ� ���� ������ �ٸ�*/
	static int n,p;
	static int[][] a = new int[17][17];
	static boolean[] on = new boolean[17];
	static String start = "";
	static int[] d = new int[17];
	static int ans = 987654321;
	static int index = 1;
	public static String change(String temp, int index, char c){
		char[] ch = temp.toCharArray();
		ch[index] = c;
		return String.copyValueOf(ch);
	}
	public static void go(int total, String now, int cnt){
		if(cnt>=p){
			ans = Math.min(total, ans);
			index++;
			return;
		}
		for(int i=0; i<n; i++){
			//�����ִ°� �ϴ� �н�
			if(now.charAt(i)=='0') continue;
			//���������� ���� �ŷ� ���� �Ÿ� �Ѻ���
			for(int j=0; j<n; j++){
				if(now.charAt(j)=='1') continue;	//������ Ȯ���� �ʿ� ����
				String next = change(now,j,'1');
				go(total+a[i][j], next, cnt+1);
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				a[i][j]=in.nextInt();
		
		int cnt = 0;
		String temp = in.next();
		for(int i=0; i<n; i++){
			if(temp.charAt(i)=='Y') {
				start += "1";
				++cnt;
			}
			else start += "0";
		}
		p = in.nextInt();
		
		if(cnt==0) System.out.println(-1);
		else{
			go(0,start,cnt);
			System.out.println(ans);
		}
		in.close();
	}
}

package week_16th_mon;

import java.util.*;
public class Baek1062 {

	//����ħ
	//anta�� ����
	//tica�� ������
	//k���� ���ڸ� ����ĥ �ð� �ۿ�����
	//n���� �ܾ�ۿ� ����
	static int n, k, ans=0, alpha=0;
	static List<char[]> a = new ArrayList<>();
	static boolean[] c = new boolean[26];
						  //����ģ����	//�����ľ��Ұ��� //�����Ѵܾ��
	public static void go(int cnt, int num, int max){
		if(cnt==num){
			ans = Math.max(ans, max);
			return;
		}
		
		
	}
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		if(k<5){
			System.out.println(0);
			return;
		}
		alpha=k-5;	//������� �ܾ� ������ acnti(5��) ����
		for(int i=0; i<n; i++){
			String temp = in.next();
			temp = temp.replaceAll("a", "");
			temp = temp.replaceAll("c", "");
			temp = temp.replaceAll("n", "");
			temp = temp.replaceAll("t", "");
			temp = temp.replaceAll("i", "");
			
			char[] t = temp.toCharArray();
			Arrays.sort(t);
			for(int j=0; j<t.length; j++)
				if(!c[t[j]-'a']) c[t[j]-'a']=true;
			a.add(t);
		}
		
		//a n t i c
		c['a'-'a'] = true;
		c['c'-'a'] = true;
		c['n'-'a'] = true;
		c['t'-'a'] = true;
		c['i'-'a'] = true;
		
		in.close();
	}
}
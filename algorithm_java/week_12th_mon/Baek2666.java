package week_12th_mon;

import java.util.*;
public class Baek2666_fix2 {
	/*���幮�� �̵�
	 * 
	 �ݷʰ� ����µ�
	 ���� 3����
	 1,2 �� �����ִ»��¿���
	 3 2 3 ������� ����ϰ� ���� ��
	 ���ʲ��� ���ֳ� �����ʲ��� �Ű��ֳĿ� ���� 2,3 ����� ���´�
	 
	 �׷� ����
	 ���ʿ��� �ű�ų� �����ʿ��� �ű�� ����� �� �߿��� �ּҰ��� ������ �ȴ�.
	 
	 d[n]
	 a[n][0] �� ���ʿ��� ���ܿͼ� n�� ������ �� �� �ּҰ�
	 a[n][1] �� �����ʿ��� ���ܿͼ� n�� ������ �ϴ� �ּҰ�
	 d[n] = a[n][0] �� a[n][1]�� min���� �ȴ�
	 
	 * */
	static int n, ans = 987654321; //������ 3<=n<=20
	static int k; //�̿��� ������ ����
	static int[] open = new int[2];
	static List<Integer> arr = new ArrayList<>();
	public static void go(int l, int r, int cnt, int total){
		if(cnt==k){
			ans = Math.min(total, ans);
			return;
		}
		int num = arr.get(cnt);
		//���ʲ��� ���ܴ� �� ���
		if(l==num) 
			go(l,r,cnt+1,total);
		
		else if(l!=num)
			go(num,r,cnt+1,total + Math.abs(num-l));
		
		//�����ʲ��� ���ܴ� �� ���
		if(r==num)
			go(l,r,cnt+1,total);
		
		else if(r!=num)
			go(l,num,cnt+1,total+Math.abs(num-r));
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<2; i++){
			int o = in.nextInt();
			open[i] = o;
		}
		
		Arrays.sort(open);
		
		k = in.nextInt();
		for(int i=0; i<k; i++)
			arr.add(in.nextInt());
		
		go(open[0],open[1],0,0);
		System.out.println(ans);
		
		in.close();
	}
}
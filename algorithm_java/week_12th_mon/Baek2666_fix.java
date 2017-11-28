package week_12th_mon;

import java.util.*;
public class Baek2666_fix {
	/*���幮�� �̵�
	 * 
	 �ݷʰ� ����µ�
	 ���� 3����
	 1,2 �� �����ִ»��¿���
	 3 2 3 ������� ����ϰ� ���� ��
	 ���ʲ��� ���ֳ� �����ʲ��� �Ű��ֳĿ� ���� 2,3 ����� ���´�
	 
	 �׷� ����
	 ���ʿ��� �ű�ų� �����ʿ��� �ű�� ����� �� �߿��� �ּҰ��� ������ �ȴ�.
	 
	 * */
	static int n; //������ 3<=n<=20
	static int k; //�̿��� ������ ����
	static boolean[] ropen = new boolean[21];
	static boolean[] lopen = new boolean[21];
	static List<Integer> a = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<2; i++){
			int o = in.nextInt();
			lopen[o] = ropen[o] = true;
		}
		
		k = in.nextInt();
		for(int i=0; i<k; i++)
			a.add(in.nextInt());
		
		int left = 0;
		for(int i : a){
			//���������� �ٷ� ����ȴ�.
			if(lopen[i]) 
				continue;
			//�������� �ʴٸ�
			for(int j=0; j<a.size(); j++){
				//�����ʲ��� �����ְų�
				if(i+j<=n && lopen[i+j]){
					left += j;
					lopen[i+j]=false;
					lopen[i]=true;
					break;
				}
				//���ʲ� �����ְų�
				if(i-j>0 && lopen[i-j]){
					left += j;
					//i-j�� close ���ְ� i�� open���ش�
					lopen[i-j]=false;
					lopen[i]=true;
					break;
				}
			}
		}
		int right = 0;
		for(int i : a){
			//���������� �ٷ� ����ȴ�.
			if(ropen[i])
				continue;
			//�������� �ʴٸ�
			for(int j=0; j<a.size(); j++){
				//���ʲ� �����ְų�
				if(i-j>0 && ropen[i-j]){
					right += j;
					//i-j�� close ���ְ� i�� open���ش�
					ropen[i-j]=false;
					ropen[i]=true;
					break;
				}
				//�����ʲ��� �����ְų�
				if(i+j<=n && ropen[i+j]){
					right += j;
					ropen[i+j]=false;
					ropen[i]=true;
					break;
				}
			}
		}
		System.out.println(Math.min(left, right));
		in.close();
	}
}
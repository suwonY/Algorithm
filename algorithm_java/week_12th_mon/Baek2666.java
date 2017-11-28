package week_12th_mon;

import java.util.*;
public class Baek2666 {
	/*���幮�� �̵�
	 * 
	 * ���幮�� ���������� �̿��� ������ ���� �ڽſ��� �ű� �� �ִ�
	 * �̿��ϰ� ���� ������ �־����� �� �ּ�Ƚ���� ������ ������ �̿��� ����� ã��
	 * 
	 * d[n]�� n�� ����ϱ� ���� �ּ� �̵�Ƚ����� ����
	 * d[n]��
	 * ���������� 0
	 * if(open[n]) d[n]=0;
	 * �������� �ʴٸ�
	 * n-i, n+i�� �ּҰ��� ���Ѵ� 
	 * n-i�� 0���� Ŭ������ n+i<
	 * 
	 * */
	static int n; //������ 3<=n<=20
	static int k; //�̿��� ������ ����
	static boolean[] open = new boolean[21]; //�����ִ� ���� - ���������� true
	static List<Integer> a = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<2; i++)
			open[in.nextInt()]=true;
		
		k = in.nextInt();
		for(int i=0; i<k; i++)
			a.add(in.nextInt());
		
		int ans = 0;
		for(int i : a){
			//���������� �ٷ� ����ȴ�.
			if(open[i]) continue;
			//�������� �ʴٸ�
			for(int j=0; j<a.size(); j++){
				//���ʲ� �����ְų�
				if(i-j>0 && open[i-j]){
					ans += j;
					//i-j�� close ���ְ� i�� open���ش�
					open[i-j]=false;
					open[i]=true;
					break;
				}
				//�����ʲ��� �����ְų�
				else if(i+j<=n && open[i+j]){
					ans += j;
					open[i+j]=false;
					open[i]=true;
					break;
				}
			}
		}
		System.out.println(ans);
		in.close();
	}
}
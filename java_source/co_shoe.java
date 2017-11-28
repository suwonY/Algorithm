package week_12th_thu;

import java.util.*;
public class co_shoe {

	/*��������
	 * 
	 * �ֹ����� ������ �� N
	 * �ֹ� �Ƿڽð� a
	 * �����ð� f
	 * ���۽ð� p
	 * 
	 * ���δ� �Ѹ��̳� �������� �����Ѵ�
	 * �ڱ� �ٹ��ð��� ���θ� �����
	 * ���θ��� �ٹ����۽ð� s
	 *       �ٹ������ð� e
	 * �� ������ �� �̻��� ���� ���ۿ� ������ �� ����
	 * ������ �Ѱ� ���θ� ����
	 * */
	static int n,k,ans;
	static int smax,pmax;	//���� �����ð� �ִ�, ���� �����ð� �ִ�
	static int[][] d; //���ι�ȣ�� ���۱Ⱓ�� ��Ÿ���� �迭
	static int[] hour;
	static int[] cnt;
	static boolean[][] work; //������ �ٹ��ϴ� �ð��� ����
	static int[] a;
	static int[] f;	//�ּ� ���� ��¥(�����ð� - ���۽ð�)
	static int[] s;
	static int[] e;
	public static boolean finish(int[] h){
		for(int i=1; i<=n; i++)
			if(h[i]!=0) return false;
		return true;
	}
						//���糯¥	   //���λ��� 		    //�������۱Ⱓ
	public static void go(int day,int[] h){
		//�� ���θ��� �ּ� ���۳�¥(�����ð� - ���۽ð�) �ȿ� ���� ������ ���۵Ǿ�� �Ѵ�
		//���� ���� �ּҽ��۳�¥�� �Ѿ ���ΰ� �ִٸ� �Ұ���
		//�ּҽ��۳� ¥�� ���� ���� �ð� - ���� ��¥�� ���� ���� �ð��� ���ؼ�
		//���糯¥�� ���� ���۽ð��� 
	
		//���࿡ ������ �� ������ ���ϴµ� ���ΰ� �����ִٸ� �Ұ���
		if(day>pmax)
			if(!finish(h)) return;
		
		//���� �����ٸ� ����
		if(finish(h)){
			ans = 1;
			return;
		}
		for(int i=1; i<=k; i++){
			if(!work[i][day]) continue; //�ٹ��ð��� �ƴϸ� �н�
			for(int j=1; j<=n; j++){
				if(a[j]>day) continue; //�Ƿ� ��¥�� �ȵ� ��� �н�
				--h[j];			//�ش� �������۽ð��� 1 �ٿ��ְ�
				go(day+1,h);
				++h[j];
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			d = new int[201][101];
			hour = new int[201];
			work = new boolean[101][101];
			a = new int[201];
			f = new int[201];	//�ּ� ���� ��¥(�����ð� - ���۽ð�)
			s = new int[101];
			e = new int[101];
			cnt = new int[101];
			ans = 0;
			smax = pmax = 0;
			
			n = in.nextInt();//�ֹ����� ������ ��
			k = in.nextInt();//������ ��
			
			for(int i=1; i<=n; i++){
				a[i] = in.nextInt();//���� �Ƿڽð�
				int finish = in.nextInt();//���� �����ð�
				hour[i] = in.nextInt();//���� ���۽ð�
				for(int j=a[i]; j<a[i]+hour[i]; j++)
					d[i][j] = 1;
				
				smax = Math.max(finish, smax);
				f[i] = finish - hour[i];
			}

			for(int i=1; i<=k; i++){
				int start = in.nextInt();
				int end = in.nextInt();
				for(int j=start; j<=end; j++)
					work[i][j]=true;
				pmax = Math.max(pmax, end);
			}
			go(0,hour);
			System.out.println("Case #"+t);
			System.out.println(ans);
		}
		
		in.close();
	}
}

package week_12th_mon;

import java.util.*;
public class co�达�� {

	/*�达�� �ູ�� ����
	 * ������ ������ ���� 
	 * 
	 * */
	static int n, m;
	static boolean[] cnt = new boolean[201];
	static int[][] a;
	static int[] d;
	static boolean[] c;
	public static int rev(int n){
		if(n==1) return 2;
		else return 1;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			
			System.out.println("Case #"+t);
		}*/
		n = in.nextInt();
		m = in.nextInt();
		int[][] a = new int[n+1][n+1];
		boolean[] c = new boolean[n+1];
		int[] d = new int[n+1];
		
		for(int i=0; i<m; i++){
			int x = in.nextInt();
			int y = in.nextInt();
			a[x][y]=1;
			a[y][x]=1;
			if(!cnt[x]) cnt[x]=true;	//Ȯ���ؾ��� ģ���鸸 ǥ���صд�
			if(!cnt[y]) cnt[y]=true;
		}

		int count = 0;
		int num = 1;
		
		finish:
		for(int i=1; i<=n; i++){
			if(!cnt[i]) continue;	//Ȯ���� �ʿ���� ������ pass
			if(c[i]) continue;	//������ ���� �ٽ� Ȯ������ �ʴ´�.
			if(count==0){	//ó��Ȯ���� ģ������ �Ǻ�
				d[i]=num;	//��ó�� 1�� �����Ѵ�
				num=2;		//���� ����� 2�� �Ѵ�	
				++count;	//�ٽ� �������� ���´�
				c[i]=true;	//���� �Ϸ��� ���� true
			}
			
			for(int j=1; j<=n; j++){
				if(i==j) continue;
				//i�� j�� ���������� ��쿡��
				if(a[i][j]==1){
					//���� ���谡 ���� ���� ģ�����
					if(!c[j]){
						d[j] = rev(d[i]);	//i�� �ٸ� �ַ� �����Ѵ�
						c[j] = true;		//����Ϸ��Ѵ�
					}
					//���谡 �� ģ�����
					else if(c[j]){
						//����� ģ���� i��°�� ���� �ֶ��
						if(d[i]==d[j]){
							break finish;//����
						}
						d[i]=rev(d[j]);
						c[i]=true;
					}
				}
			}
		}
		
		boolean fin = true;
		for(int i=1; i<=n; i++){
			if(!cnt[i]) continue;	//Ȯ�������ʾƵ� �� �ֵ��� �н�
			if(!c[i]){
				System.out.println("�Ұ����ϴ�");
				fin = false;
				break;
			}
		}
		if(fin) System.out.println("�����ϴ�");
		
		in.close();
	}

}

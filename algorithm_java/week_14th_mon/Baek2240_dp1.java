package week_14th_mon;

import java.util.*;
public class Baek2240_dp1 {
	
	/*dfs�δ� �ȵǳ�����
	 * �׷� dp�� �����غ���
	 * d[t][w] t�ʿ� w�� �̵����� �� ���� �ڵ��� ����
	 * */
	static int t,w,ans=0;
	static int[] a = new int[1001];
	static int[][] d = new int[1001][31];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		t = in.nextInt();
		w = in.nextInt();
		
		//d[t][w] t�� �� w�� �̵����� �� ���� �ڵ��� ����
		for(int i=0; i<t; i++)
			a[i] = in.nextInt();
		
		//0�ʿ� �ڵΰ� 1�� ������ ������ �ƴϸ� ��������
		if(a[0]==1) d[1][0] = 1;
		else d[1][1]=1;
		
		for(int i=1; i<t; i++){
			for(int j=0; j<=w; j++){
				//i�� �� j�� �̵����� ��
				if(j==0){
					//�ѹ��� �������� �ʾҴٸ�
					if(a[i]==1) d[i][0]=d[i-1][0]+1;	//1�������� +1
					else d[i][0]=d[i-1][0];				//2�� ������ �״��
					continue;
				}
				if(j%2==0){	//¦���� �̵����� ���� 1���� ��ġ
					if(a[i]==1)	//1�������� +1
						d[i][j] = Math.max(d[i-1][j-1], d[i-1][j])+1;
					else			//2�������� �״��
						d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]);
				}
				else{ 	//Ȧ���� �̵����� ���� 2���� ��ġ
					if(a[i]==2)	//2�������� +1
						d[i][j] = Math.max(d[i-1][j-1], d[i-1][j])+1;
					else			//1�������� �״��
						d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]);
				}
			}
		}
		for(int i=0; i<=w; i++)
			ans = Math.max(d[t-1][i], ans);
		System.out.println(ans);
		in.close();
	}
}

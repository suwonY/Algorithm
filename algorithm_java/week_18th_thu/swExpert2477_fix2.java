package week_18th_thu;

import java.util.*;
public class swExpert2477_fix2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int tc=1; tc<=t; tc++){
			int n = in.nextInt();
			int m = in.nextInt();
			int k = in.nextInt();
			int endA = in.nextInt();
			int endB = in.nextInt();
			int[] a = new int[n+1];
			int[] b = new int[m+1];
			int[] time = new int[k+1];
			int[] c1 = new int[n+1];//����â�� ��Ȳ
			int[] c2 = new int[m+1];//����â�� ��Ȳ
			for(int i=1; i<=n; i++)
				a[i] = in.nextInt();
			for(int i=1; i<=m; i++)
				b[i] = in.nextInt();
			for(int i=1; i<=k; i++)
				time[i] = in.nextInt();
			Arrays.sort(time);
			
			Person[] customer = new Person[k+1];
			for(int i=1; i<=k; i++)
				customer[i]=new Person();
			
			int now = time[0]; int cnt = 0;
			while(true){
				System.out.println("���� �ð� : " + now);
				//�մ� ���� - 0
				for(int i=1; i<=k; i++){
					if(time[i]>now) break;
					if(now==time[i] && customer[i].situation==0){
//							System.out.println(i+"��° �մ� ����");
						customer[i].setStart(i);
						customer[i].situation=1;
					}
				}
				
				//0���� 1������� 2������ 3������ ->������ 4������ 5��
				//����â�� ���� ���� ģ���� Ȯ�����ֱ�(���� ������ ��������� 3���� �ٲٰ� ������� ������)
				for(int i=1; i<=k; i++){
					if(now==time[0]) break;
					if(customer[i].situation==2){//�������� �ֵ��߿���
						if(c1[customer[i].anum]==0){//�ش� ����â������ ������ ��������
//							System.out.println(customer[i].start+"��° ��  ����â������ ����");
							customer[i].situation = 3;//�����������ְ� ��������·� ������
						}
					}
					//����â�� ���� �����ֵ�
					if(customer[i].situation==4){
						if(c2[customer[i].bnum]==0){
//							System.out.println(customer[i].start+"��° �� ����â������ ����");
							customer[i].situation=5;
							++cnt;
						}
					}
				}
				
				if(cnt==k) break;
				
				//0���� 1������� 2������ 3������ ->������ 4������ 5��
				//��������ڵ� �� �� ����â���� �����ֱ�
				
				for(int i=1; i<=k; i++){
					if(customer[i].situation==1){//����������� ������߿���
						for(int j=1; j<=n; j++){//�� ����â�� Ȯ��
							if(c1[j]==0){//�� ����â����������
//								System.out.println(i+"��° �մ�  " + j+"�� ����â���� �̵�");
								customer[i].situation=2;//������ ���·� �ٲٰ�
								customer[i].anum=j;//���� ����â����ȣ ����
								c1[j]=a[j];//�����ð� ����
								break;
							}
							
						}
					}
				}
				
				//0���� 1������� 2������ 3������ ->������ 4������ 5��
				//�������ڵ� �� �� ����â���� �����ֱ�
				for(int i=1; i<=k; i++){
					if(customer[i].situation==3){//���������� ������߿���
						for(int j=1; j<=m; j++){//�� ����â�� Ȯ��
							if(c2[j]==0){//�� ����â����������
//								System.out.println(i+"��° �մ�  " + j+"�� ����â���� �̵�");
								customer[i].situation=4;//������ ���·� �ٲٰ�
								customer[i].bnum=j;//���� ����â����ȣ ����
								c2[j]=b[j];//����ð� ����
								break;
							}
							
						}
					}
				}
				
				System.out.print("a : ");
				for(int i=1; i<=n; i++){
					System.out.print(c1[i]+" ");
				}
				System.out.print("\nb : ");
				for(int i=1; i<=m; i++)
					System.out.print(c2[i]+" ");
				System.out.println();
				
				++now;
				for(int i=1; i<=n; i++)
					if(c1[i]>0)--c1[i];
				for(int i=1; i<=m; i++)
					if(c2[i]>0)--c2[i];
				
			}
			
			int ans = -1;
			for(int i=1; i<=k; i++){
				Person temp = customer[i];
//				System.out.println(temp.start+"�� �� : " + temp.anum+"�� ���� ," + temp.bnum+"�� ����");
				if(temp.anum==endA && temp.bnum==endB) ans+=i;
			}
			System.out.print("#"+tc+" ");
			if(ans==-1) System.out.println(-1);
			else System.out.println(ans+1);
		}
		
		in.close();
	}
	public static class Person{
		int start, anum, bnum;
		int situation;
		public Person(){
			start = anum = bnum = -10;
			situation = 0;	//0���� 1������� 2������ 3������ ->������ 3������ 4������
		}
		public void setStart(int num){
			start = num;
		}
		public void setAnum(int num){
			anum = num;
		}
		public void setBnum(int num){
			bnum = num;
		}
	}
}
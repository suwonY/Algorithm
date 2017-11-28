package week_18th_thu;

import java.util.*;
public class swExpert2477_fix4 {
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

			Deque<Person> q = new ArrayDeque<>();
			Person[] customer = new Person[k+1];
			for(int i=1; i<=k; i++)
				customer[i]=new Person();
			
			int now = time[0]; int cnt = 0;
			while(true){
				System.out.println("\n���� �ð� : " + now);
				//�մ� ���� - 0
				for(int i=1; i<=k; i++){
					if(time[i]>now) break;
					if(now==time[i] && customer[i].situation==0){
						System.out.println(i+"��° �մ� ����");
						customer[i].start=i;
						customer[i].situation=1;
					}
				}
				
				//0���� 1������� 2������ 3������ ->������ 4������ 5��
				//����â�� ���� ���� ģ���� Ȯ�����ֱ�(���� ������ ��������� 3���� �ٲٰ� ������� ������)
				List<Person> wait = new ArrayList<>();
				for(int i=1; i<=k; i++){
					if(now==time[0]) break;
					if(customer[i].situation==2){//�������� �ֵ��߿���
						if(c1[customer[i].anum]==0){//�ش� ����â������ ������ ��������
							System.out.println(customer[i].start+"��° ��  ����â������ ����");
							customer[i].situation = 3;//�����������ְ� ��������·� ������
							wait.add(customer[i]);
						}
					}
					//����â�� ���� �����ֵ�
					if(customer[i].situation==4){
						if(c2[customer[i].bnum]==0){
							System.out.println(customer[i].start+"��° �� ����â������ ����");
							customer[i].situation=5;
							++cnt;
						}
					}
				}
				Collections.sort(wait);
				for(Person p : wait)
					q.add(p);
				
				if(cnt==k) break;
				//0���� 1������� 2������ 3������ ->������ 4������ 5��
				//��������ڵ� �� �� ����â���� �����ֱ�
				for(int i=1; i<=k; i++){
					if(customer[i].situation==1){//����������� ������߿���
						for(int j=1; j<=n; j++){//�� ����â�� Ȯ��
							if(c1[j]==0){//�� ����â����������
								System.out.println(i+"��° �մ�  " + j+"�� ����â���� �̵�");
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
				//�������ھֵ��� ����â���������
				while(!q.isEmpty()){
					boolean finish = true;
					for(int i=1; i<=m; i++){
						if(c2[i]==0){//�� ���� â���� �ִٸ�
//							System.out.println(p.start+"��° �մ� : " + i + "�� ����â���� �̵�");
							Person temp = q.removeFirst();
							temp.situation=4;//������ ���·� �ٲٱ�
							temp.bnum = i;//�� ����â����ȣ ����
							c2[i]=b[i];
							customer[temp.start] = temp;
							finish = false;
							break;
						}
					}
					if(finish) break;
				}
				++now;
				for(int i=1; i<=n; i++)
					if(c1[i]>0)--c1[i];
				for(int i=1; i<=m; i++)
					if(c2[i]>0)--c2[i];
				
			}
			int ans = -1;
			for(int i=1; i<=k; i++){
				Person temp = customer[i];
				System.out.println(temp.start+"�� �� : " + temp.anum+"�� ���� ," + temp.bnum+"�� ����");
				if(temp.anum==endA && temp.bnum==endB) ans+=i;
			}
			System.out.print("#"+tc+" ");
			if(ans==-1) System.out.println(-1);
			else System.out.println(ans+1);
		}
		
		in.close();
	}
	public static class Person implements Comparable<Person>{
		int start, anum, bnum;
		int situation;
		public Person(){
			start = anum = bnum = -10;
			situation = 0;	//0���� 1������� 2������ 3������ ->������ 3������ 4������
		}
		@Override
		public int compareTo(Person o) {
			return this.anum-o.anum;
		}
	}
}
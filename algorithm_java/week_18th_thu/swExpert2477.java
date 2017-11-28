package week_18th_thu;
import java.util.*;
public class swExpert2477 {
	/*
	 n���� ���� â���� m���� ���� â��
	 ����â������ ������ �����ϰ� ����â������ ������ ����޴´�
	 ����â�������� ó���ð� a
	 ����â�� ó���ð� b
	 
	 �� â���� ������ �����ϰ� �ƴϸ� ��ٸ���
	 �� â���� ������ �����ϰ� �ƴϸ� ��ٸ���
	 
	 ����â���� �켱������ �Ʒ��� ����
	 1. ���� ���� ��ٸ��� ���, ��ȣ�� ���� ������� �켱 ����
	 2. �� â���� ���� ���� ��� â����ȣ�� ���� ������ ����.
	 
	 1. ���� ��ٸ��� ���� �켱�Ѵ�.
	 2. ���ÿ� ���� â���� ���� â����ȣ ���� ������ ���� �޴´�
	 3. �� ���� ���� ���� ��� â����ȣ�� ���� ������ ����
	 
	 -> ���� �ð�, â�� ó���ð��� �־��� �� ������ �н��� ���� ���� 
	 â���� �̿��� �� ��ȣ�� ���� ���϶�
	 * */
	static int n,m,k,endA,endB,cnt,ans,max,min=987654321;
	static int[] a,b,time, rec, fix;
	static int[] situationA, situationB;
	static int[] c1,c2;
	static Person[] cus, finish;
	public static void go(){
		Deque<Person> wait1 = new ArrayDeque<>();
		Deque<Person> wait2 = new ArrayDeque<>();
		int total = 0;
		int index = 0;//���° ������� üũ
		int now = min-1;
		boolean fin = false;
		while(!fin){
			System.out.println("���� �ð� : " + now);
			System.out.println("�� ���� �ð� : " + time[index]);
			//������ �ֵ���� ���
			for(int i=0; i<k; i++){
				if(situationB[i]==0){//����ð����� �����ٸ�
					System.out.println(i+"�� �� �Ϸ�");
					finish[i] = cus[i];
					++total;
				}
			}
			
			if(total==k){
				System.out.println("����");
				break;
			}
			
			//���� ���� ���� �����
			if(time[index]==now){
				System.out.println(index+" �� �� ����");
				cus[index] = new Person();
				cus[index].setStart(index);//��Ϲ�ȣ�� �־��ش�
				wait1.addLast(cus[index]);//��������ڷ� ����ִ´�
				++index;
			}
			
			//����â���� ��ٸ��� ����� �ִٸ�
			while(!wait1.isEmpty()){
				boolean next = true;
				Person t;
				for(int i=0; i<n; i++){ //����â�� �˻�
					if(c1[i]==0){ 		//����â���� ����ִٸ�
						System.out.println(wait1.getFirst().start+"��° �մ� :" + i +" ��° â���� �̵�");
						t = wait1.removeFirst();
						situationA[t.start]=a[i];//��ٸ��� �ð��� ����
						t.setAnum(i);//����â����ȣ �Է�
						cus[t.start] = t; //�� ������ ����
						c1[i] = a[i];//�մ��� �����ϱ� â�� �ð��� ä����
						next = false;
						break;
					}
				}
				if(next) break;	//�� â���� ����
			}
			//���������� ���� ����� wait2�� ����־�� �ȴ�
			//������ �̹� �߰�, ���� �� �Ϸ�� �Ÿ� äũ�ؾߵȴ�
			for(int i=0; i<k; i++){
				if(cus[i]!=null && situationA[i]==0){	//������ �߾���, ���������� ��������
					wait2.addLast(cus[i]);
					situationA[i] = -1;
				}
			}
			while(!wait2.isEmpty()){
				boolean next = true;
				Person t;
				for(int i=0; i<m; i++){
					if(c2[i]==0){
						System.out.println(wait2.getFirst().start+"��° �մ�: "+i+" ��° â���� �̵�");
						t = wait2.removeFirst();
						situationB[t.start]=a[i];
						t.setBnum(i);
						cus[t.start] = t;
						c2[i] = a[i];
						next = false;
						break;
					}
				}
				if(next) break;
			}
			
			++now;//�ð�����
			for(int i=0; i<n; i++){
				--situationA[i];
				--c1[i];
			}
			for(int i=0; i<m; i++){
				--situationB[i];
				--c2[i];
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		int endA = in.nextInt()-1;
		int endB = in.nextInt()-1;
		
		int[] a = new int[n];
		int[] rec = new int[n];
		int[] c1 = new int[n];
		int[] b = new int[m];
		int[] fix = new int[m];
		int[] c2 = new int[m];
		Person[] cus = new Person[k+1];
		Person[] finish = new Person[k+1];
		int[] situationA = new int[21];
		int[] situationB = new int[21];
		int[] time = new int[k];
		Arrays.fill(situationA, -1);
		Arrays.fill(situationB, -1);
		ans = 1;
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		for(int i=0; i<m; i++)
			b[i] = in.nextInt();
		for(int i=0; i<k; i++) {
			time[i] = in.nextInt();
			min = Math.min(time[i], min);
		}
		Arrays.sort(time);
		
		Deque<Person> wait1 = new ArrayDeque<>();
		Deque<Person> wait2 = new ArrayDeque<>();
		int total = 0;
		int index = 0;//���° ������� üũ
		int now = min;
		boolean fin = false;
		while(!fin){
			System.out.println("���� �ð� : " + now);
			//������ �ֵ���� ���
			for(int i=0; i<k; i++){
				if(situationB[i]==0){//����ð����� �����ٸ�
					++total;
					System.out.println(total+"���� �� �Ϸ�");
					finish[i] = cus[i];
				}
			}
			
			if(total==k){
				System.out.println("����");
				break;
			}
			
			//���� ���� ���� �����
			if(index<k && time[index]==now){
				System.out.println(index+" �� �� ����");
				cus[index] = new Person();
				cus[index].setStart(index);//��Ϲ�ȣ�� �־��ش�
				wait1.addLast(cus[index]);//��������ڷ� ����ִ´�
				++index;
			}
			
			//����â���� ��ٸ��� ����� �ִٸ�
			while(!wait1.isEmpty()){
				boolean next = true;
				Person t;
				for(int i=0; i<n; i++){ //����â�� �˻�
					if(c1[i]==0){ 		//����â���� ����ִٸ�
						System.out.println(wait1.getFirst().start+"��° �մ� :" + i +" ��° ����â���� �̵�");
						t = wait1.removeFirst();
						situationA[i]=a[i];//��ٸ��� �ð��� ����
						t.setAnum(i);//����â����ȣ �Է�
						cus[t.start] = t; //�� ������ ����
						c1[i] = a[i];//�մ��� �����ϱ� â�� �ð��� ä����
						next = false;
						break;
					}
				}
				if(next) break;	//�� â���� ����
			}
			//���������� ���� ����� wait2�� ����־�� �ȴ�
			//������ �̹� �߰�, ���� �� �Ϸ�� �Ÿ� äũ�ؾߵȴ�
			for(int i=0; i<k; i++){
				if(cus[i]!=null && situationA[i]==0){	//������ �߾���, ���������� ��������
					wait2.addLast(cus[i]);
					situationA[i] = -1;
				}
			}
			
			while(!wait2.isEmpty()){
				boolean next = true;
				Person t;
				for(int i=0; i<m; i++){
					if(c2[i]==0){
						System.out.println(wait2.getFirst().start+"��° �մ�: "+i+" ��° ����â���� �̵�");
						t = wait2.removeFirst();
						situationB[i]=b[i];
						t.setBnum(i);
						cus[t.start] = t;
						c2[i] = b[i];
						next = false;
						break;
					}
				}
				if(next) break;
			}
			
			++now;//�ð�����
			for(int i=0; i<n; i++){
				if(situationA[i]>0) --situationA[i];
				if(c1[i]>0) --c1[i];
			}
			for(int i=0; i<m; i++){
				if(situationB[i]>0) --situationB[i];
				if(c2[i]>0) --c2[i];
			}
		}
		for(int i=0; i<k; i++){
			Person tt = cus[i];
			System.out.println(tt.start+"��° �� : " +" ������ȣ : " + tt.anum + " �����ȣ : " + tt.bnum);
		}
		in.close();
	}
	public static class Person{
		int start, anum, bnum;
		public Person(){
			start = anum = bnum = -10;
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
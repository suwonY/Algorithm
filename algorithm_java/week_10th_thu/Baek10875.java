package week_10th_thu;

import java.util.*;
public class Baek10875 {

	/*��
	 * 
	 * ���� �ٸ� ��
	 * ���� �ε����ų� �����̿� �ε����� �״°� �Ȱ���
	 * �ٵ� 2�����迭�� �������
	 * �����ϰ� ���Ѵ�
	 * �ڽ��� ������ ������ ����־��*/
	static int n, time=0, k;
	static int change = -1;
	static char to = 'N';
	static int hx=0, hy=0, dir=4; //�� ó������ ��
	static Queue<Time> c = new LinkedList<>();
	static List<Snake> snake = new ArrayList<>();
	public static boolean die(){
		for(Snake temp : snake){
			int x = temp.x;
			int y = temp.y;
			if(hx==x && hy==y) return true;
		}
		return false;
	}
	public static void change(){
		if(change==-1 && to=='N'){
			Time temp = c.remove();
			change = temp.x;
			to = temp.c;
		}
		if(change!=time) return;
		
		
		switch(dir){
		case 1: //��
			if(to=='L') dir=3;
			else if(to=='R') dir=4;
			break;
		case 2: //��
			if(to=='L') dir=4;
			else if(to=='R') dir=3;
			break;
		case 3: //��
			if(to=='L') dir=2;
			else if(to=='R') dir=1;
			break;
		case 4: //��
			if(to=='L') dir=1;
			else if(to=='R') dir=2;
			break;
		}
		System.out.println("���� �ð�: " + time + " �ٲ� ���� : " +to);
		change = -1;
		to = 'N';
	}
	/*public static void change(){
		if(c[time]!='L' && c[time]!='R'){
			return;
		}
		switch(dir){
		case 1: //��
			if(c[time]=='L') dir=3;
			else if(c[time]=='R') dir=4;
			break;
		case 2: //��
			if(c[time]=='L') dir=4;
			else if(c[time]=='R') dir=3;
			break;
		case 3: //��
			if(c[time]=='L') dir=2;
			else if(c[time]=='R') dir=1;
			break;
		case 4: //��
			if(c[time]=='L') dir=1;
			else if(c[time]=='R') dir=2;
			break;
		}
	}*/
	public static void go(){
		boolean finish = false;
		while(true){
			switch(dir){
			case 1:	//��
				++hy;	//y�� ����
				finish = die();	//�׾����� �Ǻ�
				if(finish) finish = true;
				break;
			case 2:	//��
				--hy;	//y�� ����
				finish = die();	//�׾����� �Ǻ�
				if(finish) finish = true;
				break;
			case 3:	//��
				--hx;	//y�� ����
				finish = die();	//�׾����� �Ǻ�
				if(finish) finish = true;
				break;
			case 4:	//��
				++hx;	//y�� ����
				finish = die();
				if(finish) finish = true;
				break;
			}
			++time;	//�ð�����
			if(finish) break; //�׾����� ��
			snake.add(new Snake(hx,hy)); //���׾����� �Ӹ� �÷��ֱ�
//			System.out.println(hx+","+hy+"�� �߰���");
			change(); //����ٲٱ�
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		snake.add(new Snake(0,0));
		n = in.nextInt();
		
		//n���� 1ū �ֵ��� ���� �Ϻζ�� ���� 
		for(int i=-n+1; i<=n+1; i++){
			snake.add(new Snake(i,n+1));
			snake.add(new Snake(i,-(n+1)));
			snake.add(new Snake(n+1,i));
			snake.add(new Snake(-(n+1),i));
		}
		k = in.nextInt();
		
		int x = in.nextInt();
		String temp = in.next();
		change = x;
		to = temp.charAt(0);
		for(int i=0; i<k-1; i++){
			int t = in.nextInt();
			x+=t;
			temp = in.next();
			c.add(new Time(x,temp.charAt(0)));
		}
		
		go();
		System.out.println(time);
		in.close();
	}
	public static class Snake{
		int x, y;
		public Snake(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static class Time{
		int x;
		char c;
		public Time(int x, char c){
			this.x = x;
			this.c = c;
		}
	}
}

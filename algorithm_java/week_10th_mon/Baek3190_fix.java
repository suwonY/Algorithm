package week_10th_mon;

import java.util.*;
public class Baek3190_fix {

	static int n, k, l, time=0, dir=4, hx=0, hy=0;
	static Deque<Point> tails = new ArrayDeque<>();
	static int[][] a = new int[101][101];
	static char[] t = new char[10001];
	//dir�� ���� �������� U D R L�� ������
	public static void change(char where){
		if(t[time]!='L' && t[time]!='D') return;
		
		//��1	��2	   ��3	        ��4 ��� ������ �ϸ� 
		//���϶� �·� ���� �� ��ε��� ��
		if(dir==1){
			if(where=='L') dir=3;
			else dir=4;
		}
		//���϶� �·� ���� �� ��ε��� ��
		else if(dir==2){
			if(where=='L') dir=4;
			else dir=3;
		}
		//���϶� �·ε��� �� ��ε��� ��
		else if(dir==3){
			if(where=='L') dir=2;
			else dir=1;
		}
		else{
			if(where=='L') dir=1;
			else dir=2;
		}
	}
	public static boolean moveUp(){
		if(hx-1<0) return true;//���� ������ ������ ���� ��
		if(a[hx-1][hy]==1) return true; //�� �����̿� �ε����� ���� ��

		--hx;
		//������ִٸ� ����
		if(a[hx][hy]==2){
			a[hx][hy]=1;
			tails.addFirst(new Point(hx,hy));//�Ӹ��� �߰����ش�
		}
		//��������ٸ� ������ �ٿ��ش�
		else if(a[hx][hy]==0){
			a[hx][hy] = 1;
			//�Ӹ��� �߰��ϰ� ������ ����
			tails.addFirst(new Point(hx,hy));
			Point last = tails.removeLast();
			a[last.x][last.y] = 0;
		}
		++time;
		change(t[time]);
		
		return false;
	}
	public static boolean moveDown(){
		if(hx+1>n) return true;//���� ������ ������ ���� ��
		if(a[hx+1][hy]==1) return true; //�� �����̿� �ε����� ���� ��

		++hx;
		//������ִٸ� ����
		if(a[hx][hy]==2){
			a[hx][hy]=1;
			tails.addFirst(new Point(hx,hy));//�Ӹ��� �߰����ش�
		}
		//��������ٸ� ������ �ٿ��ش�
		else if(a[hx][hy]==0){
			a[hx][hy] = 1;
			//�Ӹ��� �߰��ϰ� ������ ����
			tails.addFirst(new Point(hx,hy));
			Point last = tails.removeLast();
			a[last.x][last.y] = 0;
		}
		++time;
		change(t[time]);
		
		return false;
	}
	public static boolean moveLeft(){
		if(hy-1<0) return true;//���� ������ ������ ���� ��
		if(a[hx][hy-1]==1) return true; //�� �����̿� �ε����� ���� ��

		--hy;
		//������ִٸ� ����
		if(a[hx][hy]==2){
			a[hx][hy]=1;
			tails.addFirst(new Point(hx,hy));//�Ӹ��� �߰����ش�
		}
		//��������ٸ� ������ �ٿ��ش�
		else if(a[hx][hy]==0){
			a[hx][hy] = 1;
			//�Ӹ��� �߰��ϰ� ������ ����
			tails.addFirst(new Point(hx,hy));
			Point last = tails.removeLast();
			a[last.x][last.y] = 0;
		}
		++time;
		change(t[time]);
		
		return false;
	}
	public static boolean moveRight(){
		if(hy+1>n) return true;//���� ������ ������ ���� ��
		if(a[hx][hy+1]==1) return true; //�� �����̿� �ε����� ���� ��

		++hy;
		//������ִٸ� ����
		if(a[hx][hy]==2){
			a[hx][hy]=1;
			tails.addFirst(new Point(hx,hy));//�Ӹ��� �߰����ش�
		}
		//��������ٸ� ������ �ٿ��ش�
		else if(a[hx][hy]==0){
			a[hx][hy] = 1;
			//�Ӹ��� �߰��ϰ� ������ ����
			tails.addFirst(new Point(hx,hy));
			Point last = tails.removeLast();
			a[last.x][last.y] = 0;
		}
		++time;
		change(t[time]);
		
		return false;
	}
	public static void go(){
		boolean finish = false;
		//��1 ��2 ��3 ��4
		while(!finish){
			switch(dir){
			case 1:
				finish = moveUp();
				break;
			case 2:
				finish = moveDown();
				break;
			case 3:
				finish = moveLeft();
				break;
			case 4:
				finish = moveRight();
				break;
			}
		}
		System.out.println(time);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		for(int i=0; i<k; i++){
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			a[x][y] = 2;	//���
		}
		l = in.nextInt();
		int x = 0;
		for(int i=0; i<l; i++){
			x += in.nextInt();
			String temp = in.next();
			t[x]=temp.charAt(0);
		}
		
		a[0][0] = 1;
		tails.addFirst(new Point(0,0));
		
		go();
		
		in.close();
	}
	public static class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}

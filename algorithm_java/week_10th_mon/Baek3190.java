package week_10th_mon;

import java.util.*;
public class Baek3190 {

	/*��
	 * n*n
	 * ����� ������
	 * ���� ���̴� 1 ���� ������ ����
	 * ����ĭ �̵��ÿ� ���� �þ�� �Ӹ��� ����ĭ���� �̵�
	 * ����� ������ ���̰� �þ�� ����� ������ ���̴� �״��
	 * ����� ��ġ�� ���� �̵���ΰ� �־��� �� �� ������ �� �� �Ŀ� �������� ����ϱ�
	 * ���̳� �ڱ��ڽ��� ���� �ε����� ������ ������.*/
	static int n, k, l, time=0, dir=4, hx, hy;
	static Point head;
	static Snake snake;
	static int[][] a = new int[101][101];
	static char[] t = new char[101];
	static boolean[][] c = new boolean[101][101];
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

		//������ִٸ�
		if(c[hx-1][hy]){
			//���� ��ǥ�� ������ ���δ�
			snake.push(new Point(hx,hy,dir));
			//�Ӹ��� �ٲ��ش�
			--hx;
			a[hx][hy]=1;
			change(t[time]);
			//1. ���࿡ �� �ð��� ������ �ٲ�ٸ�
			snake.changeHead(hx, hy, dir);
		}
		//����� ���ٸ�
		else if(!c[hx-1][hy]){
			a[hx][hy]=0;
			--hx;
			a[hx][hy]=1;
			change(t[time]);
			snake.changeHead(hx, hy, dir);
		}
		
		return false;
	}
	public static boolean moveDown(){
		if(hx+1>=n) return true;//���� ������ ������ ���� ��
		if(a[hx+1][hy]==1) return true; //�� �����̿� �ε����� ���� ��

		//������ִٸ�
		if(c[hx+1][hy]){
			//���� ��ǥ�� ������ ���δ�
			snake.push(new Point(hx,hy,dir));
			//�Ӹ��� �ٲ��ش�
			++hx;
			a[hx][hy]=1;
			change(t[time]);
			//1. ���࿡ �� �ð��� ������ �ٲ�ٸ�
			snake.changeHead(hx, hy, dir);
		}
		//����� ���ٸ�
		else if(!c[hx+1][hy]){
			a[hx][hy]=0;
			++hx;
			a[hx][hy]=1;
			change(t[time]);
			snake.changeHead(hx, hy, dir);
		}
		
		return false;
	}
	public static boolean moveLeft(){
		if(hy-1<0) return true;//���� ������ ������ ���� ��
		if(a[hx][hy-1]==1) return true; //�� �����̿� �ε����� ���� ��

		//������ִٸ�
		if(c[hx][hy-1]){
			//���� ��ǥ�� ������ ���δ�
			snake.push(new Point(hx,hy,dir));
			
			//�Ӹ��� �ٲ��ش�
			--hy;
			a[hx][hy]=1;
			change(t[time]);
			//1. ���࿡ �� �ð��� ������ �ٲ�ٸ�
			snake.changeHead(hx, hy, dir);
		}
		//����� ���ٸ�
		else if(!c[hx][hy-1]){
			a[hx][hy]=0;
			--hy;
			a[hx][hy]=1;
			change(t[time]);
			snake.changeHead(hx, hy, dir);
		}
		
		return false;
	}
	public static boolean moveRight(){
		if(hy+1>=n) return true;//���� ������ ������ ���� ��
		if(a[hx][hy+1]==1) return true; //�� �����̿� �ε����� ���� ��

		//������ִٸ�
		if(c[hx][hy+1]){
			//���� ��ǥ�� ������ ���δ�
			snake.push(new Point(hx,hy,dir));
			
			//�Ӹ��� �ٲ��ش�
			++hy;
			a[hx][hy]=1;
			change(t[time]);
			//1. ���࿡ �� �ð��� ������ �ٲ�ٸ�
			snake.changeHead(hx, hy, dir);
		}
		//����� ���ٸ�
		else if(!c[hx][hy+1]){
			a[hx][hy]=0;
			++hy;
			a[hx][hy]=1;
			change(t[time]);
			snake.changeHead(hx, hy, dir);
		}
		
		return false;
	}
	public static void print(){
		System.out.println("time: " +time);
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void go(){
		boolean finish = false;
		//��1 ��2 ��3 ��4
		while(!finish){
			switch(dir){
			case 1:
				finish = moveUp();
				System.out.println("up");
				print();
				++time;
				break;
			case 2:
				finish = moveDown();
				System.out.println("down");
				print();
				++time;
				break;
			case 3:
				finish = moveLeft();
				System.out.println("left");
				print();
				++time;
				break;
			case 4:
				finish = moveRight();
				System.out.println("right");
				print();
				++time;
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
			c[x][y] = true;	//���
		}
		l = in.nextInt();
		int x = 0;
		for(int i=0; i<l; i++){
			x = in.nextInt();
			String temp = in.next();
			t[x]=temp.charAt(0);
		}
		
		a[0][0] = 1;
		snake = new Snake();
		head = snake.head;
		hx = head.x;
		hy = head.y;
		
		go();
		in.close();
	}
	public static class Snake{
		int dir, length, index;
		Point head = new Point(0,0,4);
		List<Point> p = new LinkedList<>();;
		public Snake(){
			p.add(head);
			dir = 4;
			length = 1;
			index = 1;
		}
		public void push(Point num){
			int x = num.x;
			int y = num.y;
			Point front = p.get(index-1); //�ڽ��� �տ����� ������ �޾� �����ص�
			int fx = front.x;
			int fy = front.y;
			int fdir = front.dir;
			p.add(new Point(x,y,dir,fx,fy,fdir));
			++index;
		}
		public void changeHead(int x, int y, int dir){
			head = new Point(x,y,dir);
		}
		public void chageTails(){
			for(int i=index-1; i>0; i--){
				
			}
		}
	}
	public static class Point{
		int x, y, dir;
		Point front = null;
		public Point(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		public Point(int x, int y, int dir, int fx, int fy, int fdir){
			this.x = x;
			this.y = y;
			front = new Point(fx,fy,fdir);
			this.dir = dir;
		}
	}
}

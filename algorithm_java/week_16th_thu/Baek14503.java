package week_16th_thu;
import java.util.*;
public class Baek14503 {
	/*
	1. ���ʹ����� û�Ұ� �ȵ����� ���ʹ����� û���Ѵ�.(�׻� ���ʹ������ û�����)
	2. ���� ������ û�Ұ� ������ �������� ������ �ٲٰ� �ٽ� ������ Ȯ���Ѵ�
	3. �׹����� ��� û�Ұ� ���ְų� ���ΰ��, �����Ͽ� 2���� �Ѵ�
	4. �׹����� ��� û�Ұ� ���ְų� ���ΰ��, ������ ���̸� �۵��� �����.
	0�� ��ĭ 1�� ���̴�
	0 �� 
	1 ��
	2 ��
	3 ��
	 * */
	static int n,m;
	static int[][] a = new int[51][51];
	static boolean[][] c = new boolean[51][51];
	static int ans = 0;
	static int sx,sy,dir;
	public static void left(){
		if(dir==3) dir=0;
		else dir++;
	}
	public static boolean first(int x, int y){
		if(!c[x][y]){
			++ans;
			c[x][y]=true;
		}
		//���ʹ����� ���̰ų�, �� ���̰ų� û�Ұ� �� ��� -1�� ��ȯ
		if(dir==0){//��
//			System.out.println("������ �������ιٲ�");
			if(y-1<0 || a[x][y-1]==1 || c[x][y-1]) return false;
			sy=y-1;
		}
		else if(dir==1){//��
//			System.out.println("���ʿ��� �Ʒ��ιٲ�");
			if(x+1>=n || a[x+1][y]==1 || c[x+1][y]) return false;
			sx=x+1;
		}
		else if(dir==2){//��
//			System.out.println("�Ʒ����� ��ιٲ�");
			if(y+1>=m || a[x][y+1]==1 || c[x][y+1]) return false;
			sy=y+1;
		}
		else{//��
//			System.out.println("�쿡�� ���ιٲ�");
			if(x-1<0 || a[x-1][y]==1 || c[x-1][y]) return false;
			sx=x-1;
		}

		c[sx][sy]=true;
		++ans;
		return true;
	}
	public static boolean checkBack(int x, int y){
		if(dir==0){
			if(x+1>=n || a[x+1][y]==1) return false;
			sx=x+1;
		}
		else if(dir==1){
			if(y+1>=m || a[x][y+1]==1) return false;
			sy=y+1;
		}
		else if(dir==2){
			if(x-1<0 || a[x-1][y]==1) return false;
			sx=x-1;
		}
		else{
			if(y-1<0 || a[x][y-1]==1) return false;
			sy=y-1;
		}
		if(!c[sx][sy]){
			++ans;
			c[sx][sy]=true;
		}

//		System.out.println("���ӳ�");
		return true;
	}
	public static void go(){
		while(true){
			int cnt = 0;
			while(cnt!=4){
				boolean temp = first(sx,sy);
				left();
				if(!temp) ++cnt;
				else cnt=0;
			}
			if(!checkBack(sx,sy))break;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		sx = in.nextInt();
		sy = in.nextInt();
		dir = in.nextInt();
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				a[i][j] = in.nextInt();
		
		go();
		System.out.println(ans);
		in.close();
	}
}
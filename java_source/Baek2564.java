package week_10th_mon;

import java.util.*;
public class Baek2564 {

	//1�� 2�� 3�� 4��
	static int n,m,k,sx,sy,ans=0,sd=0;
	static int[] dx = {-1,0}, dy = {0,-1};
	static Queue<Point> q = new LinkedList<>();
	public static int rev(int num){
		if(num==1)return 2;
		else if(num==2)return 1;
		else if(num==3)return 4;
		else return 3;
	}
	public static void go(){
		while(!q.isEmpty()){
			Point store = q.remove();
			int x = store.x;
			int y = store.y;
			int dir = store.dir;
			int dis = 0;
			//�ݴ����ִ� ģ����
			if(dir==rev(sd)){
				int first, second;
				if(dir==1||dir==2){
					first = Math.abs(0-y)+Math.abs(0-sy);
					second = Math.abs((m-1)-sy)+Math.abs((m-1)-y);
					dis = Math.min(first, second)+n-1;
				}
				else{
					first = Math.abs(0-x)+Math.abs(0-sx);
					second = Math.abs((m-1)-sx)+Math.abs((m-1)-x);
					dis = Math.min(first, second)+m-1;
				}
				ans+=dis;
			}
			//�پ��ִ� ģ����
			else{
				dis = Math.abs(x-sx)+Math.abs(y-sy);
				ans+=dis;
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		m = in.nextInt()+1;
		n = in.nextInt()+1;
		k = in.nextInt();
		//1�� ��� x��ǥ�� 0
		//2�� ��� x��ǥ�� n-1
		//3�ϰ�� y��ǥ�� 0
		//4�� ��� y��ǥ�� m-1
		for(int i=0; i<k; i++){
			int w = in.nextInt();
			int l = in.nextInt();
			if(w==1) q.add(new Point(0,l,1));
			else if(w==2) q.add(new Point(n-1,l,2));
			else if(w==3) q.add(new Point(l,0,3));
			else q.add(new Point(l,m-1,4));
		}
		
		int w = in.nextInt();
		int l = in.nextInt();
		if(w==1) {sx=0;sy=l;sd=1;}
		else if(w==2) {sx=n-1;sy=l;sd=2;}
		else if(w==3) {sy=0;sx=l;sd=3;}
		else if(w==4) {sy=m-1;sx=l;sd=4;}
		
		go();
		System.out.println(ans);
		in.close();
	}
	static class Point{
		int x, y, dir;
		public Point(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
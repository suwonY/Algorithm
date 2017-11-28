package week_10th_thu;

import java.util.*;
public class Baek10875_fix {

	/*��
		�ð� �ʰ��� ��
		������ �� ���ϴϱ� �״� �ð� ���ϱⰡ �ʹ� �����ɸ��µ� �ϴ�
	*/
	static int n, k;
	static long change = -1, time = 0, hx=0, hy=0;
	static char to = 'N';
	static int dir=4; //�� ó������ ��
	static List<Width> w = new ArrayList<>();
	static List<Height> h = new ArrayList<>();
	public static boolean die(long hx, long hy){
		if(hx<-n || hx>n || hy<-n || hy>n) {
//			System.out.println("�� ������ ����");
			return true;
		}
		for(Height height : h){
			long x = height.x;
			long sy = Math.min(height.sy, height.ey);
			long ey = Math.max(height.sy, height.ey);
			if(hx==x){
				if(sy<=hy && hy<=ey){
//					System.out.println("x��ǥ "+x+"�� ���Ͽ�"+" y�� ���� "+sy +"~"+ey+"����");
//					System.out.println("���μ��� �浹����");
					return true;
				}
			}
		}
		
		for(Width width: w){
			long y = width.y;
			long sx = Math.min(width.sx, width.ex);
			long ex = Math.max(width.sx, width.ex);
			if(hy==y){
				if(sx<=hx && hx<=ex){
//					System.out.println("y��ǥ "+y+"�� ���Ͽ�"+" x�� ���� "+sx +"~"+ex+"����");
//					System.out.println("���μ��� �浹����");
					return true;
				}
			}
		}
		
		return false;
	}
	public static boolean go(long change, char to){
		boolean finish = false;
		//�ٲ�� �ð��� change - change�Ǳ� ������ �����̵��ϱ�
		long x = hx;
		long y = hy;
		for(long i=0; i<change; i++){
			if(dir==1){		//��
//				System.out.println("���� ������");
				++y;
				finish=die(x,y);
			}
			else if(dir==2){//��
//				System.out.println("�Ʒ��� ������");
				--y;
				finish=die(x,y);
			}
			else if(dir==3){//��
//				System.out.println("�·� ������");
				--x;
				finish=die(x,y);
			}
			else{			//��
//				System.out.println("��� ������");
				++x;
				finish=die(x,y);
			}
			++time;
			if(finish) return true;
		}
//		System.out.println("���� x,y��ǥ�� : " + hx+","+hy);
//		System.out.println("������ x,y��ǥ�� : " + x +","+y);
		if(dir==3 || dir==4){
			w.add(new Width(hx,x,y));
//			System.out.println("���� y: "+y+"���� x��ǥ�� :"+hx+" ~ "+x+"��ŭ �̵�");
			hx = x;
		}
		else if(dir==1 || dir==2){
			h.add(new Height(hy,y,x));
//			System.out.println("���� x: "+x+"���� x��ǥ�� :"+hy+" ~ "+y+"��ŭ �̵�");
			hy = y;
		}
//		System.out.print("������ : "+dir+" ����" );
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
//		System.out.println(dir+"�� �ٲ�");
//		System.out.println("���� �Ӹ� ��ǥ : " + hx+","+hy);
		return false;
	}
	public static void go(){
		while(true){
			if(dir==1)//��
				++hy;
			else if(dir==2)//��
				--hy;
			else if(dir==3)//��
				--hx;
			else if(dir==4)//��
				++hx;
			++time;
			if(die(hx,hy)) break;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		
		long x = 0;
		boolean finish = false;
		for(int i=0; i<k; i++){
			long t = in.nextLong();
			String temp = in.next();
			finish = go(t,temp.charAt(0));
			if(finish) break;
		}
		if(finish)
			System.out.println(time);
		
		else{
			go();
			System.out.println(time);
		}
		in.close();
	}
	public static class Width{
		//����
		long sx,ex,y;
		public Width(long sx, long ex, long y){
			this.sx = sx;
			this.ex = ex;
			this.y = y;
		}
	}
	public static class Height{
		//����
		long sy,ey,x;
		public Height(long sy, long ey, long x){
			this.sy = sy;
			this.ey = ey;
			this.x =x;
		}
	}
}

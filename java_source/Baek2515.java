package week_12th_mon;

import java.util.*;
public class Baek2515_2nd_fix {
	/*전시장
	 * 
	 * 그림들은 높이가 다르다
	 * 세로길이가 S이상인 그림만 관람객이 관심을 보이고 사게 된다
	 * 보이는 세로부분이 S이상인 그림만 판매가 가능하다
	 * 그림 높이와 가격이 주어질 때 가격의 합이 최대가 되게 만들기
	 * 
	 * dp같으니깐 점화식을 생각해보자
	 d[n]가 n개의 그림으로 남길 수 있는 최대의 이익
	 d[n] = max(d[n-1] , d[k] + c[n])
	 (k는 내가 보이게 내 앞에 서있을 수잇는 친구)
	 * */
	static int n,s;//그림의 개수 , 범위
	static int[] d;
	static List<Pic> a = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		s = in.nextInt();
		d = new int[n+1];
		a.add(new Pic(0,0));
		for(int i=0; i<n; i++){
			int h = in.nextInt();
			int c = in.nextInt();
			a.add(new Pic(h,c));
		}
		
		Comparator<Pic> com = new Comparator<Pic>() {
			@Override
			public int compare(Pic o1, Pic o2) {
				if(o1.h>o2.h) return 1;
				else if(o2.h>o1.h)return -1;
				return 0;
			}
		};
		
		Collections.sort(a,com);
		
		for(int i=1,j=0; i<=n; i++){
			//내 앞에꺼중에 내가 보일 수 있는 애를 찾는다
			while(a.get(j+1).h<=a.get(i).h-s) ++j;
//			System.out.println("i가 " + i + " 일 때  // " + "앞에 올 수 잇는 친구 : " + j);
			d[i] = Math.max(d[i-1], d[j]+a.get(i).c);
		}
		
		System.out.println(d[n]);
		in.close();
	}
	public static class Pic{
		int h, c;
		public Pic(int h, int c){
			this.h = h;
			this.c = c;
		}
	}
}
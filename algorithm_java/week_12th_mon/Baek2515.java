package week_12th_mon;

import java.util.*;
public class Baek2515 {

	/*전시장
	 * 
	 * 그림들은 높이가 다르다
	 * 세로길이가 S이상인 그림만 관람객이 관심을 보이고 사게 된다
	 * 보이는 세로부분이 S이상인 그림만 판매가 가능하다
	 * 그림 높이와 가격이 주어질 때 가격의 합이 최대가 되게 만들기
	 * 
	 * dp같으니깐 점화식을 생각해보자
	 d[i]가 i번째 그림을 포함했을 때 생긱는 최대값이라고 하면
	 d[1]은 
	 
	 
	 
	 * */
	static int n,s,max=0;//그림의 개수 , 범위
	static int[] d = new int[300001];
	static Map<Integer,Integer> m = new TreeMap<>();
	static List<Pic> a = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		s = in.nextInt();
		for(int i=0; i<n; i++){
			int h = in.nextInt();
			int c = in.nextInt();
			m.put(h, c);
		}
		
		//Treemap을 이용해서 정렬후 list에 저장
		for(int h : m.keySet())
			a.add(new Pic(h,m.get(h)));
		
		max = a.get(n-1).h;	//최대 높이
		
		int index = 0;
		for(int i=s; i<=max; i++){
			d[i] = d[i-1];
			if(a.get(index).h==i){
				d[i] = Math.max(d[i], d[i-s]+a.get(index).c);
				++index;
			}
			if(index<n && a.get(index).h==i){
				while(a.get(index).h==i) ++index;
			}
		}
		System.out.println(d[a.get(n-1).h]);
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
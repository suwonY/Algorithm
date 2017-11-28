package week_12th_thu;

import java.util.*;
public class co_shoe {

	/*구두제작
	 * 
	 * 주문받은 구두의 수 N
	 * 주문 의뢰시간 a
	 * 마감시간 f
	 * 제작시간 p
	 * 
	 * 구두는 한명이나 여러명이 제작한다
	 * 자기 근무시간에 구두를 만든다
	 * 장인마다 근무시작시간 s
	 *       근무마감시간 e
	 * 한 장인이 둘 이상의 구두 제작에 참여할 수 없다
	 * 장인은 한개 구두만 만들
	 * */
	static int n,k,ans;
	static int smax,pmax;	//구두 마감시간 최대, 장인 마감시간 최대
	static int[][] d; //구두번호당 제작기간을 나타내는 배열
	static int[] hour;
	static int[] cnt;
	static boolean[][] work; //장인이 근무하는 시간을 저장
	static int[] a;
	static int[] f;	//최소 시작 날짜(마감시간 - 제작시간)
	static int[] s;
	static int[] e;
	public static boolean finish(int[] h){
		for(int i=1; i<=n; i++)
			if(h[i]!=0) return false;
		return true;
	}
						//현재날짜	   //장인상태 		    //구두제작기간
	public static void go(int day,int[] h){
		//각 구두마다 최소 시작날짜(마감시간 - 제작시간) 안에 구두 제작이 시작되어야 한다
		//현재 날에 최소시작날짜이 넘어간 구두가 있다면 불가능
		//최소시작날 짜에 구두 제작 시간 - 현재 날짜의 구두 제작 시간을 비교해서
		//현재날짜의 구두 제작시간이 
	
		//만약에 장인이 더 일하지 못하는데 구두가 남아있다면 불가능
		if(day>pmax)
			if(!finish(h)) return;
		
		//일이 끝낫다면 가능
		if(finish(h)){
			ans = 1;
			return;
		}
		for(int i=1; i<=k; i++){
			if(!work[i][day]) continue; //근무시간이 아니면 패스
			for(int j=1; j<=n; j++){
				if(a[j]>day) continue; //의뢰 날짜가 안된 경우 패스
				--h[j];			//해당 구두제작시간을 1 줄여주고
				go(day+1,h);
				++h[j];
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			d = new int[201][101];
			hour = new int[201];
			work = new boolean[101][101];
			a = new int[201];
			f = new int[201];	//최소 시작 날짜(마감시간 - 제작시간)
			s = new int[101];
			e = new int[101];
			cnt = new int[101];
			ans = 0;
			smax = pmax = 0;
			
			n = in.nextInt();//주문받은 구두의 수
			k = in.nextInt();//장인의 수
			
			for(int i=1; i<=n; i++){
				a[i] = in.nextInt();//구두 의뢰시간
				int finish = in.nextInt();//구두 마감시간
				hour[i] = in.nextInt();//구두 제작시간
				for(int j=a[i]; j<a[i]+hour[i]; j++)
					d[i][j] = 1;
				
				smax = Math.max(finish, smax);
				f[i] = finish - hour[i];
			}

			for(int i=1; i<=k; i++){
				int start = in.nextInt();
				int end = in.nextInt();
				for(int j=start; j<=end; j++)
					work[i][j]=true;
				pmax = Math.max(pmax, end);
			}
			go(0,hour);
			System.out.println("Case #"+t);
			System.out.println(ans);
		}
		
		in.close();
	}
}

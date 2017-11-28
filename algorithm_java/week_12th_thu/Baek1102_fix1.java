package week_12th_thu;
import java.util.*;
public class Baek1102_fix1 {
	/*발전소
	 * string으로 하니깐 시간이 오래걸린다.
	 * 비트마스크 써야되나보다
	 * dp + 비트마스크니깐
	 * */
	static final int INF = 987654321;
	static int n,p;
	static int[][] a = new int[17][17];
	static boolean[] on = new boolean[17];
	static int[][] d = new int[17][1<<17];
	static int ans = 987654321;
	public static int go(int cnt, int now){
		if(cnt>=p) return 0;
		if(d[cnt][now]!=-1) return d[cnt][now];
		d[cnt][now] = INF;
		
		for(int i=0; i<n; i++){
			int temp = 1<<(n-i-1);
			if((now&temp)==0) continue;	//켜져있는 것만 확인한다
			for(int j=0; j<n; j++){
				if(i==j) continue;		//같을 경우 continue
				temp = 1<<(n-j-1);
				if((now&temp)!=0) continue;	//꺼져있는것만 확인
				d[cnt][now] = Math.min(d[cnt][now],go(cnt+1,now+temp)+a[i][j]);
			}
		}
		
		return d[cnt][now];
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				a[i][j]=in.nextInt();
		
		int cnt = 0;
		int now = 0;
		String temp = in.next();
		for(int i=0; i<n; i++){
			//켜져있으면 1
			if(temp.charAt(i)=='Y'){
				now += (1<<(n-i-1));
				++cnt;
			}
			//꺼져있으면 0
		}
		p = in.nextInt();
		
		for(int[] t : d)
			Arrays.fill(t, -1);
		
		System.out.println(now);
		int ans = go(cnt,now);
		if(ans==INF)System.out.println(-1);
		else System.out.println(ans);
		
		in.close();
	}
}

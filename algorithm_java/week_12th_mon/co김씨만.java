package week_12th_mon;

import java.util.*;
public class co김씨만 {

	/*김씨만 행복한 세상
	 * 인접한 지역은 같은 
	 * 
	 * */
	static int n, m;
	static boolean[] cnt = new boolean[201];
	static int[][] a;
	static int[] d;
	static boolean[] c;
	public static int rev(int n){
		if(n==1) return 2;
		else return 1;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		/*int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			
			System.out.println("Case #"+t);
		}*/
		n = in.nextInt();
		m = in.nextInt();
		int[][] a = new int[n+1][n+1];
		boolean[] c = new boolean[n+1];
		int[] d = new int[n+1];
		
		for(int i=0; i<m; i++){
			int x = in.nextInt();
			int y = in.nextInt();
			a[x][y]=1;
			a[y][x]=1;
			if(!cnt[x]) cnt[x]=true;	//확인해야할 친구들만 표시해둔다
			if(!cnt[y]) cnt[y]=true;
		}

		int count = 0;
		int num = 1;
		
		finish:
		for(int i=1; i<=n; i++){
			if(!cnt[i]) continue;	//확인할 필요없는 지역은 pass
			if(c[i]) continue;	//지배한 곳은 다시 확인하지 않는다.
			if(count==0){	//처음확인할 친구인지 판별
				d[i]=num;	//맨처음 1이 지배한다
				num=2;		//다음 지배는 2가 한다	
				++count;	//다시 못들어오게 막는다
				c[i]=true;	//지배 완료한 곳은 true
			}
			
			for(int j=1; j<=n; j++){
				if(i==j) continue;
				//i와 j가 인접해있을 경우에는
				if(a[i][j]==1){
					//아직 지배가 되지 않은 친구라면
					if(!c[j]){
						d[j] = rev(d[i]);	//i와 다른 애로 지배한다
						c[j] = true;		//지배완료한다
					}
					//지배가 된 친구라면
					else if(c[j]){
						//지배된 친구가 i번째와 같은 애라면
						if(d[i]==d[j]){
							break finish;//종료
						}
						d[i]=rev(d[j]);
						c[i]=true;
					}
				}
			}
		}
		
		boolean fin = true;
		for(int i=1; i<=n; i++){
			if(!cnt[i]) continue;	//확인하지않아도 될 애들은 패스
			if(!c[i]){
				System.out.println("불가능하다");
				fin = false;
				break;
			}
		}
		if(fin) System.out.println("가능하다");
		
		in.close();
	}

}

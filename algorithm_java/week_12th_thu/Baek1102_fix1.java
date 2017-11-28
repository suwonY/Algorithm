package week_12th_thu;
import java.util.*;
public class Baek1102_fix1 {
	/*������
	 * string���� �ϴϱ� �ð��� �����ɸ���.
	 * ��Ʈ����ũ ��ߵǳ�����
	 * dp + ��Ʈ����ũ�ϱ�
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
			if((now&temp)==0) continue;	//�����ִ� �͸� Ȯ���Ѵ�
			for(int j=0; j<n; j++){
				if(i==j) continue;		//���� ��� continue
				temp = 1<<(n-j-1);
				if((now&temp)!=0) continue;	//�����ִ°͸� Ȯ��
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
			//���������� 1
			if(temp.charAt(i)=='Y'){
				now += (1<<(n-i-1));
				++cnt;
			}
			//���������� 0
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

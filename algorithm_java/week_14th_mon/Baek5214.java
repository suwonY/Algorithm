package week_14th_mon;

import java.util.*;
public class Baek5214 {
	/*ȯ��
	 * ������Ʃ���ϳ��� �� k���� ���� �����Ѵ�
	 * 1�������� n�������� ���µ� �湮�ϴ� �ּ� ���� ���� �� ���ϱ�?*/
	static final int INF = 987654321;
	static int n,k,m;
	static List<List<Integer>> a = new ArrayList<>();
	static int[] d = new int[100001];
	static boolean[] c = new boolean[100001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();	//���� ��
		k = in.nextInt();	//������Ʃ�갡 �����ϴ� ���� ����
		m = in.nextInt();	//������Ʃ���� ����
		for(int i=0; i<m; i++){
			List<Integer> temp = new ArrayList<>();
			for(int j=0; j<k; j++){
				int num = in.nextInt();
				temp.add(num);
			}
			a.add(temp);
		}
		Arrays.fill(d, INF);
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		d[1] = 1;
		c[1] = true;
		
		finish:
		while(!q.isEmpty()){
			int now = q.remove();
			if(now==n)
				break;
			
			for(List<Integer> temp : a){
				if(temp.contains(now)){//���Ե� �ְ� �ִٸ�
					for(int close : temp){
						if(c[close]) continue;
						d[close] = d[now]+1;
						if(close==n){
							q.clear();
							break finish;
						}
						c[close] = true;
						q.add(close);
					}
				}
			}
		}
		
		System.out.println(d[n]==INF?-1:d[n]);
		in.close();
	}
}
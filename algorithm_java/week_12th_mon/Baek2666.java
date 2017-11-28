package week_12th_mon;

import java.util.*;
public class Baek2666 {
	/*벽장문의 이동
	 * 
	 * 벽장문이 열려있으면 이웃한 벽장의 문을 자신에게 옮길 수 있다
	 * 이용하고 싶은 벽장이 주어졌을 때 최소횟수를 움직여 벽장을 이용할 방법을 찾기
	 * 
	 * d[n]을 n을 사용하기 위한 최소 이동횟수라고 하자
	 * d[n]은
	 * 열려있으면 0
	 * if(open[n]) d[n]=0;
	 * 열려있지 않다면
	 * n-i, n+i중 최소값을 구한다 
	 * n-i가 0보다 클때까지 n+i<
	 * 
	 * */
	static int n; //벽장은 3<=n<=20
	static int k; //이용할 벽장의 개수
	static boolean[] open = new boolean[21]; //열려있는 벽장 - 열려있으면 true
	static List<Integer> a = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<2; i++)
			open[in.nextInt()]=true;
		
		k = in.nextInt();
		for(int i=0; i<k; i++)
			a.add(in.nextInt());
		
		int ans = 0;
		for(int i : a){
			//열려있으면 바로 쓰면된다.
			if(open[i]) continue;
			//열려있지 않다면
			for(int j=0; j<a.size(); j++){
				//왼쪽께 열려있거나
				if(i-j>0 && open[i-j]){
					ans += j;
					//i-j를 close 해주고 i를 open해준다
					open[i-j]=false;
					open[i]=true;
					break;
				}
				//오른쪽꺼가 열려있거나
				else if(i+j<=n && open[i+j]){
					ans += j;
					open[i+j]=false;
					open[i]=true;
					break;
				}
			}
		}
		System.out.println(ans);
		in.close();
	}
}
package week_12th_mon;

import java.util.*;
public class Baek2666_fix2 {
	/*벽장문의 이동
	 * 
	 반례가 생기는데
	 문이 3개다
	 1,2 가 열려있는상태에서
	 3 2 3 순서대로 사용하고 싶을 때
	 왼쪽꺼를 해주냐 오른쪽꺼를 옮겨주냐에 따라 2,3 결과가 나온다
	 
	 그럼 문을
	 왼쪽에서 옮기거나 오른쪽에서 옮기고 계산한 것 중에서 최소값을 만들어야 된다.
	 
	 d[n]
	 a[n][0] 은 왼쪽에서 땡겨와서 n이 열리게 할 때 최소값
	 a[n][1] 은 오른쪽에서 땡겨와서 n이 열리게 하는 최소값
	 d[n] = a[n][0] 과 a[n][1]의 min값이 된다
	 
	 * */
	static int n, ans = 987654321; //벽장은 3<=n<=20
	static int k; //이용할 벽장의 개수
	static int[] open = new int[2];
	static List<Integer> arr = new ArrayList<>();
	public static void go(int l, int r, int cnt, int total){
		if(cnt==k){
			ans = Math.min(total, ans);
			return;
		}
		int num = arr.get(cnt);
		//왼쪽꺼를 땡겨다 쓸 경우
		if(l==num) 
			go(l,r,cnt+1,total);
		
		else if(l!=num)
			go(num,r,cnt+1,total + Math.abs(num-l));
		
		//오른쪽꺼를 땡겨다 쓸 경우
		if(r==num)
			go(l,r,cnt+1,total);
		
		else if(r!=num)
			go(l,num,cnt+1,total+Math.abs(num-r));
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<2; i++){
			int o = in.nextInt();
			open[i] = o;
		}
		
		Arrays.sort(open);
		
		k = in.nextInt();
		for(int i=0; i<k; i++)
			arr.add(in.nextInt());
		
		go(open[0],open[1],0,0);
		System.out.println(ans);
		
		in.close();
	}
}
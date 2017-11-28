package week_12th_mon;

import java.util.*;
public class Baek2666_fix {
	/*벽장문의 이동
	 * 
	 반례가 생기는데
	 문이 3개다
	 1,2 가 열려있는상태에서
	 3 2 3 순서대로 사용하고 싶을 때
	 왼쪽꺼를 해주냐 오른쪽꺼를 옮겨주냐에 따라 2,3 결과가 나온다
	 
	 그럼 문을
	 왼쪽에서 옮기거나 오른쪽에서 옮기고 계산한 것 중에서 최소값을 만들어야 된다.
	 
	 * */
	static int n; //벽장은 3<=n<=20
	static int k; //이용할 벽장의 개수
	static boolean[] ropen = new boolean[21];
	static boolean[] lopen = new boolean[21];
	static List<Integer> a = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=0; i<2; i++){
			int o = in.nextInt();
			lopen[o] = ropen[o] = true;
		}
		
		k = in.nextInt();
		for(int i=0; i<k; i++)
			a.add(in.nextInt());
		
		int left = 0;
		for(int i : a){
			//열려있으면 바로 쓰면된다.
			if(lopen[i]) 
				continue;
			//열려있지 않다면
			for(int j=0; j<a.size(); j++){
				//오른쪽꺼가 열려있거나
				if(i+j<=n && lopen[i+j]){
					left += j;
					lopen[i+j]=false;
					lopen[i]=true;
					break;
				}
				//왼쪽께 열려있거나
				if(i-j>0 && lopen[i-j]){
					left += j;
					//i-j를 close 해주고 i를 open해준다
					lopen[i-j]=false;
					lopen[i]=true;
					break;
				}
			}
		}
		int right = 0;
		for(int i : a){
			//열려있으면 바로 쓰면된다.
			if(ropen[i])
				continue;
			//열려있지 않다면
			for(int j=0; j<a.size(); j++){
				//왼쪽께 열려있거나
				if(i-j>0 && ropen[i-j]){
					right += j;
					//i-j를 close 해주고 i를 open해준다
					ropen[i-j]=false;
					ropen[i]=true;
					break;
				}
				//오른쪽꺼가 열려있거나
				if(i+j<=n && ropen[i+j]){
					right += j;
					ropen[i+j]=false;
					ropen[i]=true;
					break;
				}
			}
		}
		System.out.println(Math.min(left, right));
		in.close();
	}
}
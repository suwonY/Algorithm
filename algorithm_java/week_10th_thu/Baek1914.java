package week_10th_thu;

import java.util.*;
public class Baek1914 {

	/*하노이 탑
		1~n개의 원판을
		1~n-1개를 b에 놓고 n을 c로 옮긴다
		1~n-2개를 a에 놓고 n-1를 c로 옮긴다
		~
		1을 c로 옮긴다
		
		더 작게 생각해보자 
		1~n-1을 b로 옮기는 방법을 생각해보자
		-> 1~n-2를 c로 옮긴 n-1를 b로 옮긴다
		
		
		n==2일 경우(c로 최종적으로 옮기는데, b를 이용한 경우)
		1. 1을 b에 놓고 2를 c에 놓는다(1->2, 1->3)
		2. 1를 c로 옮긴다(2->3)
		(a-b a-c b-c)
		
		n==3일 경우
		2까지 b로 옮긴후 3을 c로 옮긴다
		1. a에서 2까지 b로 옮기는 방법은 (b로 최종적으로 옮기는데, c를 이용한 경우)
		(a-c a-b c-b)
		2. 3을 c로 옮긴다(a-c)
		3. 1까지 a로 옮긴다(b-a)
		4. 2를 c로 옮긴다(b-c)
		5. 1을 c로 옮긴다(a-c)
		
		n==4일 경우
		1.a에서 3까지 b로 옮긴다
		- 3까지 b로 옮긴다
		  - 2까지 c로 옮긴다
		  - 1
		
		1. a에서 b까지 n-1을 옮긴다
		- n-2까지 a에서 c로 옮긴다
		  - n-3까지 a에서 b로 옮긴다
		  - n-4까지 a에서 c로 옮긴다
		  - n-5까지 a에서 b로 옮긴다
		  - n-6까지 a에서 c로 옮긴다 ~~~~
		  - 1을 b나 c로 옮긴다
		2. 
	 * */
	static int n, ans=0;
	static long ans1=0;
	//어디서 어디로 갔는지 저장
	static int[][] a = new int[3000000][2];
	public static void go(int num, int from, int to, int spare){
		//결국
		//1. n-1개를 from에서 spare에게 보내주고
		//2. n을 from에서 to로 보낸다
		//3. n-1개를 spare에서 to로 보낸다
		if(num==1){
			//2.계속나옴
			a[ans][0]=from;
			a[ans][1]=to;
			++ans;
		}
		else{
			//1.
			go(num-1,from,spare,to);
			a[ans][0]=from;
			a[ans][1]=to;
			++ans;
			//3.
			go(num-1,spare,to,from);
		}
	}
	public static void go1(int num, int from, int to, int spare){
		if(num==1)
			++ans1;
		else{
			go(num-1,from,spare,to);
			++ans1;
			go(num-1,spare,to,from);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		if(n<=20){
			go(n,1,3,2);
			System.out.println(ans);
			for(int i=0; i<ans; i++)
				System.out.println(a[i][0] + " " + a[i][1]);
		}
		else{
			go1(n,1,3,2);
			System.out.println(ans1);
		}
		in.close();
	}
}
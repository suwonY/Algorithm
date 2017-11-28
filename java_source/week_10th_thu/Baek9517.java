package week_10th_thu;

import java.util.*;
public class Baek9517 {

	/*아이러브 크로아티아
	 * 폭탄은 3분 30초후에 터진다
	 * 문제를 못맞추거나 패스하면 다음 문제를 푼다
	 * 문제를 풀면 왼쪽 사람에게 넘겨줌
	 * 
	 * 폭탄을 들고있는 사람의 번호와 n개의 질문을 대답할때까지 걸린 시간이 주어진다
	 * 폭탄을 터뜨린 사람의 번호를 구하라
	 * 대답이 맞으면 T
	 * 대답이 틀리면 N
	 * 스킵했으면     P*/
	static int total = 210;	//끝나는시간
	static int ans,n;
	public static void change(){
		if(ans==8){
			ans=1;
			return;
		}
		++ans;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ans = in.nextInt();
		n = in.nextInt();
		for(int i=0; i<n; i++){
			int t = in.nextInt();
			String temp = in.next();
			char ans = temp.charAt(0);
			
			//남은 시간이 
			if(t>total)
				break;
			
			total-=t;
			if(ans=='T')
				change();
		}
		System.out.println(ans);
		in.close();
	}
}

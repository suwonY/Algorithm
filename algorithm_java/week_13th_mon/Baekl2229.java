package week_13th_mon;

import java.util.*;
public class Baekl2229 {

	/*조짜기
	 * 잘하는 학생과 잘못하는 학생들을 같은조로 묶는다
	 * 실력차이가 많이 나게 묶는다
	 * 
	 * 1. 나이순으로 정렬한다.
	 * 2. 개수는 상관없다
	 * 3. 가장 높은친구와 낮은친구의 차이 = 그 조의 잘 짜진 정도
	 * 4. 한명으로 구성된 조의 잘 짜여진 정도는 0
	 * 
	 * 점화식을 한번 세워보자
	 * d[i]를 i개의 조로 만들었을때의 정도라고 하면
	 * 너무 노가다같은대
	 * 흠...
	 * 
	 d[i]를 i명의 사람들의 잘 짜여진 정도의 최대값이라고하면
	 d[1] = 0
	 d[2] = d[1] + 0, a[2]-a[3]중 최대값
	 d[3] = 
	  		d[2] + 0
	  		d[1] + abs(a[3]-a[2])
	  중 최대값
	 d[4] = 
	 * 		d[3] + 0
	 * 		d[2] + a[4]-a[3]
	 * 		d[1] + 2~4의 최대값
	 * 		d[0] + 1~4의 최대값
	 * 
	 *  ~~
	 *  
	 d[n] = 
	 d[n-1] + 0
	 d[n-2] + a[n]-a[n-1]
	 d[n-3] + n-2~n까지 최대값-최소값
	 ~
	 ~
	 d[0] + 1~n까지의 최대값-최소값
	  중 최대값이 답이 되는상황이 발생한다.
	  
	 * */
	static final int INF = 987654321;
	static int n;
	static int[] a;
	static int[] d;
	public static void go(){
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n];
		d = new int[n];
		//나이순서대로 주어진다
		for(int i=0; i<n; i++)
			a[i] = in.nextInt();
		
		
		/*
		d[n] = 
	 	d[n-1] + 0
	 	d[n-2] + a[n]-a[n-1]
	 	d[n-3] + n-2~n까지 최대값-최소값
	 	~
	 	~
	 	d[0] + 1~n까지의 최대값-최소값
	  	중 최대값이 답이 되는상황이 발생한다.
	  	
	  	d[1] = 0
	 	d[2] = 
	 		d[1] + 0
	 		d[0] + a1~2 중 최대값-최소값
	 	d[3] = 
	  	 d[2] + 0
	  	 d[1] + abs(a[3]-a[2])
	  	중 최대값
	  	d[4] = 
	  	 d[3] + 0
	  	 d[2] + 3~4
	  	 d[1] + 2~4
	  	 d[0] + 1~4
		*/
		d[0] = 0;
		for(int i=1; i<n; i++){
			if(i==1){
				d[i] = Math.abs(a[1]-a[0]);
				continue;
			}
			int temp = 0, max = 0, min = INF;
//			System.out.println("d["+i+"] 일 때");
			for(int j=i; j>0; j--){
				max = Math.max(max, a[j]);
				min = Math.min(min, a[j]);
//				System.out.println("d["+j+"]"+ " = " + "d["+(j-1)+"] + " + "(max :" + max +" min :"+min + ")" + (max-min));
				temp = Math.max(temp, d[j-1]+(max-min));
			}
			d[i] = temp;
		}
		System.out.println(d[n-1]);
		in.close();
	}
}

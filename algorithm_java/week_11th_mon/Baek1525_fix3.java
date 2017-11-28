package week_11th_mon;

import java.util.*;

public class Baek1525_fix3 {

	static int[] m = {-3,3,-1,1};
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static List<int[]> c = new ArrayList<>();
	static int[] a = new int[9];
	static int[] end = {1,2,3,4,5,6,7,8,0};
	static int ans = 987654321;
	public static boolean finish(int[] a){
		for(int i=0; i<9; i++){
			if(a[i]!=end[i]) return false;
		}
		return true;
	}
	//같은게 있는지 찾아봐야되는 건대 이게 잘못된거같다
	public static boolean find(int[] temp){	//같으면 true, 다르면 false
		for(int[] a : c){
			for(int i=0; i<9; i++){
				if(a[i]!=temp[i])
					return false;
			}
		}
		return true;
	}
	public static int[] swap(int[] a, int num1, int num2 ){
		int temp = a[num1];
		a[num1] = a[num2];
		a[num2] = temp;
		return a;
	}
	public static void go(int[] a){
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a,0));
		c.add(a);
		
		while(!q.isEmpty()){
			Node temp = q.poll();
			int[] now = temp.a;
			int cnt = temp.cnt;
			
			if(finish(now)){
				ans = Math.min(cnt, ans);
			}
			int pos = 0;
			for(int i=0; i<9; i++){
				if(now[i]==0){
					pos=i;
					break;
				}
			}
			int x = pos/3;
			int y = pos%3;
			
			for(int k=0; k<4; k++){
				int nx = x + dx[k];
				int ny = y + dy[k];

				if(nx<0 || nx>=3 || ny<0 || ny>=3) continue;
				
				//임시배열 설정
				int[] next = new int[9];
				//현재배열을 복사해둠
				for(int i=0; i<9; i++) next[i] = now[i];
				//0을 바꿈
				swap(next,pos,pos+m[k]);
				
				if(find(next)) continue;
				
				//방문한적이 없으면
				q.add(new Node(next,cnt+1));
				c.add(next);
			}
			
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int i=0; i<9; i++){
			a[i] = in.nextInt();
		}
		
		go(a);
		System.out.println(ans);
		in.close();
	}

	public static class Point {
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	public static class Node{
		int[] a = new int[9];
		int cnt;
		public Node(int[] a, int cnt){
			this.a = a;
			this.cnt = cnt;
		}
		
	}
}

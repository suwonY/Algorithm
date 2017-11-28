package week_11th_mon;

import java.util.*;
public class Baek1525_fix4 {

	static int[] m = {-3,3,-1,1};
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static int ans = 987654321;
	static String start = "", end = "123456780";
	static List<String> c = new ArrayList<>();
	public static String swap(String str, int i, int j) { 
		char[] ch = str.toCharArray();
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
		return String.copyValueOf(ch);
	}
	public static boolean find(String str){
		for(String t : c)
			if(t.equals(str)) return true;
		return false;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int i=0; i<9; i++) start += in.nextInt();
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start, 0));
		c.add(start);
		
		while(!q.isEmpty()){
			Node t = q.remove();
			String now = t.str;
			int cnt = t.cnt;
			
			if(now.equals(end)){
				System.out.println("들어온적이 있음");
				ans = Math.min(ans, cnt);
			}
			
			int pos = 0;
			pos = now.indexOf('0');
			int x = pos/3;
			int y = pos%3;
			
			for(int k=0; k<4; k++){
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(nx<0 || nx>=3 || ny<0 || ny>=3) continue;
				String next = now;
				swap(next,pos,pos+m[k]);
				
				if(find(next)) continue;
				
				q.add(new Node(next,cnt+1));
				c.add(next);
			}
		}
		
		System.out.println(ans);
		in.close();
	}
	public static class Node {
		String str;
		int cnt;
		public Node(String str,  int cnt) {
			this.str = str;
			this.cnt = cnt;
		}
	}
}

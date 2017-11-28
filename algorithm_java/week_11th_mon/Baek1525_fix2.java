package week_11th_mon;

import java.util.*;
public class Baek1525_fix2 {

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
		Queue<String> q = new LinkedList<>();
		q.add(start);
		c.add(start);
		
		int cnt = 0;
		while(!q.isEmpty()){
			for(int i=0; i<q.size(); i++){
				String now = q.remove();
				
				if(now.equals(end)){
					System.out.println(cnt);
//					ans = Math.min(ans, cnt);
				}
				
				int pos = 0;
				pos = now.indexOf('0');
				int x = pos/3;
				int y = pos%3;
				
				for(int k=0; k<4; k++){
					int nx = x + m[k];
					int ny = y + m[k];
					
					if(nx<0 || nx>=3 || ny<0 || ny>=3) continue;
					String next = now;
					System.out.println(pos + pos+m[k]);
					swap(next,pos,pos+m[k]);
					
					//방문한적이 있으면 true
					if(find(next)) continue;
					
					q.add(next);
					c.add(next);
				}
			}
			++cnt;
		}
//		if(ans==987654321)
//			System.out.println(-1);
//		else
//			System.out.println(ans);
		in.close();
	}
}

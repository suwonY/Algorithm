package week_15th_thu;

import java.util.*;
public class Baek14226 {

	//이모티콘
	//Deque 두개 쓰면될거같음
	static boolean[][] c = new boolean[1001][1001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		Queue<Node> q = new LinkedList<>();
		Node start = new Node(1,0);
		q.add(start);
		
		int ans = 0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0; i<size; i++){
				Node now = q.poll();
				int cnt = now.now;
				int clip = now.clip;
				if(cnt==n){
					q.clear();
					break;
				}
				c[cnt][clip] = true;
				
				if(clip+cnt<=n){
					if(c[cnt][clip+cnt]) continue;
					q.add(new Node(cnt,clip+cnt));
					c[cnt][clip+cnt]=true;
					
					if(c[cnt+clip][clip]) continue;
					q.add(new Node(cnt+clip, clip));
					c[cnt+clip][clip]=true;
				}
				
				if(cnt-1<0) continue;
				if(c[cnt-1][clip]) continue;
				q.add(new Node(cnt-1,clip));
				c[cnt-1][clip]=true;
			}
			++ans;
		}
		System.out.println(ans-1);
		in.close();
	}
	public static class Node{
		int now, clip;
		public Node(int now, int clip){
			this.now = now;
			this.clip = clip;
		}
	}
}

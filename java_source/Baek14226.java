package week_15th_thu;

import java.util.*;
public class Baek14226_fix1 {

	//이모티콘
	//Deque 두개 쓰면될거같음
	static boolean[][] c = new boolean[1001][1001];
	static int[][] d = new int[1001][1001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		Queue<Node> q = new LinkedList<>();
		Node start = new Node(1,0);
		d[1][0] = 0;
		q.add(start);
		
		int ans = 0;
		while(!q.isEmpty()){
			Node now = q.poll();
			int cnt = now.now;
			int clip = now.clip;
			if(cnt==n){
				q.clear();
				break;
			}
			
			if(clip+cnt<=n){
				if(d[cnt][clip+cnt]==0){
					d[cnt][clip+cnt]=d[cnt][clip]+1;
					q.add(new Node(cnt,clip+cnt));
				}
				if(d[cnt+clip][clip]==0)
				d[cnt+clip][clip]=d[cnt][clip]+1;
				q.add(new Node(cnt+clip, clip));
			}
			if(cnt-1>=0 && d[cnt-1][clip]==0){
				q.add(new Node(cnt-1,clip));
				c[cnt-1][clip]=true;
				
			}
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

package week_15th_mon;
import java.util.LinkedList;
import java.util.Queue;
public class kakao07 {
	public static int go(String[] a, String b){
		final int INF = 987654321;
		int min = INF;
		int n = a.length;
		Queue<String> q = new LinkedList<>();
		for(int i=0; i<a.length; i++)
			if(a[i].charAt(0)==b.charAt(0))
				q.add(a[i]);
		int cnt = 1;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i=0; i<size; i++){
				String temp = q.remove();
				if(temp.equals(b)){
					min = Math.min(min, cnt);
					q.clear();
					break;
				}
				for(int j=0; j<n; j++){
					String next = temp + a[j];
					if(next.length() > b.length()) continue;
					if(b.contains(next))
						q.add(next);
				}
			}
			++cnt;
		}
		return min!=INF?min:-1;
	}
	public static void main(String[] args) {
		String[] a1 = {"ba","na","n","a"};
		String b1 = "banana";
		
		String[] a2  = {"app","ap","p","l","e","ple","pp"};
		String b2 = "apple";
		
		String[] a3 = {"ba","an","nan","ban","n"};
		String b3 = "banana";
		
		System.out.println(go(a1,b1));
		System.out.println(go(a2,b2));
		System.out.println(go(a3,b3));
	}
}

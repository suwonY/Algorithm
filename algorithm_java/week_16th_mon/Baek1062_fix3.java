package week_16th_mon;
import java.util.*;
public class Baek1062_fix3 {
	static int n, k, ans=0;
	static List<Integer> a = new ArrayList<>();
	static boolean[] c = new boolean[26];
	public static void go(int cnt, int start){
		if(cnt>k) return;
		if(cnt==k){
			int max = 0;
			for(int t: a)
				if(t-start==0)
					++max;
			ans = Math.max(max, ans);
			return;
		}
		for(int i=0; i<26; i++){
			if(i==0 || i==2 || i==8 || i==13 || i==19) continue;
			if(!c[i]) continue;	//확인할 필요없는 애들
			//이미 확인한 애는 다시 확인안해도된다
			if((start & (1<<i)) !=0 ) continue;
			go(cnt+1,start|(1<<i));
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();
		k-=5;	//배워야할 단어 개수는 acnti(5개) 빼고
		for(int i=0; i<n; i++){
			String temp = in.next();
			temp.substring(4, temp.length()-4);
			int al = 0;
			for(int j=0; j<temp.length(); j++){
				char t = temp.charAt(j);
				if(!c[t-'a']) c[t-'a']=true;
				al |= (1<<(t-'a'));
			}
			a.add(al);
		}
		int start = (1<<0) + (1<<2) + (1<<8) + (1<<13) + (1<<19);
		go(0,start);
		System.out.println(ans);
		in.close();
	}
}
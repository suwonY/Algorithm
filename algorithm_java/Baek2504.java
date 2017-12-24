import java.util.*;
public class Baek2504 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);;
		String n = in.next();
		Stack<Character> s = new Stack<>();
		int ans = 0, k = 0, l = 0, mul = 1;
		for(int i=0; i<n.length(); i++){
			char now = n.charAt(i);
			switch(now){
			case '(':
				++k;
				s.push(now);
				mul *= 2;
				if(i+1<n.length() && n.charAt(i+1)==')')
					ans += mul;
				break;
			case '[':
				++l;
				s.push(now);
				mul *= 3;
				if(i+1<n.length() && n.charAt(i+1)==']')
					ans += mul;
				break;
			case ')':
				--k;
				s.pop();
				mul /=2;
				break;
			case ']':
				--l;
				s.pop();
				mul /=3;
				break;
			}
		}
		if(!s.isEmpty() || l!=0 || k!=0) System.out.println(0);
		else System.out.println(ans);
		in.close();
	}
}
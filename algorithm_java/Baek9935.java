import java.util.*;
public class Baek9935 {
	static String a;
	static int idx = 0, len;
	static char[] ans = new char[1000001], bomb = new char[1000001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		a = in.next();
		String temp  = in.next();
		for(int i=0; i<temp.length(); i++)
			bomb[i] = temp.charAt(i);
		len = temp.length();
		for(int i = 0; i < a.length(); i++) {
			ans[idx++] = a.charAt(i);
			if (ans[idx - 1] == bomb[len - 1]){
				if(idx-len<0) continue;
				boolean c = false;
				for(int j = 0; j < len; j++) {
					if (ans[idx - 1 - j] != bomb[len - 1 - j]){
						c = true;
						break;
					}
				}
				if(!c) idx -= len;
			}
		}
		if(idx==0) System.out.println("FRULA");
		else{
			for(int i=0; i<idx; i++)
				System.out.print(ans[i]);
		}
		in.close();
	}
}
import java.util.*;
public class Baek2839 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int ans = 0;
		while(true){
			if(n%5==0){
				ans += (n/5);
				break;
			}
			n -= 3;
			++ans;
			if(n<0) break;
		}
		System.out.println(n<0?-1:ans);
		in.close();
	}
}
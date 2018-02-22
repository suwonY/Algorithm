import java.util.*;
public class Baek2675 {
	static int t, n;
	static String a;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		t = in.nextInt();
		for(int tc=0; tc<t; tc++){
			n = in.nextInt();
			a = in.next();
			for(int i=0; i<a.length(); i++)
				for(int j=0; j<n; j++)
					System.out.print(a.charAt(i));
			System.out.println();
		}
		in.close();
	}
}
package pracAlgorithm;

import java.util.*;
public class Baek5532 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int L,A,B,C,D;
		L = in.nextInt();
		A = in.nextInt();
		B = in.nextInt();
		C = in.nextInt();
		D = in.nextInt();
		System.out.println((L - Math.max(A%C == 0 ? A / C : A / C + 1, (B%D == 0 ? B / D : B / D + 1))));
		in.close();
	}
}
package pracAlgorithm;

import java.util.*;
public class Baek2161 {
	static int n;
	static Deque<Integer> q = new ArrayDeque<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i=1; i<=n; i++)
			q.addLast(i);
		for(int i=0; i<n-1; i++){
			System.out.print(q.removeFirst()+" ");
			q.addLast(q.removeFirst());
		}
		System.out.println(q.removeFirst());
		in.close();
	}
}
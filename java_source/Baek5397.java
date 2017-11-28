package week_13th_mon;

import java.util.*;
public class Baek5397_fix1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=0; t<tc; t++){
			String l = in.next();
			Deque<Character> a = new ArrayDeque<>();
			Deque<Character> b = new ArrayDeque<>();
			for(int i=0; i<l.length(); i++){
				char c = l.charAt(i);
				switch(c){
				case '<':
					if(!a.isEmpty())
						b.addLast(a.removeLast());
					break;
				case '>':
					if(!b.isEmpty())
						a.addLast(b.removeLast());
					break;
				case '-':
					if(!a.isEmpty())
						a.removeLast();
					break;
				default:
					a.addLast(c);
					break;
				}
			}
			while(!b.isEmpty())
				a.addLast(b.removeLast());
			while(!a.isEmpty())
				System.out.print(a.removeFirst());
			System.out.println();
		}
		in.close();
	}
}
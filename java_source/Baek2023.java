import java.util.*;
public class Baek2023 {
	static int n;
	static boolean prime(int num){
		for(int i=2; i<=num/2; i++)
			if(num%i==0) return false;
		return true;
	}
	static void go(int cnt, int num){
		if(cnt==n){
			if(prime(num)) System.out.println(num);
			return; 
		}
		for(int i=1; i<10; i++){
			if(i==2) continue;
			if(prime(num*10+i)) go(cnt+1, num*10+i);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		go(1,2);
		go(1,3);
		go(1,5);
		go(1,7);
		in.close();
	}
}

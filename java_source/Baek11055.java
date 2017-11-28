package pracAlgorithm;

import java.util.*;
public class Baek11055 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] d = new int[n+1];
		for(int i=1; i<=n; i++){
			if(i==1){
				d[i]=1;
				continue;
			}
			if(i==2){
				d[i] = 2;
				continue;
			}
			d[i] = d[i-1] + d[i-2];
		}
		for(int i=0; i<n; i++)
		System.out.print(d[i]+" ");
		in.close();
	}
}
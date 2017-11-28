package pracAlgorithm;

import java.util.*;
public class Baek14889 {
	static int n,ans=987654321;
	static int[][] a;
	static int[] team1, team2;
	public static void go(String s, int start, int link){
		if(s.length()==n){
			team1 = new int[n/2]; team2 = new int[n/2];
			int index1 = 0, index2 = 0;
			for(int i=0; i<s.length(); i++){
				if(s.charAt(i)=='1') team1[index1++] = i;
				else team2[index2++] = i;
			}
			int total1=0, total2=0;
			for(int i=0; i<n/2; i++){
				for(int j=0; j<n/2; j++){
					if(i==j) continue;
					total1 += a[team1[i]][team1[j]];
					total2 += a[team2[i]][team2[j]];
				}
			}
			ans = Math.min(ans, Math.abs(total1-total2));
			return;
		}
		if(start<(n/2)) go(s+"1",start+1,link);
		if(link<(n/2)) go(s+"2",start,link+1);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		a = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				a[i][j] = in.nextInt();
		go("",0,0);
		System.out.println(ans);
		in.close();
	}
}
	
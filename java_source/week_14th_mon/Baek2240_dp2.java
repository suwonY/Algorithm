package week_14th_mon;

import java.util.*;
public class Baek2240_dp2 {
	
	static int t,w,ans=0;
    static int d[][] = new int[1001][31];
    static int a[] = new int[1001];
    public static void main(String[] args) throws Exception {
    	Scanner in = new Scanner(System.in);
		t = in.nextInt();
		w = in.nextInt();
         
        for(int i=1; i<=t; i++)
        	a[i] = in.nextInt();
         
        if(a[1]==1) d[1][0] = 1;
        else d[1][1] = 1;
 
        for(int i = 2; i <= t; i++){
            for(int j = 0; j <=w; j++){
                if(j == 0){
                	if (a[i]==1) d[i][0] = d[i-1][0]+1;
                	else d[i][0] = d[i-1][0];
                }
                else{
                    if(j%2==0){//짝수번 이동했으면 1번
                    	if(a[i]==1)
                    		d[i][j] = Math.max(d[i-1][j-1], d[i-1][j])+1;
                    	else
                    		d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]);
                    } else{//홀수번 이동 2번
                    	if(a[i]==2)
                    		d[i][j] = Math.max(d[i-1][j-1], d[i-1][j])+1;
                    	else
                    		d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]);
                    }
                }
            }
        }
        for(int i = 0; i <= w; i++)
        	ans = Math.max(d[t][i],ans);
        
        System.out.println(ans);
        in.close();
    }
}
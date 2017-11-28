package week_17th_thu;

import java.util.*;
public class swExpert2115 {

	static int n,m,max;
	static long ans;
	static int[][] a;
	static boolean[][] c1;
	static boolean[][] c2;
	static int dx = 0, dy = 1;
	public static void go(){
		int[] w1 = new int[m];
		int[] w2 = new int[m];
		c1 = new boolean[n][n];
		c2 = new boolean[n][n];
		//1번이 도는 동안 확인
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(j+m>n) continue;
//				System.out.println("1번이 : " + i + "," + j + "일 때");
				w1 = new int[m];
				c1[i][j] = true;
				int temp1 = a[i][j];
				if(temp1<=max) w1[0]=a[i][j];

				for(int t=1, index=1; t<m; t++){
					temp1 += a[i][j+t];
					c1[i][j+t] = true;
					if(temp1<=max) w1[index++]=a[i][j+t];
				}
				
				//2번을 돌면서 확인
				for(int k=0; k<n; k++){
					for(int l=0; l<n; l++){
						if(l+m>n) continue;
						if(c1[k][l]) continue;
						boolean im = false;
						for(int t=1; t<m; t++){
							if(c1[k][l+t]) {
								im = true;
								break;
							}
						}
						if(im) continue;
						w2 = new int[m];

//						System.out.println("2번이 : " + k + "," + l + "일 때");
						int temp2 = a[k][l];
						if(temp2<=max) w2[0]=a[k][l];

						for(int t=1, index=1; t<m; t++){
							temp2 += a[k][l+t];
							if(temp2<=max) w2[index++]=a[k][l+t];
						}
						
						long cnt = 0;
						for(int t=0; t<m; t++){
							cnt += (w1[t]*w1[t]);
							cnt += (w2[t]*w2[t]);
						}
//						for(int s=0; s<m; s++){
//							System.out.print(w1[s]+" ");
//						}
//						System.out.println();
//						for(int s=0; s<m; s++){
//							System.out.print(w2[s]+" ");
//						}
//						System.out.println();
//						System.out.println(cnt);
						ans = Math.max(cnt, ans);
						c2 = new boolean[n][n];
					}
				}
//				c1 = new boolean[n][n];
			}
		}
		
//		System.out.println("마지막 ");
//		for(int s=0; s<m; s++){
//			System.out.print(w1[s]+" ");
//		}
//		System.out.println();
//		for(int s=0; s<m; s++){
//			System.out.print(w2[s]+" ");
//		}
//		System.out.println();
//		System.out.println(ans);
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = in.nextInt();
		for(int t=1; t<=tc; t++){
			n = in.nextInt();
			m = in.nextInt();
			max = in.nextInt();
			a = new int[n][n];
			ans = 0;
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					a[i][j] = in.nextInt();
			go();
			
			System.out.println("#"+t+" "+ans);
		}
		in.close();
	}
}
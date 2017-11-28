package Baek_SWPractice_1st;

import java.util.*;
public class Baek14500_fix1 {

	static int n,m,ans;
	static int[] dx1 = {0,1,2,3,0,0,0,0};
	static int[] dx2 = {0,0,0,0,0,1,2,3}; 	//| ¤Ñ µÎ°³
	
	static int[] dx3 = {0,0,1,1,0,1,0,1};	//¹ç¸ð¾ç
	
	static int[] dx4 = {0,0,1,1,0,1,1,2};
	static int[] dx5 = {0,0,-1,-1,0,1,1,2};
	static int[] dx6 = {0,0,-1,1,0,1,1,0};
	static int[] dx7 = {0,1,1,2,0,0,1,1};
	
	static int[] dx8 = {0,0,0,1,0,1,2,2};
	static int[] dx9 = {0,1,1,1,0,0,1,2};
	static int[] dx10 = {0,0,0,1,0,1,2,1};
	static int[] dx11 = {0,1,1,1,2,0,1,2};
	
	static int[] dx12 = {0,1,2,2,0,0,0,1};
	static int[] dx13 = {0,1,2,2,0,1,1,1};
	static int[] dx14 = {0,0,1,2,0,1,0,0};
	static int[] dx15 = {0,0,1,2,0,1,1,1};

	static int[] dx16 = {0,1,1,1,1,0,1,2};
	static int[] dx17 = {0,0,0,1,0,1,2,1};
	static int[] dx18 = {0,1,1,2,1,0,1,1};
	static int[] dx19 = {0,1,1,2,0,0,1,0};
	
	static int[][] a = new int[501][501];
	static boolean[][] c = new boolean[501][501];
	static List<int[]> d = new ArrayList<>();
	public static void go(){
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				for(int[] t : d){
					int sum = 0;
					for(int k=0; k<4; k++){
						int nx = i + t[k];
						int ny = j + t[k+4];
						
						if(nx<0 || nx>=n || ny<0 || ny>=m) break;
						sum += a[nx][ny];
					}					
					ans = Math.max(ans, sum);
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				a[i][j] = in.nextInt();
		
		d.add(dx1);
		d.add(dx2);
		d.add(dx3);
		d.add(dx4);
		d.add(dx5);
		d.add(dx6);
		d.add(dx7);
		d.add(dx8);
		d.add(dx9);
		d.add(dx10);
		d.add(dx11);
		d.add(dx12);
		d.add(dx13);
		d.add(dx14);
		d.add(dx15);
		d.add(dx16);
		d.add(dx17);
		d.add(dx18);
		d.add(dx19);
		
		go();
		System.out.println(ans);
		in.close();
	}
}
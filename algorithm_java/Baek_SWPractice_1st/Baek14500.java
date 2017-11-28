package Baek_SWPractice_1st;

import java.util.*;
public class Baek14500 {

	static int n,m,ans;
	static int[] dx1 = {0,1,2,3}, dy1 = {0,0,0,0};
	static int[] dx2 = {0,0,0,0}, dy2 = {0,1,2,3}; 	//| ¤Ñ µÎ°³
	
	static int[] dx3 = {0,0,1,1}, dy3 = {0,1,0,1};	//¹ç¸ğ¾ç
	
	static int[] dx4 = {0,0,1,1}, dy4 = {0,1,1,2};
	static int[] dx5 = {0,0,-1,-1}, dy5 = {0,1,1,2};
	static int[] dx6 = {0,0,-1,1}, dy6 = {0,1,1,0};
	static int[] dx7 = {0,1,1,2}, dy7 = {0,0,1,1};
	
	static int[] dx8 = {0,0,0,1}, dy8 = {0,1,2,2};
	static int[] dx9 = {0,1,1,1}, dy9 = {0,0,1,2};
	static int[] dx10 = {0,0,0,1}, dy10 = {0,1,2,1};
	static int[] dx11 = {0,1,1,1}, dy11 = {2,0,1,2};
	
	static int[] dx12 = {0,1,2,2}, dy12 = {0,0,0,1};
	static int[] dx13 = {0,1,2,2}, dy13 = {0,1,1,1};
	static int[] dx14 = {0,0,1,2}, dy14 = {0,1,0,0};
	static int[] dx15 = {0,0,1,2}, dy15 = {0,1,1,1};

	static int[] dx16 = {0,1,1,1}, dy16 = {1,0,1,2};
	static int[] dx17 = {0,0,0,1}, dy17 = {0,1,2,1};
	static int[] dx18 = {0,1,1,2}, dy18 = {1,0,1,1};
	static int[] dx19 = {0,1,1,2}, dy19 = {0,0,1,0};
	
	static int[][] a = new int[501][501];
	public static void go(){
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				int sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx1[k];
					int ny = j + dy1[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx2[k];
					int ny = j + dy2[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx3[k];
					int ny = j + dy3[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx4[k];
					int ny = j + dy4[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx5[k];
					int ny = j + dy5[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx6[k];
					int ny = j + dy6[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx7[k];
					int ny = j + dy7[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx8[k];
					int ny = j + dy8[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx9[k];
					int ny = j + dy9[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx10[k];
					int ny = j + dy10[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx11[k];
					int ny = j + dy11[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx12[k];
					int ny = j + dy12[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);

				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx13[k];
					int ny = j + dy13[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx14[k];
					int ny = j + dy14[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx15[k];
					int ny = j + dy15[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx16[k];
					int ny = j + dy16[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx17[k];
					int ny = j + dy17[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx18[k];
					int ny = j + dy18[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
				
				sum = 0;
				for(int k=0; k<4; k++){
					int nx = i + dx19[k];
					int ny = j + dy19[k];
					if(nx<0 || nx>=n || ny<0 || ny>=m) break;
					sum += a[nx][ny];
				}
				ans = Math.max(sum, ans);
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
		
		go();
		System.out.println(ans);
		in.close();
	}
}
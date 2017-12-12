import java.util.*;
public class Baek14499 {
	static int n,m,sx,sy,k;
	static int[] dice = new int[6];
	static int[][] a = new int[21][21];
	static int[] d = new int[1001];
	public static void swap(int a, int b, int c, int d, int e, int f){
		int[] temp = new int[6];
		temp[0] = dice[a];
		temp[1] = dice[b];
		temp[2] = dice[c];
		temp[3] = dice[d];
		temp[4] = dice[e];
		temp[5] = dice[f];
		dice = temp.clone();
	}
	public static void move(int dir){
		int nx = sx, ny = sy;
		switch(dir){
		case 1://©Л
			if(++ny>=m) return;
			swap(2,0,5,3,4,1);
			break;
		case 2://аб
			if(--ny<0) return;
			swap(1,5,0,3,4,2);
			break;
		case 3://╩С
			if(--nx<0) return;
			swap(3,1,2,5,0,4);
			break;
		case 4://го
			if(++nx>=n) return;
			swap(4,1,2,0,5,3);
			break;
		}
		sx=nx; sy=ny;
		if(a[sx][sy]==0) {
			a[sx][sy]=dice[0];
			System.out.println(dice[5]);
			return;
		}
		if(a[sx][sy]!=0){
			dice[0] = a[sx][sy];
			a[sx][sy]=0;
			System.out.println(dice[5]);
			return;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		sx = in.nextInt();
		sy = in.nextInt();
		k = in.nextInt();
		for(int i=0; i<n; i++)
			for(int j=0 ; j<m; j++)
				a[i][j] = in.nextInt();
		for(int i=0; i<k; i++){
			d[i] = in.nextInt();
			move(d[i]);
		}
		in.close();
	}
}
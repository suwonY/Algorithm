package pracAlgorithm;

import java.util.*;
public class Baek14891 {
	static int[][] a = new int[4][8];
	static int k;
	static List<Change> c = new ArrayList<>();
	public static int opp(int dir){
		return dir==1?-1:1;
	}
	public static void changeDir(int dir, int[] a){
		int temp = 0;
		switch(dir){
		case 1://시계방향
			temp = a[7];
			for(int i=7; i>0; i--)
				a[i] = a[i-1];
			a[0] = temp;
			break;
		case -1://반시계방향
			temp = a[0];
			for(int i=0; i<7; i++)
				a[i] = a[i+1];
			a[7] = temp;
			break;
		}
	}
	public static void go(){
		for(Change change : c){
			int num = change.num;
			int dir = change.dir;
			
			int[] d = new int[4];
			d[num] = dir;
			
			int next = num-1;
			while(true){
				if(next<0) break;
				if(a[next][2]==a[next+1][6]) break;
				d[next] = opp(d[next+1]);
				--next;
			}
			next = num+1;
			while(true){
				if(next>3) break;
				if(a[next][6]==a[next-1][2]) break;
				d[next] = opp(d[next-1]);
				++next;
			}
			for(int i=0; i<4; i++){
				if(d[i]==0) continue;
				changeDir(d[i],a[i]);
			}
		}
		int ans = 0;
		if(a[0][0]==1) ans+=1;
		if(a[1][0]==1) ans+=2;
		if(a[2][0]==1) ans+=4;
		if(a[3][0]==1) ans+=8;
		System.out.println(ans);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int i=0; i<4; i++){
			String s = in.next();
			for(int j=0; j<8; j++){
				a[i][j] = s.charAt(j)-48;
			}
		}
		k = in.nextInt();
		for(int i=0; i<k; i++){
			int num = in.nextInt()-1;
			int dir = in.nextInt();
			c.add(new Change(num,dir));
		}
		go();
		in.close();
	}
	static class Change{
		int num, dir;
		public Change(int num, int dir){
			this.num = num;
			this.dir = dir;
		}
	}
}
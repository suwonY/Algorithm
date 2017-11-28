package week_15th_mon;
import java.util.*;
public class kakao03 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[][] a = {{1,1},{2,2},{1,2}};
		int x1 = -1, y1 = -1, xc = 0, yc = 0;
		int x2 = -1, y2 = -1;

		for(int i=0; i<3; i++){
			if(x1==-1 && x2==-1){
				x1 = a[i][0];
				++xc;
				continue;
			}
			else if(x1!=-1 && x1!=a[i][0] && x2==-1)
				x2 = a[i][0];
			else if(x1!=-1 && x1==a[i][0]){
				++xc;
				continue;
			}
		}
		for(int i=0; i<3; i++){
			if(y1==-1 && y2==-1){
				y1 = a[i][1];
				++yc;
				continue;
			}
			else if(y1!=-1 && y1!=a[i][1] && y2==-1){
				y2 = a[i][1];
			}
			else if(y1!=-1 && y1==a[i][1]){
				++yc;
				continue;
			}
		}
		int x = (xc==1?x1:x2);
		int y = (yc==1?y1:y2);
		System.out.println(x + " " + y);
		in.close();
	}
}
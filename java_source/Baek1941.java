import java.util.*;
public class Baek1941 {
	static char[][] a = new char[5][5];
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static boolean[] c = new boolean[1<<25];
	static int ans;
	static void go(int cnt, int s, int check){
		if(cnt==7){
			if(s>3)++ans;
			return;
		}
		for (int i = 0; i < 25; i++) {
			if ((check&(1 << i)) == 0) continue; //���� ���ã��
			int x = i / 5;
			int y = i % 5;

			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
				int num = nx * 5 + ny;
				if (c[check | (1 << num)]) continue; //�̹� �湮�ߴٸ�

				c[check | (1 << num)] = true;
				if(a[nx][ny]=='S') go(cnt + 1, s + 1, check | (1 << (num)));
				if(a[nx][ny]=='Y') go(cnt + 1, s, check | (1 << (num)));
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int i=0; i<5; i++){
			String s = in.next();
			for(int j=0; j<5; j++)
				a[i][j] = s.charAt(j);
		}
		for(int i=0; i<5; i++)
			for(int j=0; j<5; j++){
				c[1 << (i * 5 + j)] = true;
				if(a[i][j]=='S') go(1,1,1 << (i * 5 + j));
				else go(1,0,1 << (i * 5 + j));
			}
		System.out.println(ans);
		in.close();
	}
}
import java.util.*;
public class Baek3197 {
	static int n,m;
	static char[][] a = new char[1501][1501], map = new char[1501][1501];
	static Queue<Point> q = new LinkedList<>();
	static Queue<Point> nq = new LinkedList<>();
	static Point first = null, second=null;
	static boolean[][] c = new boolean[1501][1501];
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static boolean possible(){
		q.add(first);
		c[first.x][first.y]=true;
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			if(x==second.x && y==second.y) return true;
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0||nx>=n||ny<0||ny>=m)continue;
				if(c[nx][ny])continue;
				c[nx][ny]=true;
				if(a[nx][ny]=='X') {
					nq.add(new Point(nx,ny));
					continue;
				}
				q.add(new Point(nx,ny));
			}
		}
		return false;
	}
	static void go(){
		q = nq;
		nq = new LinkedList<>();
		melt();
		int cnt = 1;
		while(!q.isEmpty()){
			Point now = q.remove();
			int x = now.x;
			int y = now.y;
			if(x==second.x && y==second.y) {
				System.out.println(cnt);
				System.exit(0);
			}
			for(int i=0; i<4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0||nx>=n||ny<0||ny>=m)continue;
				if(c[nx][ny])continue;
				c[nx][ny]=true;
				if(a[nx][ny]=='X') {
					nq.add(new Point(nx,ny));
					continue;
				}
				q.add(new Point(nx,ny));
			}
			if(q.isEmpty()){
				q = nq;
				nq = new LinkedList<>();
				melt();
				++cnt;
			}
		}
	}
	static void melt(){
		for(int i=0 ;i<n; i++){
			for(int j=0; j<m; j++){
				if(a[i][j]=='.'|| a[i][j]=='L') {
					map[i][j]=a[i][j];
					continue;
				}
				boolean ok = false;
				for(int k=0; k<4; k++){
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx<0||nx>=n||ny<0||ny>=m||a[nx][ny]=='X'/*||a[nx][ny]=='L'*/)continue;
					ok=true;
					break;
				}
				if(ok)map[i][j]='.';
				if(!ok)map[i][j]='X';
			}
		}
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				a[i][j] = map[i][j];
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		for(int i=0; i<n; i++){
			String s = in.next();
			for(int j=0; j<m; j++){
				a[i][j] = s.charAt(j);
				if(a[i][j]=='L'){
					if(first==null)
						first=new Point(i,j);
					if(first!=null)
						second=new Point(i,j);
				}
			}
		}
		if(possible()) System.out.println(0);
		else go();
		in.close();
	}
	public static class Point{
		int x, y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
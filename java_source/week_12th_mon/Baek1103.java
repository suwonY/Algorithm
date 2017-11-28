package week_12th_mon;

import java.util.*;
public class Baek1103 {
	static int n,m;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
    static int[][] a = new int[51][51];
    static int[][] d = new int[51][51];
    static int[][] c = new int[51][51];
    static boolean cycle=false;
    public static void cycle(int x, int y){
        c[x][y] = -1;
        if(a[x][y]==-1){
            c[x][y]=1;
            return;
        }
        for(int i=0; i<4; i++){
            int nx = x + dx[i]*a[x][y];
            int ny = y + dy[i]*a[x][y];
            if(0<=nx && nx<n && 0<=ny && ny<m){
                if(c[nx][ny]==-1){
                    cycle=true;
                    return;
                }
                else if(c[nx][ny]!=1){
                    cycle(nx,ny);
                }
            }
        }
        c[x][y]=1;
    }
    public static int go(int x, int y){
        if(x<0 || x>=n || y<0 || y>=m || a[x][y]==-1) return 0;
        if(d[x][y]!=-1) return d[x][y];

        for(int i=0; i<4; i++){
            int nx = x + dx[i]*a[x][y];
            int ny = y + dy[i]*a[x][y];
            d[x][y] = Math.max(d[x][y], go(nx,ny)+1);
        }

        return d[x][y];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        for(int i=0; i<n; i++){
            String temp = in.next();
            for(int j=0; j<m; j++){
                d[i][j] = -1;
                if(temp.charAt(j)=='H'){
                    a[i][j] = -1;
                    continue;
                }
                a[i][j] = temp.charAt(j)-48;
            }
        }
        cycle(0,0);
        if(cycle) System.out.println(-1);
        else System.out.println(go(0,0));

        in.close();
    }
}
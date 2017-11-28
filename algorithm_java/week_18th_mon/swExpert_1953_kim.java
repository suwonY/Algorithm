package week_18th_mon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
   int x;
   int y;

   Pair(int x, int y) {
      this.x = x;
      this.y = y;
   }
}

public class swExpert_1953_kim {
   static final int X[] = { 0, 0, -1, 1 };
   static final int Y[] = { -1, 1, 0, 0 };
   static int N;
   static int M;
   static int R;
   static int C;
   static int L;
   static int a[][];
   static int dist[][];
   static boolean check[][];
   static int cnt;
   static int ans;

   public static void main(String args[]) {
      Scanner sc = new Scanner(System.in);
      int T = sc.nextInt();
      for (int TC = 1; TC <= T; TC++) {
         N = sc.nextInt();
         M = sc.nextInt();
         R = sc.nextInt();
         C = sc.nextInt();
         L = sc.nextInt();
         a = new int[N][M];
         dist = new int[N][M];
         check = new boolean[N][M];
         ans = 0;
         cnt = 0;
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
               a[i][j] = sc.nextInt();
            }
         }
         check[R][C] = true;
         dist[R][C] = 1;
         Queue<Pair> q = new LinkedList<Pair>();
         q.add(new Pair(R, C));
         while (!q.isEmpty()) {
            Pair p = q.remove();
            int x = p.x;
            int y = p.y;
            if (dist[x][y] == L) {
               break;
            }
            for (int k = 0; k < 4; k++) {
               int nx = x + X[k];
               int ny = y + Y[k];
               if (0 <= nx && nx < N && 0 <= ny && ny < M && a[nx][ny] != 0 && !check[nx][ny]) {
                  if (a[x][y] == 1) {
                     if (k == 0) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 3 || a[nx][ny] == 4 || a[nx][ny] == 5) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 1) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 3 || a[nx][ny] == 6 || a[nx][ny] == 7) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 2) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 2 || a[nx][ny] == 5 || a[nx][ny] == 6) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 3) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 2 || a[nx][ny] == 4 || a[nx][ny] == 7) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     }
                  } else if (a[x][y] == 2) {
                     if (k == 0) {
                        continue;
                     } else if (k == 1) {
                        continue;
                     } else if (k == 2) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 2 || a[nx][ny] == 5 || a[nx][ny] == 6) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 3) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 2 || a[nx][ny] == 4 || a[nx][ny] == 7) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     }
                  } else if (a[x][y] == 3) {
                     if (k == 0) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 3 || a[nx][ny] == 4 || a[nx][ny] == 5) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 1) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 3 || a[nx][ny] == 6 || a[nx][ny] == 7) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 2) {
                        continue;
                     } else if (k == 3) {
                        continue;
                     }
                  } else if (a[x][y] == 4) {
                     if (k == 0) {
                        continue;
                     } else if (k == 1) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 3 || a[nx][ny] == 6 || a[nx][ny] == 7) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 2) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 2 || a[nx][ny] == 5 || a[nx][ny] == 6) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 3) {
                        continue;
                     }
                  } else if (a[x][y] == 5) {
                     if (k == 0) {
                        continue;
                     } else if (k == 1) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 3 || a[nx][ny] == 6 || a[nx][ny] == 7) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 2) {
                        continue;
                     } else if (k == 3) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 2 || a[nx][ny] == 4 || a[nx][ny] == 7) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     }
                  } else if (a[x][y] == 6) {
                     if (k == 0) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 3 || a[nx][ny] == 4 || a[nx][ny] == 5) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 1) {
                        continue;
                     } else if (k == 2) {
                        continue;
                     } else if (k == 3) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 2 || a[nx][ny] == 4 || a[nx][ny] == 7) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     }
                  } else if (a[x][y] == 7) {
                     if (k == 0) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 3 || a[nx][ny] == 4 || a[nx][ny] == 5) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 1) {
                        continue;
                     } else if (k == 2) {
                        if (a[nx][ny] == 1 || a[nx][ny] == 2 || a[nx][ny] == 5 || a[nx][ny] == 6) {
                           check[nx][ny] = true;
                           q.add(new Pair(nx, ny));
                           dist[nx][ny] = dist[x][y] + 1;
                        } else {
                           continue;
                        }
                     } else if (k == 3) {
                        continue;
                     }
                  }
               }
            }
         }
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
               if (check[i][j]) {
                  ans++;
               }
            }
         }
         System.out.println("#" + TC + " " + ans);
      }
      sc.close();
   }
}
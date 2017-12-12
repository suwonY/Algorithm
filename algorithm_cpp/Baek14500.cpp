#include<iostream>
#include<algorithm>
using namespace std;
int sx[4] = { -1,1,0,0 }, sy[4] = { 0,0,-1,1 };
int dx[4][4] = { { 0,0,-1 },{ 0,-1,1 },{ 0,0,1 },{ 0,-1,1 } };
int dy [4][3]= { { 1,2,1 },{ 1,0,0 },{ 1,2,1 },{ 1,1,1 } };
int n, m, ans;
int a[501][501];
bool c[501][501];
void go(int x, int y, int cnt, int total) {
	if (cnt == 4) {
		ans = max(ans, total);
		return;
	}
	for (int i = 0; i<4; i++) {
		int nx = x + sx[i];
		int ny = y + sy[i];
		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
		if (c[nx][ny]) continue;
		c[nx][ny] = true;
		go(nx, ny, cnt + 1, total + a[nx][ny]);
		c[nx][ny] = false;
	}
}
void other(int x, int y) {
	for (int i = 0; i < 4; i++) {
		int total = a[x][y];
		bool no = false;
		for (int k = 0; k<3; k++) {
			int nx = x + dx[i][k];
			int ny = y + dy[i][k];
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
				no = true;
				break;
			}
			total += a[nx][ny];
		}
		if (no) continue;
		ans = max(ans, total);
	}

}
int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			cin >> a[i][j];
	for(int i=0; i<n; i++)
		for (int j = 0; j < m; j++) {
			c[i][j] = true;
			go(i, j, 1, a[i][j]);
			other(i, j);
			c[i][j] = false;
		}
	cout << ans << endl;
	return 0;
}
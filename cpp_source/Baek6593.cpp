#include<iostream>
#include<string>
#include<queue>
#include<memory.h>
using namespace std;
class Point {
public:
	int x, y, z;
	Point(){
		x = y = z = -1;
	}
	Point(int z, int x, int y) {
		this->x = x;
		this->y = y;
		this->z = z;
	}
};
int L, R, C;
string s;
char a[31][31][31];
int d[31][31][31];
int dx[6] = { -1,1,0,0,0,0 }, dy[6] = { 0,0,-1,1,0,0 }, dz[6] = { 0,0,0,0,-1,1 };
Point start, finish;
void go() {
	queue<Point> q;
	q.push(start);

	while (!q.empty()) {
		Point now = q.front(); q.pop();
		int x = now.x;
		int y = now.y;
		int z = now.z;

		for (int i = 0; i < 6; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int nz = z + dz[i];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C || nz < 0 || nz >= L) continue;
			if (a[nz][nx][ny] == '#' || d[nz][nx][ny] != 0) continue;

			q.push(Point(nz, nx, ny));
			d[nz][nx][ny] = d[z][x][y] + 1;
		}
	}
}
int main() {
	while (true) {
		cin >> L >> R >> C;
		if (L == 0 && R == 0 && C == 0) break;
		memset(a, ' ', sizeof(a));
		memset(d, 0, sizeof(d));
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < R; j++) {
				cin >> s;
				for (int k = 0; k < C; k++) {
					a[i][j][k] = s[k];
					if (a[i][j][k] == 'S') start = Point(i, j, k);
					if (a[i][j][k] == 'E') finish = Point(i, j, k);
				}
			}
		}

		go();
		int ans = d[finish.z][finish.x][finish.y];
		if (ans == 0) cout << "Trapped!" << endl;
		else cout << "Escaped in " << ans << " minute(s)." << endl;

	}

	return 0;
}
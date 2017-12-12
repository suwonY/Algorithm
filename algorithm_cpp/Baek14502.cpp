#include<iostream>
#include<algorithm>
#include<queue>
using namespace std;
class Point {
public:
	int x, y;
	Point() {
		x = y = -1;
	}
	Point(int x, int y) {
		this->x = x;
		this->y = y;
	}
};
int n, m, a[9][9], map[9][9], ans;
int dx[4] = { -1,1,0,0 }, dy[4] = { 0,0,-1,1 };
void cloneMap() {
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			map[i][j] = a[i][j];
}
void virus() {
	int temp[9][9] = { 0, };
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			temp[i][j] = map[i][j];

	queue<Point> q;
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < m; j++) 
			if (temp[i][j] == 2) q.push(Point(i, j));

	while (!q.empty()) {
		int x = q.front().x;
		int y = q.front().y; q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if (temp[nx][ny] == 2 || temp[nx][ny] == 1) continue;
			temp[nx][ny] = 2;
			q.push(Point(nx, ny));
		}
	}

	int cnt = 0;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			if (temp[i][j] == 0) ++cnt;

	ans = max(ans, cnt);
}
void go(int cnt) {
	if (cnt == 3) {
		virus();
		return;
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (map[i][j] == 1 || map[i][j] == 2) continue;
			map[i][j] = 1;
			go(cnt + 1);
			map[i][j] = 0;
		}
	}
}
int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			cin >> a[i][j];
	
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) {
			if (a[i][j] == 2 || a[i][j] == 1) continue;
			cloneMap();
			map[i][j] = 1;
			go(1);
			map[i][j] = 0;
		}
	cout << ans << endl;
	return 0;
}
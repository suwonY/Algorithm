#include<iostream>
#include<algorithm>
#include<queue>
using namespace std;
class Point {
public:
	int x, y, cnt;
	Point() {
		x = y = cnt = -1;
	}
	Point(int x, int y) {
		this->x = x;
		this->y = y;
		cnt = 0;
	}
	Point(int x, int y, int cnt) {
		this->x = x;
		this->y = y;
		this->cnt = cnt;
	}
};
const int INF = 987654321;
int n, m;
int ans = INF;
//0 ¸Ç¶¥ 1 ¹æ¼® 2 µ¹¸æÀÌ 3 ½ÃÀÛ 4 ³¡
Point start, fin;
int a[31][31];
bool c[31][31];
int dx[8] = { -2,-2 ,-1,1,2,2,1,-1 }, dy[8] = { -1,1,2,2,1,-1,-2,-2 };
int main() {
	cin >> n >> m;
	for(int i=0; i<n; i++)
		for (int j = 0; j < m; j++) {
			cin >> a[i][j];
			if (a[i][j] == 3)
				start = Point(i, j);
			if (a[i][j] == 4)
				fin = Point(i, j);
		}

	queue<Point> q;
	q.push(start);
	c[start.x][start.y] = true;
	bool flag = false;
	while (!q.empty()) {
		int size = q.size();
		
		for (int k = 0; k < size; k++) {
			int x = q.front().x;
			int y = q.front().y;
			int cnt = q.front().cnt;
			q.pop();

			if (x == fin.x && y == fin.y) {
				if (ans == INF) ans = 1;
				else ++ans;
				flag = true;
			}

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (a[nx][ny] == 2) continue;
			}

		}
		if (flag) break;
	}

	return 0;
}

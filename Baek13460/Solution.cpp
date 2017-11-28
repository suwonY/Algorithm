#include<iostream>
#include<string>
#include<algorithm>
using namespace std;
int INF = 987654321;
string s;
int n, m, ans = INF;
int dx[4] = { -1,1,0,0 }, dy[4] = { 0,0,-1,1 };
class Point {
public:
	int x, y;
	Point(){
		x = y = -1;
	}
	Point(int x, int y) {
		this->x = x;
		this->y = y;
	}
};
int reverse(int dir) {
	if (dir == 0) return 1;
	if (dir == 1) return 0;
	if (dir == 2) return 3;
	else return 2;
}
void go(int dir, int cnt, char map[11][11], bool r, bool b, Point R, Point B) {
	++cnt;
	char a[11][11];
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			a[i][j] = map[i][j];
	int first = 0; //0�� ������ ���� 1�� �Ķ��� ����
	switch (dir) {
	case 0://x�� ������
		if (R.x>B.x) first = 1;
		break;
	case 1://x�� ū��
		if (R.x<B.x) first = 1;
		break;
	case 2://y�� ������
		if (R.y>B.y) first = 1;
		break;
	case 3://y�� ū��
		if (R.y<B.y) first = 1;
		break;
	}
	if (first == 0) {//������ ������ ��
		while (true) {//���� �����̱�
			int nx = R.x + dx[dir];
			int ny = R.y + dy[dir];
			if (a[nx][ny] == '#') break;
			if (a[nx][ny] == 'O') {
				a[R.x][R.y] = '.';
				R = Point(-1,-1);
				r = true;
				break;
			}
			a[R.x][R.y] = '.';
			a[nx][ny] = 'R';
			R = Point(nx, ny);
		}
		while (true) {//�Ķ� �����̱�
			int nx = B.x + dx[dir];
			int ny = B.y + dy[dir];
			if (a[nx][ny] == '#' || a[nx][ny] == 'R') break;
			if (a[nx][ny] == 'O') {
				a[B.x][B.y] = '.';
				B = Point(-1,-1);
				b = true;
				break;
			}
			a[B.x][B.y] = '.';
			a[nx][ny] = 'B';
			B = Point(nx, ny);
		}
	}
	if (first == 1) {//�Ķ��� ������ ��
		while (true) {//�Ķ� �����̱�
			int nx = B.x + dx[dir];
			int ny = B.y + dy[dir];
			if (a[nx][ny] == '#') break;
			if (a[nx][ny] == 'O') {
				a[B.x][B.y] = '.';
				B = Point(-1,-1);
				b = true;
				break;
			}
			a[B.x][B.y] = '.';
			a[nx][ny] = 'B';
			B = Point(nx, ny);
		}
		while (true) {//���� �����̱�
			int nx = R.x + dx[dir];
			int ny = R.y + dy[dir];
			if (a[nx][ny] == '#' || a[nx][ny] == 'B') break;
			if (a[nx][ny] == 'O') {
				a[R.x][R.y] = '.';
				R = Point(-1,-1);
				r = true;
				break;
			}
			a[R.x][R.y] = '.';
			a[nx][ny] = 'R';
			R = Point(nx, ny);
		}
	}

	if (cnt>10) return;
	if (r && b) return;
	if (!r && b) return;
	if (r && !b) {
		ans = min(ans, cnt);
		return;
	}
	for (int i = 0; i<4; i++) {
		if (i == dir || i == reverse(dir)) continue;
		go(i, cnt, a, r, b, R, B);
	}
}
int main() {
	cin >> n >> m;
	char a[11][11];
	Point R, B;
	for (int i = 0; i < n; i++) {
		cin >> s;
		for (int j = 0; j < m; j++) {
			a[i][j] = s[j];
			if (a[i][j] == 'R') R = Point(i, j);
			if (a[i][j] == 'B') B = Point(i, j);
		}
	}
	for (int i = 0; i < 4; i++) go(i, 0, a, false, false, R, B);
	cout << (ans == INF ? -1 : ans) << endl;
	return 0;
}
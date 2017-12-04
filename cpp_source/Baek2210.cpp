#include<iostream>
#include<set>
using namespace std;
set<int> ans;
int a[5][5];
int dx[4] = { -1,1,0,0 }, dy[4] = { 0,0,-1,1 };
void go(int cnt, int x, int y, int num) {
	if (cnt == 6) {
		ans.insert(num);
		return;
	}
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
		go(cnt + 1, nx, ny, num * 10 + a[nx][ny]);
	}
}
int main() {
	for (int i = 0; i < 5; i++)
		for (int j = 0; j < 5; j++)
			cin >> a[i][j];

	for (int i = 0; i < 5; i++)
		for (int j = 0; j < 5; j++)
			go(1, i, j, a[i][j]);

	cout << ans.size() << endl;

	return 0;
}
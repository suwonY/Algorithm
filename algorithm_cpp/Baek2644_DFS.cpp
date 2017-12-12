#include<iostream>
using namespace std;
int n, m, start, finish, ans;
int a[101][101];
void go(int end, int now, int before, int cnt) {
	if (end == now) {
		ans = cnt;
		return;
	}
	for (int i = 1; i <= n; i++) {
		if (a[now][i] == 0) continue;
		if (i == before) continue;
		go(end, i, now, cnt + 1);
	}
}
int main() {
	cin >> n >> start >> finish >> m;
	for (int i = 0; i < m; i++) {
		int x = 0, y = 0;
		cin >> x >> y;
		a[x][y] = a[y][x] = 1;
	}
	go(finish, start, 0, 0);
	cout << (ans != 0 ? ans : -1) << endl;
	return 0;
}
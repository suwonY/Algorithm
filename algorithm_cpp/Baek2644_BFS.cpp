#include<iostream>
#include<queue>
using namespace std;
int n, start, finish, m, a[101][101], d[101];
int main() {
	cin >> n >> start >> finish >> m;
	for (int i = 0; i < m; i++) {
		int x = 0, y = 0;
		cin >> x >> y;
		a[x][y] = a[y][x] = 1;
	}

	queue<int> q;
	q.push(start);
	while (!q.empty()) {
		int now = q.front(); q.pop();
		for (int i = 1; i <= n; i++) {
			if (a[now][i] == 0 || d[i] != 0) continue;
			d[i] = d[now] + 1;
			q.push(i);
		}
	}
	cout << (d[finish] == 0 ? -1 : d[finish]) << endl;
	return 0;
}
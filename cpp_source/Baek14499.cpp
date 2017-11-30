#include<iostream>
using namespace std;
int n, m, sx, sy, k, dice[6], a[21][21], d[1001];
void swap(int a, int b, int c, int d, int e, int f) {
	int temp[6] = { 0, };
	temp[0] = dice[a];
	temp[1] = dice[b];
	temp[2] = dice[c];
	temp[3] = dice[d];
	temp[4] = dice[e];
	temp[5] = dice[f];
	for (int i = 0; i < 6; i++)
		dice[i] = temp[i];
}
void move(int dir) {
	int nx = sx, ny = sy;
	switch (dir) {
	case 1://©Л
		if (++ny >= m) return;
		swap(2, 0, 5, 3, 4, 1);
		break;
	case 2://аб
		if (--ny<0) return;
		swap(1, 5, 0, 3, 4, 2);
		break;
	case 3://╩С
		if (--nx<0) return;
		swap(3, 1, 2, 5, 0, 4);
		break;
	case 4://го
		if (++nx >= n) return;
		swap(4, 1, 2, 0, 5, 3);
		break;
	}
	sx = nx; sy = ny;
	if (a[sx][sy] == 0) {
		a[sx][sy] = dice[0];
		cout << dice[5] << endl;
		return;
	}
	if (a[sx][sy] != 0) {
		dice[0] = a[sx][sy];
		a[sx][sy] = 0;
		cout << dice[5] << endl;
		return;
	}
}
int main() {
	cin >> n >> m >> sx >> sy >> k;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			cin >> a[i][j];

	for (int i = 0; i < k; i++) {
		cin >> d[i];
		move(d[i]);
	}

	return 0;
}
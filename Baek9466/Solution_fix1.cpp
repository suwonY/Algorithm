#include<iostream>
#include<algorithm>
#include<memory.h>
#include<vector>
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
int MAX = 102;
int n;
int d[102][102];
vector<Point> a;
int getDist(Point first, Point second) {
	return abs(first.x - second.x) + abs(first.y - second.y);
}
void go() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < n; k++) {
				if (d[i][j] > d[i][k] + d[k][j])
					d[i][j] = d[i][k] + d[k][j];
			}
		}
	}
}
int main() {
	int t = 0;
	cin >> t;
	for (int k = 0; k < t; k++) {
		cin >> n;
		n += 2;
		a.clear();
		a.resize(n);
		memset(d, 0, sizeof(d));

		for (int i = 0; i < n; i++) {
			int x = 0, y = 0;
			cin >> x >> y;
			a.push_back(Point(x, y));
		}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i != j) d[i][j] = MAX;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) continue;
				Point now = a[i];
				Point next = a[j];
				if (getDist(now, next) <= 1000) d[i][j] = 1;
			}
		}
		go();

		n -= 2;
		if (0 < d[0][n + 1] && d[0][n + 1] < MAX) 
			cout << "happy" << endl;
		else
			cout << "sad" << endl;
	}


	return 0;
}
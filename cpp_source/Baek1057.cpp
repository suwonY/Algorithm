#include<iostream>
#include<vector>
#include<deque>
using namespace std;
int n, kim, im, ans;
deque<int> q;
bool fin;
void go() {
	for (int i = 1; i <= n; i++)
		q.push_back(i);
	while (!fin) {
		int size = q.size();
		if (size % 2 == 0) {
			for (int i = 0; i < size; i+=2) {
				int x = q.front(); q.pop_front();
				int y = q.front(); q.pop_front();
				if (x == kim && y == im) {
					fin = true;
					break;
				}
				if (x == im && y == kim) {
					fin = true;
					break;
				}
				if (x == kim || x == im) {
					q.push_back(x);
					continue;
				}
				if (y == kim || y == im) {
					q.push_back(y);
					continue;
				}
				q.push_back(x);
			}
		}
		if (size % 2 != 0) {
			int temp = q.back();
			q.pop_back();
			for (int i = 0; i < size - 1; i+=2) {
				int x = q.front(); q.pop_front();
				int y = q.front(); q.pop_front();
				if (x == kim && y == im) {
					fin = true;
					break;
				}
				if (x == im && y == kim) {
					fin = true;
					break;
				}
				if (x == kim || x == im) {
					q.push_back(x);
					continue;
				}
				if (y == kim || y == im) {
					q.push_back(y);
					continue;
				}
				q.push_back(x);
			}
			q.push_back(temp);
			
		}
		++ans;
	}
}
int main() {
	cin >> n >> kim >> im;
	go();
	cout << (ans == 0 ? -1 : ans) << endl;
	return 0;
}
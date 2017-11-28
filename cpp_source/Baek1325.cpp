#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

vector<vector<int>> a;
bool c[100001];
int n, m, ans;
vector<int> out;
int go(int start, int next) {
	int cnt = 1;
	for (vector<int>::iterator i = a[next].begin(); i != a[next].end(); i++) {
		if (c[*i]) continue;
		c[*i] = true;
		cnt += go(start, *i);
	}
	return cnt;
}
int main() {
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		a.resize(100001);
		int x, y;
		cin >> x >> y;
		a[y].push_back(x);
	}
	for (int i = 1; i <= n; i++) {
		memset(c, false, sizeof(c));
		c[i] = true;
		int temp = go(i, i);
		if (temp > ans) {
			out.clear();
			out.push_back(i);
			ans = temp;
			continue;
		}
		if (temp == ans)
			out.push_back(i);
	}
	for (vector<int>::iterator i = out.begin(); i != out.end(); i++)
		cout << *i << " ";
	return 0;
}
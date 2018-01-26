#include<iostream>
#include<algorithm>
#include<stack>
using namespace std;
stack<int> a[7];
int n, m, ans;
int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		int x = 0, y = 0;
		cin >> x >> y;
		if (a[x].size() == 0) {
			++ans;
			a[x].push(y);
			continue;
		}
		while (a[x].top() > y) {
			++ans;
			a[x].pop();
			if (a[x].size() == 0) break;
		}
		if (a[x].size() != 0 && a[x].top() == y) continue;
		++ans;
		a[x].push(y);
	}
	cout << ans << endl;
	return 0;
}
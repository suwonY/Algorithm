#include<iostream>
#include<algorithm>
using namespace std;
int a, b, c, n, ans;
int d[101];
int main() {
	cin >> a >> b >> c;
	for (int i = 0; i < 3; i++ ) {
		int s = 0, f = 0;
		cin >> s >> f;
		for (int j = s; j < f; j++)
			++d[j];
		n = max(n, f);
	}
	for (int i = 1; i <= n; i++) {
		if (d[i] == 1) ans += a;
		if (d[i] == 2) ans += (b * 2);
		if (d[i] == 3) ans += (c * 3);
	}
	cout << ans << endl;

	return 0;
}
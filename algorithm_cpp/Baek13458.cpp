#include<iostream>
using namespace std;
int n, a[1000001], b, c;
long long ans;
int main() {
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> a[i];
	cin >> b >> c;
	for (int i = 0; i < n; i++) {
		++ans;
		a[i] -= b;
		if (a[i] <= 0) continue;
		ans += (a[i] % c == 0 ? a[i] / c : a[i] / c + 1);
	}
	cout << ans << endl;
	return 0;
}
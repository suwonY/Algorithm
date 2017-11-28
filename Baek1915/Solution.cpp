#include<iostream>
#include<algorithm>
#include<string>
using namespace std;
int n, m, a[1001][1001], d[1001][1001], ans;
string s;
int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> s;
		for (int j = 0; j < m; j++) {
			a[i][j] = s[j] - 48;
			if (a[i][j] == 1) {
				d[i][j] = 1;
				ans = 1;
			}
		}
	}
	for (int i = 1; i < n; i++) {
		for (int j = 1; j < m; j++) {
			if (a[i - 1][j] == 1 && a[i - 1][j - 1] == 1 && a[i][j - 1] == 1) {
				d[i][j] = min(d[i - 1][j - 1], d[i - 1][j]);
				d[i][j] = min(d[i][j], d[i][j - 1]) + 1;
			}
			ans = max(ans, d[i][j]);
		}
	}
	cout << ans*ans << endl;
}
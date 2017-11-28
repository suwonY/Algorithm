#include<iostream>
#include<algorithm>
#include<string>
#include<vector>
using namespace std;
int n, ans = 987654321;
int a[21][21];
void go(string s, int start, int link) {
	if (s.size() == n) {
		vector<int> team1,team2;
		for (int i = 0; i < s.size(); i++) {
			if (s[i] == '1') team1.push_back(i);
			else team2.push_back(i);
		}
		int total1 = 0, total2 = 0;
		for(int i=0; i<n/2; i++)
			for (int j = 0; j < n / 2; j++) {
				if (i == j) continue;
				total1 += a[team1[i]][team1[j]];
				total2 += a[team2[i]][team2[j]];
			}
		ans = min(ans, abs(total1 - total2));
		return;
	}
	if (start < (n / 2)) go(s+"1", start + 1, link);
	if (link < (n / 2)) go(s+"2", start, link + 1);
}
int main() {
	cin >> n;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			cin >> a[i][j];

	go("", 0, 0);
	cout << ans << endl;
	return 0;
}
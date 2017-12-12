#include<iostream>
#include<algorithm>
#include<string>
#include<vector>
using namespace std;
vector<string> a;
int n, k, ans;
void go(int cnt, int learn, int index) {
	if (index > 26) return;
	if (cnt == k) {
		int total = 0;
		for (int i = 0; i < n; i++) {
			string s = a[i];
			bool ok = true;
			for (int j = 0; j < s.length(); j++) {
				if (((1 << (s[j] - 'a')) & learn) > 0) continue;
				ok = false;
				break;
			}
			if (ok) ++total;
		}
		ans = max(ans, total);
		return;
	}
	if (index == 0 || index == 2 || index == 8 || index == 13 || index == 19)
		go(cnt, learn, index + 1);
	else if (index != 0 && index != 2 && index != 8 && index != 13 && index != 19) {
		if (cnt <= k)
			go(cnt + 1, (learn | (1 << index)), index + 1);
		go(cnt, learn, index + 1);
	}
}
int main() {
	cin >> n >> k;
	k -= 5;
	if (k < 0) {
		cout << 0 << endl;
		return 0;
	}
	for (int i = 0; i < n; i++) {
		string s = "";
		cin >> s;
		a.push_back(s);
	}
	int temp = 0;
	temp |= (1 << ('a' - 'a'));
	temp |= (1 << ('c' - 'a'));
	temp |= (1 << ('i' - 'a'));
	temp |= (1 << ('n' - 'a'));
	temp |= (1 << ('t' - 'a'));
	go(0, temp, 0);
	cout << ans << endl;
	return 0;
}
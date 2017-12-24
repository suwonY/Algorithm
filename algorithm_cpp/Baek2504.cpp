#include<iostream>
#include<stack>
#include<string>
using namespace std;
int main() {
	string n;
	cin >> n;
	stack<char> s;
	int ans = 0, k = 0, l = 0, mul = 1;
	for (int i = 0; i<n.length(); i++) {
		char now = n[i];
		switch (now) {
		case '(':
			++k;
			s.push(now);
			mul *= 2;
			if (i + 1<n.length() && n[i+1] == ')')
				ans += mul;
			break;
		case '[':
			++l;
			s.push(now);
			mul *= 3;
			if (i + 1<n.length() && n[i + 1] == ']')
				ans += mul;
			break;
		case ')':
			--k;
			s.pop();
			mul /= 2;
			break;
		case ']':
			--l;
			s.pop();
			mul /= 3;
			break;
		}
	}
	if (!s.empty() || l != 0 || k != 0) cout << 0 << endl;
	else cout << ans << endl;
	return 0;
}
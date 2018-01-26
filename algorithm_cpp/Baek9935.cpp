#include<iostream>
#include<string>
using namespace std;
char ans[1000001];
string a, bomb;
int idx, len;
int main() {
	cin >> a >> bomb;
	len = bomb.length();
	for (int i = 0; i < a.length(); i++) {
		ans[idx++] = a[i];
		if (ans[idx - 1] == bomb[len - 1]) {
			if (idx - len < 0) continue;
			bool c = false;
			for (int j = 0; j < len; j++) {
				if (ans[idx - 1 - j] != bomb[len - 1 - j]) {
					c = true;
					break;
				}
			}
			if (!c) idx -= len;
		}
	}
	ans[idx] = '\0';
	idx==0? printf("FRULA"): printf("%s", ans);
}

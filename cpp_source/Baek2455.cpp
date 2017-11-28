#include<iostream>
#include<algorithm>
using namespace std;
int up[4], down[4], ans;
int main() {
	for (int i = 0; i < 4; i++) 
		cin >> down[i] >> up[i];
	ans = up[0];
	for (int i = 1; i < 4; i++) {
		int temp = ans;
		temp -= down[i];
		temp += up[i];
		ans = max(ans, temp);
	}
	cout << ans << endl;
	return 0;
}
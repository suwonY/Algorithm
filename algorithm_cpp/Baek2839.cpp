#include<iostream>
using namespace std;
int n, ans;
int main() {
	scanf("%d", &n);
	ans = 0;
	while (true) {
		if (n % 5 == 0) {
			ans += (n / 5);
			break;
		}
		n -= 3;
		++ans;
		if (n < 0) break;
	}
	if (n < 0) printf("-1\n");
	else printf("%d\n", ans);
	return 0;
}
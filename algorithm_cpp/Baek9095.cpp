#include<iostream>
#include<algorithm>
using namespace std;
int t, n;
int d[11];
int main() {
	d[0] = 1;
	for (int i = 1; i <= 10; i++) {
		if (i - 1 >= 0) d[i] += d[i - 1];
		if (i - 2 >= 0) d[i] += d[i - 2];
		if (i - 3 >= 0) d[i] += d[i - 3];
	}
	cin >> t;
	for (int i = 0; i < t; i++) {
		cin >> n;
		cout << d[n] << endl;
	}
	return 0;
}
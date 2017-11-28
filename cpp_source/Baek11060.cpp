#include<iostream>
#include<memory.h>
#include<algorithm>
using namespace std;
int INF = 987654321;
int n, ans = INF;
int a[1001], d[1001];
int main() {
	cin >> n;
	for (int i = 1; i <= n; i++)
		cin >> a[i];
	for (int i = 1; i <= n; i++)
		d[i] = INF;
	d[1] = 0;
	for (int i = 1; i <= n; i++) 
		for (int j = i + 1; j <= i + a[i]; j++) 
			d[j] = min(d[j], d[i] + 1);
	cout << (d[n] == INF ? -1 : d[n]) << endl;
	return 0;
}
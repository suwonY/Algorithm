#include<iostream>
#pragma warning(disable:4996)
using namespace std;
int t, n;
int main() {
	scanf("%d", &t);
	for (int tc = 0; tc < t; tc++) {
		char a[21] = { '\0', };
		scanf("%d %s", &n ,a);
		cout << a << " " << endl;
		for (int i = 0; a[i] != '\0'; i++)
			for (int j = 0; j < n; j++)
				printf("%c", a[i]);
		printf("\n");
	}
}
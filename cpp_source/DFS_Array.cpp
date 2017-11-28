#include<iostream>
using namespace std;
int v, e;
int a[10][10];
bool c[10];
void dfs(int start) {
	c[start] = true;
	cout << start << " ";
	
	for (int i = 1; i <= v; i++)
		if (a[start][i] == 1 && !c[i]) dfs(i);
}
int main() {
	cin >> v >> e;
	int from = 0, to = 0;
	for (int i = 0; i < e; i++) {
		cin >> from >> to;
		a[to][from] = a[from][to] = 1;
	}
	dfs(1);
	return 0;
}
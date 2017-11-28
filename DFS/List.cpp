#include<iostream>
#include<vector>
using namespace std;
int v, e;
bool c[5];
vector<vector<int>> a;
void dfs(int vertex) {
	c[vertex] = true;
	cout << vertex << " ";
	for (vector<int>::iterator i = a[vertex].begin(); i != a[vertex].end(); i++)
		if (!c[*i]) dfs(*i);
}
int main() {
	cin >> v >> e;
	int to = 0, from = 0;

	a.resize(v+1);
	for (int i = 1; i <= v; i++)
		a[i].resize(v);
	
	for (int i = 0; i < e; i++) {
		cin >> to >> from;
		a[to].push_back(from);
		a[from].push_back(to);
	}
	
	dfs(1);
	return 0;
}
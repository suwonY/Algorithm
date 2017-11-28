#include<iostream>
#include<deque>
using namespace std;
int n;
deque<int> q;
int main() {
	cin >> n;
	int temp = 0;
	for (int i = 1; i <= n; i++)
		q.push_back(i);

	for (int i = 0; i < n - 1; i++) {
		cout << q.front() << " ";
		q.pop_front();
		q.push_back(q.front());
		q.pop_front();
	}
	cout << q.front() << endl;
	return 0;
}
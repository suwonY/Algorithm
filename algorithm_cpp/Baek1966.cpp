#include<iostream>
#include<deque>
#include<algorithm>
#include<memory>
#include<vector>
using namespace std;
class Node {
public:
	int num, val;
	Node() {
		num = val = -1;
	}
	Node(int num, int val) {
		this->num = num;
		this->val = val;
	}
};
int n, m, t, ans;
int main() {
	cin >> t;
	for (int k = 0; k < t; k++) {
		cin >> n >> m;
		deque<Node> q;
		vector<int> a;
		for (int i = 0; i < n; i++) {
			int temp = 0;
			cin >> temp;
			q.push_back(Node(i, temp));
			a.push_back(temp);
		}
		sort(a.begin(), a.end());
		int temp = -1; //나와야할 가중치
		ans = 1;
		while (true) {
			Node now = q.front();
			int num = now.num;
			int val = now.val;

			if (temp == -1) {
				temp = a.back();
				a.pop_back();
			}

			if (val == temp) { //해당 가중치가 나왔다면
				if (num == m) break;
				q.pop_front();
				++ans;
				temp = a.back();
				a.pop_back();
				continue;
			}

			if (val != temp) {
				Node temp = q.front();
				q.pop_front();
				q.push_back(temp);
			}
		}
		cout << ans << endl;

	}

	return 0;
}
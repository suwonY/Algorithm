#include<iostream>
#include<vector>
#include<string>
using namespace std;
class Change {
public:
	int num, dir;
	Change() {
		num = dir = -1;
	}
	Change(int num, int dir) {
		this->num = num;
		this->dir = dir;
	}
};
int a[4][8];
int k;
string s;
vector<Change> change;
int opp(int dir) {
	return dir == 1 ? -1 : 1;
}
void turn(int dir, int* arr) {
	int temp = 0;
	switch (dir) {
	case 1:
		temp = arr[7];
		for (int i = 7; i > 0; i--)
			arr[i] = arr[i - 1];
		arr[0] = temp;
		break;
	case -1:
		temp = arr[0];
		for (int i = 0; i < 7; i++)
			arr[i] = arr[i + 1];
		arr[7] = temp;
		break;
	}
}
void go() {
	for (int t = 0; t < change.size(); t++) {
		int num = change[t].num;
		int dir = change[t].dir;

		int c[4] = { 0,0,0,0 };
		c[num] = dir;

		int next = num - 1;
		while (true) {
			if (next<0) break;
			if (a[next][2] == a[next + 1][6]) break;
			c[next] = opp(c[next + 1]);
			--next;
		}
		next = num + 1;
		while (true) {
			if (next>3) break;
			if (a[next][6] == a[next - 1][2]) break;
			c[next] = opp(c[next - 1]);
			++next;
		}

		for (int i = 0; i < 4; i++) 
			turn(c[i], a[i]);
	}
	int ans = 0;
	if (a[0][0] == 1) ++ans;
	if (a[1][0] == 1) ans += 2;
	if (a[2][0] == 1) ans += 4;
	if (a[3][0] == 1) ans += 8;
	cout << ans << endl;
}
int main() {
	for (int i = 0; i < 4; i++) {
		cin >> s;
		for (int j = 0; j < 8; j++)
			a[i][j] = s[j] - 48;
	}
	cin >> k;
	int num = 0, dir = 0;
	for (int i = 0; i < k; i++) {
		cin >> num >> dir;
		change.push_back(Change(num - 1, dir));
	}
	go();
	return 0;
}
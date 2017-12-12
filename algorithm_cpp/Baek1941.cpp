#include<iostream>
using namespace std;
char a[5][5];
int dx[4] = { -1,1,0,0 }, dy[4] = { 0,0,-1,1 }, ans;
bool c[1 << 25];
void go(int cnt, int s, int check) {
	if (cnt == 7) {
		if (s > 3) 
			++ans;
		return;
	}
	for (int i = 0; i < 25; i++) {
		if ((check&(1 << i))==0) continue; //현재 경로찾기
		int x = i / 5;
		int y = i % 5;

		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
			int num = nx * 5 + ny;
			if (c[check | (1 << num)]) continue; //이미 방문했다면

			c[check | (1 << num)] = true;
			if(a[nx][ny]=='S') go(cnt + 1, s + 1, check | (1 << (num)));
			if(a[nx][ny]=='Y') go(cnt + 1, s, check | (1 << (num)));
		}
	}
}
int main() {
	for (int i = 0; i < 5; i++)
		cin >> a[i];
	for (int i = 0; i < 5; i++)
		for (int j = 0; j < 5; j++) {
			c[1 << (i * 5 + j)] = true;
			go(1, a[i][j] == 'S', 1 << (i * 5 + j));
		}
	cout << ans << endl;
	return 0;
}
//#include<iostream>
//#include<vector>
//#include<memory.h>
//#include<algorithm>
//using namespace std;
//class Point {
//public:
//	int x, y, cnt;
//	Point() {
//		x = y = cnt = 0;
//	}
//	Point(int x, int y) {
//		this->x = x;
//		this->y = y;
//		cnt = -1;
//	}
//	Point(int x, int y, int cnt) {
//		this->x = x;
//		this->y = y;
//		this->cnt = cnt;
//	}
//};
//int t, n;
//Point home, fin;
//bool ok;
//bool c[51];
//vector<Point> market;
//int getDis(Point first, Point second) {
//	return abs(first.x - second.x) + abs(first.y - second.y);
//}
//void go(Point now, int beer) {
//	if (ok) return;//이미 갈 수 있는 상황이라면
//	if (getDis(now, fin) <= (beer * 50)) { //편의점까지 갈 수 있는 상황이면 끝내기
//		ok = true;
//		return;
//	}
//	//편의점을 들렀다 가기
//	for (vector<Point>::iterator i = market.begin(); i != market.end(); i++) {
//		int dist = getDis(now, *i);	//현재 위치부터 편의점까지의 거리
//		if (dist > (beer * 50)) continue; //편의점까지 갈 맥주가 없을 경우
//		if (c[(*i).cnt]) continue;//방문했던 편의점이면
//		c[(*i).cnt] = true;
//		go(*i, 20);
//		c[(*i).cnt] = false;
//	}
//}
//int main() {
//	cin >> t;
//	for (int k = 0; k < t; k++) {
//		cin >> n;
//		market.clear();
//		memset(c, false, sizeof(c));
//		market.resize(n);
//		ok = false;
//		int x = 0, y = 0;
//		cin >> x >> y;
//		home = Point(x, y);
//		for (int i = 0; i < n; i++) {
//			cin >> x >> y;
//			market.push_back(Point(x, y, i));
//		}
//		cin >> x >> y;
//		fin = Point(x, y);
//		go(home, 20);
//		cout << (ok ? "happy" : "sad") << endl;
//	}
//	return 0;
//}
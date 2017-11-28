package week_18th_thu;

import java.util.*;
public class swExpert2477_fix2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int tc=1; tc<=t; tc++){
			int n = in.nextInt();
			int m = in.nextInt();
			int k = in.nextInt();
			int endA = in.nextInt();
			int endB = in.nextInt();
			int[] a = new int[n+1];
			int[] b = new int[m+1];
			int[] time = new int[k+1];
			int[] c1 = new int[n+1];//접수창구 상황
			int[] c2 = new int[m+1];//정비창구 상황
			for(int i=1; i<=n; i++)
				a[i] = in.nextInt();
			for(int i=1; i<=m; i++)
				b[i] = in.nextInt();
			for(int i=1; i<=k; i++)
				time[i] = in.nextInt();
			Arrays.sort(time);
			
			Person[] customer = new Person[k+1];
			for(int i=1; i<=k; i++)
				customer[i]=new Person();
			
			int now = time[0]; int cnt = 0;
			while(true){
				System.out.println("현재 시간 : " + now);
				//손님 입장 - 0
				for(int i=1; i<=k; i++){
					if(time[i]>now) break;
					if(now==time[i] && customer[i].situation==0){
//							System.out.println(i+"번째 손님 입장");
						customer[i].setStart(i);
						customer[i].situation=1;
					}
				}
				
				//0입장 1접수대기 2접수중 3접수끝 ->정비대기 4정비중 5끝
				//접수창구 업무 끝난 친구들 확인해주기(접수 업무가 끝난사람은 3으로 바꾸고 정비대기로 보내기)
				for(int i=1; i<=k; i++){
					if(now==time[0]) break;
					if(customer[i].situation==2){//접수중인 애들중에서
						if(c1[customer[i].anum]==0){//해당 접수창고에서의 업무가 끝났으면
//							System.out.println(customer[i].start+"번째 고객  접수창구업무 끝남");
							customer[i].situation = 3;//접수를끝내주고 정비대기상태로 보낸다
						}
					}
					//정비창구 업무 끝난애들
					if(customer[i].situation==4){
						if(c2[customer[i].bnum]==0){
//							System.out.println(customer[i].start+"번째 고객 정비창구업무 끝남");
							customer[i].situation=5;
							++cnt;
						}
					}
				}
				
				if(cnt==k) break;
				
				//0입장 1접수대기 2접수중 3접수끝 ->정비대기 4정비중 5끝
				//접수대기자들 중 빈 접수창구로 보내주기
				
				for(int i=1; i<=k; i++){
					if(customer[i].situation==1){//접수대기중인 사람들중에서
						for(int j=1; j<=n; j++){//빈 접수창구 확인
							if(c1[j]==0){//빈 접수창구가있으면
//								System.out.println(i+"번째 손님  " + j+"번 접수창구로 이동");
								customer[i].situation=2;//접수중 상태로 바꾸고
								customer[i].anum=j;//고객의 접수창구번호 갱신
								c1[j]=a[j];//접수시간 갱신
								break;
							}
							
						}
					}
				}
				
				//0입장 1접수대기 2접수중 3접수끝 ->정비대기 4정비중 5끝
				//정비대기자들 중 빈 정비창구로 보내주기
				for(int i=1; i<=k; i++){
					if(customer[i].situation==3){//정비대기중인 사람들중에서
						for(int j=1; j<=m; j++){//빈 접수창구 확인
							if(c2[j]==0){//빈 정비창구가있으면
//								System.out.println(i+"번째 손님  " + j+"번 정비창구로 이동");
								customer[i].situation=4;//정비중 상태로 바꾸고
								customer[i].bnum=j;//고객의 정비창구번호 갱신
								c2[j]=b[j];//정비시간 갱신
								break;
							}
							
						}
					}
				}
				
				System.out.print("a : ");
				for(int i=1; i<=n; i++){
					System.out.print(c1[i]+" ");
				}
				System.out.print("\nb : ");
				for(int i=1; i<=m; i++)
					System.out.print(c2[i]+" ");
				System.out.println();
				
				++now;
				for(int i=1; i<=n; i++)
					if(c1[i]>0)--c1[i];
				for(int i=1; i<=m; i++)
					if(c2[i]>0)--c2[i];
				
			}
			
			int ans = -1;
			for(int i=1; i<=k; i++){
				Person temp = customer[i];
//				System.out.println(temp.start+"번 고객 : " + temp.anum+"번 접수 ," + temp.bnum+"번 정비");
				if(temp.anum==endA && temp.bnum==endB) ans+=i;
			}
			System.out.print("#"+tc+" ");
			if(ans==-1) System.out.println(-1);
			else System.out.println(ans+1);
		}
		
		in.close();
	}
	public static class Person{
		int start, anum, bnum;
		int situation;
		public Person(){
			start = anum = bnum = -10;
			situation = 0;	//0입장 1접수대기 2접수중 3접수끝 ->정비대기 3정비중 4정비대기
		}
		public void setStart(int num){
			start = num;
		}
		public void setAnum(int num){
			anum = num;
		}
		public void setBnum(int num){
			bnum = num;
		}
	}
}
import datetime
import pandas as pd
import numpy as np
import datetime
#연,월,일을 이용해여 요일을 반환하는 함수 정의
def getDayName(a,b,c):
    dayString =['mon', 'tue', 'wed', 'thu', 'fri', 'sat' , 'sun']
    return dayString[datetime.date(a,b,c).weekday()]

def fct(row):
    return getDayName(row['YEAR'], row['MONTH'], row['DAY'])

#데이터 불러오기
demo = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/고객DEMO.txt', sep=',', engine ='python' , dtype=str)

buy = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/쇼핑업종 상품구매.txt', sep=',', engine ='python' , 
                  dtype={'ID' : str,'RCT_NO' : str,'BIZ_UNIT' : str, 'PD_S_C' : str, 'BR_C' : str ,
                         'DE_DT' : str,'DE_HR' : int, 'BUY_AM' : int, 'BUY_CT' : int})

date_train = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_train.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})

demo1 = pd.merge(demo, date_train[['ID']], how='inner', on='ID')    
    
kind=pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/쇼핑업종 상품분류.txt', sep=',', engine='python', encoding='utf-8-sig', dtype={'PD_S_C' : str})
    
#데이터 조인
def strtodate(row):
    date = datetime.datetime.strptime(row , "%Y%m%d").date()
    return date

buy['DATE']= [strtodate(row) for row in buy['DE_DT']]

buy_1 = buy[buy['DATE'] <= datetime.date(2015,9,30)] 

demo_buy = pd.merge(demo1, buy_1 , how='inner' , on='ID')

demo_buy2 = demo_buy[['ID', 'RCT_NO','BR_C','BIZ_UNIT' ,'DE_DT','DE_HR','BUY_AM', 'BUY_CT' , 'PD_S_C' ]]

buy2 = pd.merge(demo_buy2, kind, how='inner' , on=('BIZ_UNIT', 'PD_S_C'))

#구매 행위 식별 변수 생성
N_PCT=[buy2['RCT_NO'].ix[x]+buy2['BIZ_UNIT'].ix[x]+ buy2['DE_DT'].ix[x]+
       str(buy2['DE_HR'].ix[x]) + buy2['BR_C'].ix[x] for x in range(len(buy2))]

buy2['N_PCT']  = pd.DataFrame(N_PCT)

#'ID' '구매일자', '행위식별변수'
demo_buy3 = buy2[['ID' , 'DE_DT', 'N_PCT']]

#중복 행 제거
demo_buy4 = demo_buy3.drop_duplicates()

#구매일자에서 연,월,일 추출
demo_buy4['YEAR'] = demo_buy4['DE_DT'].str[0:4] 
demo_buy4['MONTH'] = demo_buy4['DE_DT'].str[4:6] 
demo_buy4['DAY'] = demo_buy4['DE_DT'].str[6:8] 
demo_buy4['CNT'] =1

#행위식별 변수 , 'DE_DT' 제거
demo_buy5 = demo_buy4[['ID','YEAR','MONTH','DAY','CNT']]

#구매 연,월, 일을 숫자로 변환
demo_buy5[['YEAR','MONTH', 'DAY']] = demo_buy5[['YEAR','MONTH', 'DAY']].apply(pd.to_numeric)

#행별로 구매 요일을 추출하여 'DAYNAME'으로 저장
demo_buy5['DAYNAME'] = demo_buy5.apply(fct, axis=1)

#요일별 쇼핑 횟수 합 구하는 피벗 테이블 생성
demo_buy_dayname = pd.pivot_table(demo_buy5, index='ID', values='CNT', columns='DAYNAME', fill_value=0, aggfunc=np.sum)

#ID별 전체 쇼핑 횟수 계산 
demo_buy_dayname['SUM'] = demo_buy_dayname['mon'] +demo_buy_dayname['tue'] +demo_buy_dayname['wed'] +demo_buy_dayname['thu'] + demo_buy_dayname['fri'] +demo_buy_dayname['sat'] + demo_buy_dayname['sun']

#ID별 주중 주말 쇼핑 횟수 계산
demo_buy_dayname['weekday'] = demo_buy_dayname['mon'] +demo_buy_dayname['tue'] +demo_buy_dayname['wed'] +demo_buy_dayname['thu'] + demo_buy_dayname['fri'] 
demo_buy_dayname['weekend'] = demo_buy_dayname['sat'] +demo_buy_dayname['sun']
demo_buy_dayname4 = demo_buy_dayname[['weekday','weekend']]

#ID 별 요일 별 쇼핑 비율 계산
demo_buy_dayname['pmon'] = demo_buy_dayname['mon'] / demo_buy_dayname['SUM']
demo_buy_dayname['ptue'] = demo_buy_dayname['tue'] / demo_buy_dayname['SUM']
demo_buy_dayname['pwed'] = demo_buy_dayname['wed'] / demo_buy_dayname['SUM']
demo_buy_dayname['pthu'] = demo_buy_dayname['thu'] / demo_buy_dayname['SUM']
demo_buy_dayname['pfri'] = demo_buy_dayname['fri'] / demo_buy_dayname['SUM']
demo_buy_dayname['psat'] = demo_buy_dayname['sat'] / demo_buy_dayname['SUM']
demo_buy_dayname['psun'] = demo_buy_dayname['sun'] / demo_buy_dayname['SUM']

demo_buy_dayname2 = demo_buy_dayname[['pmon','ptue','pwed','pthu','pfri','psat','psun']]

#주중 , 주말 쇼핑 비율 계산
demo_buy_dayname2['weekday'] = (demo_buy_dayname['pmon'] + demo_buy_dayname['ptue'] +demo_buy_dayname['pwed']+ demo_buy_dayname['pthu']
                               +demo_buy_dayname['pfri'])
demo_buy_dayname2['weekend'] = (demo_buy_dayname['psat'] + demo_buy_dayname['psun'])

#주중, 주잘 쇼핑 비율 테이블 생성
demo_buy_dayname3 = demo_buy_dayname2[['weekday','weekend']]


#군집분석을 위한 sklearn 패키지 
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt

#군집개수 별 SSE를 그려주는 함수 정의
def elbow(X):
    sse= []
    for i in range(1,11):
        km = KMeans(n_clusters = i, init = 'k-means++', random_state=0)
        km.fit(X)
        sse.append(km.inertia_)
        
    plt.plot(range(1,11), sse, marker='o')
    plt.xlabel('클러스터개수')
    plt.ylabel('SSE')
    plt.show()  

#K-means 함수 정의, 군집 개수 = 3
init_centroid = 'random'


#데이터 스케일 조정을 위한 패키지
from sklearn.preprocessing import minmax_scale

#주중 주말 쇼핑 횟수를 이용한 군집화
#minmax_scale함수를 이용한 스케일 조정
minmax_dayname = pd.DataFrame(minmax_scale(demo_buy_dayname4), columns=('weekday', 'weekend'))

#최적 군집 개수 찾는 함수
elbow(minmax_dayname)

#군집 결과를 y_km으로 저장(0,1,2)

km = KMeans(n_clusters=3, init=init_centroid, random_state=0)

y_km = pd.DataFrame(km.fit_predict(minmax_dayname))

minmax_dayname['pred'] =y_km


#군집 결과 시각화
plt.scatter(minmax_dayname['weekday'][minmax_dayname['pred'] == 0], minmax_dayname['weekend'][minmax_dayname['pred'] == 0], c='lightgreen', marker='s',
            s=5, label='0')
plt.scatter(minmax_dayname['weekday'][minmax_dayname['pred'] == 1], minmax_dayname['weekend'][minmax_dayname['pred'] == 1], c='orange', marker='o',
            s=5, label='1')
plt.scatter(minmax_dayname['weekday'][minmax_dayname['pred'] == 2], minmax_dayname['weekend'][minmax_dayname['pred'] == 2], c='lightblue', marker='v',
            s=5, label='2')
plt.scatter(km.cluster_centers_[:,0], km.cluster_centers_[:,1], c='red', marker='*',
            s=50, label='center')

plt.legend()
plt.grid(True)
plt.show()

#주중 주말 쇼핑 비율을 이용한 군집화
#스케일 조정
minmax_dayname2 = demo_buy_dayname3[['weekday','weekend']]
#최적 군집 횟수 찾는 함수
elbow(minmax_dayname2)

#군집 결과를 y_km으로 저장(0,1,2)
y_km = pd.DataFrame(km.fit_predict(minmax_dayname2))
y_km.index = minmax_dayname2.index

minmax_dayname2['pred'] =y_km

#군집 분석 결과 시각화
plt.scatter(minmax_dayname2['weekday'][minmax_dayname2['pred'] == 0], minmax_dayname2['weekend'][minmax_dayname2['pred'] == 0], c='lightgreen', marker='s',
            s=5, label='0')
plt.scatter(minmax_dayname2['weekday'][minmax_dayname2['pred'] == 1], minmax_dayname2['weekend'][minmax_dayname2['pred'] == 1], c='orange', marker='o',
            s=5, label='1')
plt.scatter(minmax_dayname2['weekday'][minmax_dayname2['pred'] == 2], minmax_dayname2['weekend'][minmax_dayname2['pred'] == 2], c='lightblue', marker='v',
            s=5, label='2')
plt.scatter(km.cluster_centers_[:,0], km.cluster_centers_[:,1], c='red', marker='*',
            s=50, label='center')

plt.legend()
plt.grid(True)
plt.show()
del demo_buy_dayname3['pred']
#ID에 군집 분석 결과 저장(0 = '주중형' , 1='균등형' , 2='주말형')

minmax_dayname2['ID'] = minmax_dayname2.index
dayname_result2 = minmax_dayname2[['ID','pred']]

dayname_result2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/dayname_result.csv', sep=',', index=False )

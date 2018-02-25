import pandas as pd
import numpy as np
import datetime
#데이터 셋 불러오기
demo = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/고객DEMO.txt', sep=',', engine ='python' , dtype=str)

add =pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/주소.txt', sep=',', engine ='python' , dtype=np.str)

#거주지가 Null인 고객 제거
demo1 = pd.merge(demo, add, how='inner', left_on='HOM_PST_NO', right_on='우편번호')

buy = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/쇼핑업종 상품구매.txt', sep=',', engine ='python' , 
                  dtype={'ID' : str,'RCT_NO' : str,'BIZ_UNIT' : str, 'PD_S_C' : str, 'BR_C' : str ,
                         'DE_DT' : str,'DE_HR' : int, 'BUY_AM' : int, 'BUY_CT' : int})

kind=pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/쇼핑업종 상품분류.txt', sep=',', engine='python', encoding='utf-8-sig', dtype={'PD_S_C' : str})

#구매 내역을 2015년 10월 이전 이후로 분리
def strtodate(row):
    date = datetime.datetime.strptime(row , "%Y%m%d").date()
    return date

buy['DATE']= [strtodate(row) for row in buy['DE_DT']]

#9월 30일 기준 분리
buy_1 = buy[buy['DATE'] <= datetime.date(2015,9,30)] 
buy_2 = buy[buy['DATE'] > datetime.date(2015,9,30)] 

#2015년 9월까지 구매내역
#ID 기준 조인
#거주지 Null인 고객의 구매내역 제거
demo_buy_1 = pd.merge(demo1, buy_1 , how='inner' , on='ID')

demo_buy_1_2 = demo_buy_1[['ID', 'RCT_NO','BR_C','BIZ_UNIT' ,'DE_DT','DE_HR','BUY_AM', 'BUY_CT' , 'PD_S_C' ]]

buy_1_2 = pd.merge(demo_buy_1_2, kind, how='inner' , on=('BIZ_UNIT', 'PD_S_C'))

# 구매행위 식별 변수 생성
N_PCT=[buy_1_2['RCT_NO'].ix[x]+buy_1_2['BIZ_UNIT'].ix[x]+ buy_1_2['DE_DT'].ix[x]+
       str(buy_1_2['DE_HR'].ix[x]) + buy_1_2['BR_C'].ix[x] for x in range(len(buy_1_2))]

buy_1_2['N_PCT']  = pd.DataFrame(N_PCT)

demo_buy_group_1 = buy_1_2.groupby(['ID','N_PCT', 'PD_S_NM','BIZ_UNIT'], as_index=False).sum()

#구매 행위 카운트 열 추가
demo_buy_group_1['CNT'] = 1

#구매 행위 피벗 테이블 생성
demo_buy_bct = pd.pivot_table(demo_buy_group_1, index='ID', columns = 'PD_S_NM' , values='CNT' , aggfunc=np.mean, fill_value=0)

demo_buy_bct2 = pd.pivot_table(demo_buy_group_1, index='ID', columns = 'PD_S_NM' , values='CNT' , aggfunc=np.sum, fill_value=0)

demo_buy_bct['ID'] = demo_buy_bct.index
demo_buy_bct2['ID'] = demo_buy_bct2.index

#demo_buy_bct.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/rating01_201509.csv', index=False)
#demo_buy_bct2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/rating_201509.csv', index=False)

#2015년 9월이후 구매내역
#ID 기준 조인
demo_buy_2 = pd.merge(demo1, buy_2 , how='inner' , on='ID')

demo_buy_2_2 = demo_buy_2[['ID', 'RCT_NO','BR_C','BIZ_UNIT' ,'DE_DT','DE_HR','BUY_AM', 'BUY_CT' , 'PD_S_C' ]]

buy_2_2 = pd.merge(demo_buy_2_2, kind, how='inner' , on=('BIZ_UNIT', 'PD_S_C'))

# 구매행위 식별 변수 생성 
N_PCT=[buy_2_2['RCT_NO'].ix[x]+buy_2_2['BIZ_UNIT'].ix[x]+ buy_2_2['DE_DT'].ix[x]+
       str(buy_2_2['DE_HR'].ix[x]) + buy_2_2['BR_C'].ix[x] for x in range(len(buy_2_2))]

buy_2_2['N_PCT']  = pd.DataFrame(N_PCT)

demo_buy_group_2 = buy_2_2.groupby(['ID','N_PCT', 'PD_S_NM','BIZ_UNIT'], as_index=False).sum()

#구매 행위 카운트 열 추가
demo_buy_group_2['CNT'] = 1

#구매 행위 피벗 테이블 생성
demo_buy_bct_2 = pd.pivot_table(demo_buy_group_2, index='ID', columns = 'PD_S_NM' , values='CNT' , aggfunc=np.mean, fill_value=0)

demo_buy_bct2_2 = pd.pivot_table(demo_buy_group_2, index='ID', columns = 'PD_S_NM' , values='CNT' , aggfunc=np.sum, fill_value=0)

demo_buy_bct_2['ID'] = demo_buy_bct_2.index
demo_buy_bct2_2['ID'] = demo_buy_bct2_2.index

#9월 이전 이후에 모두 구매내역이 있는 고객들만 추출
id_after = pd.DataFrame(demo_buy_bct_2['ID'])
id_after['a'] = 1

demo_buy_bct_train = pd.merge(demo_buy_bct2, id_after, how='inner' ,on ='ID')
del demo_buy_bct_train['a']
demo_buy_bct_train01 = pd.merge(demo_buy_bct, id_after, how='inner' ,on ='ID')
del demo_buy_bct_train01['a']


id_after_2 = pd.DataFrame(demo_buy_bct_train['ID'])
id_after_2['a'] = 1

demo_buy_bct_test = pd.merge(demo_buy_bct2_2, id_after_2, how='inner' ,on ='ID')
del demo_buy_bct_test['a']
demo_buy_bct_test01 = pd.merge(demo_buy_bct_2, id_after_2, how='inner' ,on ='ID')
del demo_buy_bct_test01['a']

demo_buy_bct_train.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/demo_buy_bct_train.csv', index=False)
demo_buy_bct_train01.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/demo_buy_bct_train01.csv', index=False)
demo_buy_bct_test.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/demo_buy_bct_test.csv', index=False)
demo_buy_bct_test01.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/demo_buy_bct_test01.csv', index=False)


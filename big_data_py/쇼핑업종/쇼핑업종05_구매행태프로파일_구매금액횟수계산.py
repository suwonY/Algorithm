import pandas as pd
import numpy as np
import datetime
#데이터 셋 불러오기
demo = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/고객DEMO.txt', sep=',', engine ='python' , dtype=str)


buy = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/쇼핑업종 상품구매.txt', sep=',', engine ='python' , 
                  dtype={'ID' : str,'RCT_NO' : str,'BIZ_UNIT' : str, 'PD_S_C' : str, 'BR_C' : str ,
                         'DE_DT' : str,'DE_HR' : int, 'BUY_AM' : int, 'BUY_CT' : int})

date_train = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_train.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})

kind=pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/쇼핑업종 상품분류.txt', sep=',', engine='python', encoding='utf-8-sig', dtype={'PD_S_C' : str})

#ID 기준 조인
demo1 = pd.merge(demo, date_train[['ID']], how='inner', on='ID')

def strtodate(row):
    date = datetime.datetime.strptime(row , "%Y%m%d").date()
    return date

buy['DATE']= [strtodate(row) for row in buy['DE_DT']]

buy_1 = buy[buy['DATE'] <= datetime.date(2015,9,30)] 

demo_buy = pd.merge(demo1, buy_1 , how='inner' , on='ID')

demo_buy2 = demo_buy[['ID', 'RCT_NO','BR_C','BIZ_UNIT' ,'DE_DT','DE_HR','BUY_AM', 'BUY_CT' , 'PD_S_C' ]]

buy2 = pd.merge(demo_buy2, kind, how='inner' , on=('BIZ_UNIT', 'PD_S_C'))

# 구매행위 식별 변수 생성
N_PCT=[buy2['RCT_NO'].ix[x]+buy2['BIZ_UNIT'].ix[x]+ buy2['DE_DT'].ix[x]+
       str(buy2['DE_HR'].ix[x]) + buy2['BR_C'].ix[x] for x in range(len(buy2))]

buy2['N_PCT']  = pd.DataFrame(N_PCT)


#행위별 구매 금액 계산
demo_buy_group = buy2.groupby(['ID','N_PCT', 'PD_S_NM'], as_index=False).sum()

demo_buy_new_group = demo_buy_group[['ID','PD_S_NM', 'BUY_AM']]
#구매 행위 카운트 열 추가
demo_buy_new_group['CNT'] = 1

#구매 금액 피벗 테이블 생성
demo_buy_bam = pd.pivot_table(demo_buy_new_group, index='ID', columns = 'PD_S_NM' , values='BUY_AM' , aggfunc=np.sum, fill_value=0)

del date_train['ID']

columns = list(date_train.columns)

demo_buy_bam2 = demo_buy_bam[columns]

#업종별 총 구매 금액 계산
demo_buy_bam2['bam_sum'] = demo_buy_bam2.apply(np.sum, axis=1)

#변동계수 계산 함수 정의
def cv(row):
    row = [row[columns] ]
    mean =np.mean(row)
    std = np.std(row)
    return std/mean

#ID 별 구매 금액에 대한 변동 계수 계산
demo_buy_bam2['bam_cv'] =demo_buy_bam2.apply(cv,axis=1)

#구매 행위 카운트 피벗 테이블 생성
demo_buy_bct = pd.pivot_table(demo_buy_new_group, index='ID', columns='PD_S_NM', values='CNT' , aggfunc=np.sum, fill_value=0)

demo_buy_bct2 = demo_buy_bct[columns]

#총 쇼핑 횟수 계산
demo_buy_bct2['bct_sum'] = demo_buy_bct2.apply(np.sum, axis=1)
#총 쇼핑 횟수에 대한 변동 계수 계산
demo_buy_bct2['bct_cv'] =demo_buy_bct2.apply(cv, axis=1)

#계산된 테이블 조인
demo_buy_bam2['ID'] =demo_buy_bam2.index
demo_buy_bct2['ID'] =demo_buy_bct2.index

demo_buy_bam_bct = pd.merge(demo_buy_bam2[['ID','bam_sum','bam_cv']], demo_buy_bct2[['ID','bct_sum','bct_cv']], how='inner', on='ID')

demo_buy_bam_bct.index = demo_buy_bam_bct['ID']

#결과 내보내기
demo_buy_bam_bct.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_bam_bct.csv' , sep=',' ,index=False)


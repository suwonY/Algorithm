import pandas as pd
import numpy as np
import datetime
#데이터 셋 불러오기
demo = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/고객DEMO.txt', sep=',', engine ='python' , dtype=str)


buy_else = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/쇼핑외 업종 상품구매.txt', sep=',', engine='python', 
                       dtype={ 'ID' : str,'BIZ_UNIT' : str ,'CRYM' : str ,' U_AM' : int ,'U_CT' : int })

date_else_train = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})


#ID 기준 조인
def strtodate(row):
    date = datetime.datetime.strptime(row , "%Y%m").date()
    return date

buy_else['DATE']= [strtodate(row) for row in buy_else['CRYM']]

buy_else_1 = buy_else[buy_else['DATE'] <= datetime.date(2015,9,30)] 


demo_buy = pd.merge(pd.DataFrame(date_else_train['ID']), buy_else_1 , how='inner' , on='ID')

#행위별 구매 금액 계산

#구매 금액 피벗 테이블 생성


demo_buy_bam = pd.pivot_table(demo_buy, index='ID', columns = 'BIZ_UNIT' , values='U_AM' , aggfunc=np.sum, fill_value=0)

columns =demo_buy_bam.columns
#업종별 총 구매 금액 계산
demo_buy_bam['bam_sum'] = demo_buy_bam.apply(np.sum, axis=1)

#변동계수 계산 함수 정의
def cv(row):
    row = [row[columns] ]
    mean =np.mean(row)
    std = np.std(row)
    return std/mean

#ID 별 구매 금액에 대한 변동 계수 계산
demo_buy_bam['bam_cv'] =demo_buy_bam.apply(cv,axis=1)

#구매 행위 카운트 피벗 테이블 생성
demo_buy_bct = pd.pivot_table(demo_buy, index='ID', columns='BIZ_UNIT', values='U_CT' , aggfunc=np.sum, fill_value=0)

#총 쇼핑 횟수 계산
demo_buy_bct['bct_sum'] = demo_buy_bct.apply(np.sum, axis=1)
#총 쇼핑 횟수에 대한 변동 계수 계산
demo_buy_bct['bct_cv'] =demo_buy_bct.apply(cv, axis=1)

#계산된 테이블 조인
demo_buy_bam['ID'] =demo_buy_bam.index
demo_buy_bct['ID'] =demo_buy_bct.index

demo_buy_bam_bct = pd.merge(demo_buy_bam[['ID','bam_sum','bam_cv']], demo_buy_bct[['ID','bct_sum','bct_cv']], how='inner', on='ID')

demo_buy_bam_bct.index = demo_buy_bam_bct['ID']

#결과 내보내기
demo_buy_bam_bct.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit.csv' , sep=',' ,index=False)


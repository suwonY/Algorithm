import pandas as pd
import numpy as np
import datetime
demo = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/고객DEMO.txt', sep=',', engine ='python' , dtype=str)

add =pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/주소.txt', sep=',', engine ='python' , dtype=np.str)

demo1 = pd.merge(demo, add, how='inner', left_on='HOM_PST_NO', right_on='우편번호')

buy_else = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/쇼핑외 업종 상품구매.txt', sep=',', engine='python', 
                       dtype={ 'ID' : str,'BIZ_UNIT' : str ,'CRYM' : str ,' U_AM' : int ,'U_CT' : int })

def strtodate(row):
    date = datetime.datetime.strptime(row , "%Y%m").date()
    return date

buy_else['DATE']= [strtodate(row) for row in buy_else['CRYM']]

buy_else1 = buy_else[buy_else['DATE'] <= datetime.date(2015,9,30)] 
buy_else2 = buy_else[buy_else['DATE'] > datetime.date(2015,9,30)] 
#2015년 9월 이전 쇼핑외 내역
demo_else1 = pd.merge(demo1, buy_else, how='inner', on='ID')

demo_else2 = demo_else1[['ID', 'BIZ_UNIT' , 'CRYM']]

demo_else2 = demo_else2.drop_duplicates()

demo_else2['CNT'] = 1

demo_else1_cnt = pd.pivot_table(demo_else2, values='CNT', index='ID' , columns='BIZ_UNIT', aggfunc=np.sum, fill_value=0)

demo_else1_cnt['ID'] = demo_else1_cnt.index
demo_else1_cnt.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/demo_else1_cnt.csv', index=False)
#2015년 9월 이후 쇼핑외 내역
demo_else1_2 = pd.merge(demo1, buy_else2, how='inner', on='ID')

demo_else2_2 = demo_else1_2[['ID', 'BIZ_UNIT' , 'CRYM']]

demo_else2_2 = demo_else2_2.drop_duplicates()

demo_else2_2['CNT'] = 1

demo_else2_cnt = pd.pivot_table(demo_else2_2, values='CNT', index='ID' , columns='BIZ_UNIT', aggfunc=np.sum, fill_value=0)

demo_else2_cnt['ID'] = demo_else2_cnt.index
demo_else2_cnt.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/demo_else2_cnt.csv', index=False)

import pandas as pd
import numpy as np
from sklearn.metrics import pairwise_distances

date_rating_train1 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train1.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})
date_rating_train2 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train2.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})
date_rating_train3 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train3.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})
date_rating_train4 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train4.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})
date_rating_train5 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train5.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})

date_rating_test = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_test.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})

demo = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/people_profile_total.csv', sep=',', engine ='python' , dtype=str)

add_buy = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_buy.csv', sep=',', engine ='python',)

demo_add = pd.merge(demo, add_buy, how='left' , on=('CITY','ST_PR')).fillna(0)
add_col = list(add_buy.columns)
demo1 =pd.concat((demo_add['ID'] , demo_add[add_col]),axis=1)
del demo1['CITY']
del demo1['ST_PR']

#데이터 합치기
rating_12345 = pd.concat((date_rating_train1,date_rating_train2,date_rating_train3,date_rating_train4,date_rating_train5))
rating_12345 = pd.merge(pd.DataFrame(rating_12345['ID']), demo1, how='inner' , on='ID')
rating_12345.index = rating_12345['ID']
del rating_12345['ID']


date_rating_test = pd.merge(pd.DataFrame(date_rating_test['ID']), demo1, how='inner' , on='ID')
date_rating_test.index =date_rating_test['ID']
del date_rating_test['ID']
#tanimoto 유사도 계산
test_cosrating = 1 - pairwise_distances(date_rating_test, rating_12345, metric="rogerstanimoto")
test_cosrating = pd.DataFrame(test_cosrating)
test_cosrating.columns = list(rating_12345.index)
test_cosrating['ID'] = date_rating_test.index
test_cosrating.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_tanimoto_rating.csv' ,index=False)
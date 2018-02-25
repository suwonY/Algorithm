import pandas as pd
import numpy as np
from sklearn.metrics import pairwise_distances
#데이터 불러오기
date_else_train1 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train1.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})
date_else_train2 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train2.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})
date_else_train3 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train3.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})
date_else_train4 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train4.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})
date_else_train5 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train5.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})

date_else_test = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_testset.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})
demo = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/people_profile_total.csv', sep=',', engine ='python' , dtype=str)

add_else = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_else.csv', sep=',', engine ='python',)
add_else = add_else.drop_duplicates()

demo_add = pd.merge(demo, add_else, how='left' , on=('CITY','ST_PR')).fillna(0)
add_col = list(add_else.columns)
demo1 =pd.concat((demo_add['ID'] , demo_add[add_col]),axis=1)
del demo1['CITY']
del demo1['ST_PR']


rating_12345 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train3['ID'],date_else_train4['ID'],date_else_train5['ID'])))
rating_12345 = pd.merge(rating_12345, demo1,how='inner' , on='ID')
rating_12345.index = rating_12345['ID']
del rating_12345['ID']

rating_test = pd.merge(pd.DataFrame(date_else_test['ID']), demo1, how='inner', on='ID')
rating_test.index = rating_test['ID']
del rating_test['ID']
#test X train12345 tanimoto 유사도
#tanimoto 유사도 계산
tanimotorating_2 = 1 - pairwise_distances(rating_test, rating_12345, metric="rogerstanimoto")
tanimotorating2 = pd.DataFrame(tanimotorating_2)
tanimotorating2.columns = list(rating_12345.index)
tanimotorating2['ID'] = rating_test.index
tanimotorating2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_tanimoto_else_rating.csv',index=False )

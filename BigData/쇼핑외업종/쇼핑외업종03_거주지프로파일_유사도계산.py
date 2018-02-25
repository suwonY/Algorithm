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


demo = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/people_profile_total.csv', sep=',', engine ='python' , dtype=str)

add_else = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_else.csv', sep=',', engine ='python',)
add_else = add_else.drop_duplicates()

demo_add = pd.merge(demo, add_else, how='left' , on=('CITY','ST_PR')).fillna(0)
add_col = list(add_else.columns)
demo1 =pd.concat((demo_add['ID'] , demo_add[add_col]),axis=1)
del demo1['CITY']
del demo1['ST_PR']


rating_1234 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train3['ID'],date_else_train4['ID'])))
rating_1234 = pd.merge(rating_1234, demo1,how='inner' , on='ID')
rating_1234.index = rating_1234['ID']
del rating_1234['ID']

rating_1235 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train3['ID'],date_else_train5['ID'])))
rating_1235 =pd.merge(rating_1235, demo1,how='inner' , on='ID')
rating_1235.index = rating_1235['ID']
del rating_1235['ID']

rating_1245 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train4['ID'],date_else_train5['ID'])))
rating_1245 =pd.merge(rating_1245, demo1,how='inner' , on='ID')
rating_1245.index = rating_1245['ID']
del rating_1245['ID']

rating_1345 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train3['ID'],date_else_train4['ID'],date_else_train5['ID'])))
rating_1345 =pd.merge(rating_1345, demo1,how='inner' , on='ID')
rating_1345.index = rating_1345['ID']
del rating_1345['ID']

rating_2345 = pd.DataFrame(pd.concat((date_else_train2['ID'],date_else_train3['ID'],date_else_train4['ID'],date_else_train5['ID'])))
rating_2345 =pd.merge(rating_2345, demo1,how='inner' , on='ID')
rating_2345.index = rating_2345['ID']
del rating_2345['ID']

rating_1 = pd.merge(pd.DataFrame(date_else_train1['ID']),demo1,how='inner' , on ='ID')
rating_1.index = rating_1['ID']
del rating_1['ID']

rating_2 = pd.merge(pd.DataFrame(date_else_train2['ID']),demo1,how='inner' , on ='ID')
rating_2.index = rating_2['ID']
del rating_2['ID']

rating_3 = pd.merge(pd.DataFrame(date_else_train3['ID']),demo1,how='inner' , on ='ID')
rating_3.index = rating_3['ID']
del rating_3['ID']

rating_4 = pd.merge(pd.DataFrame(date_else_train4['ID']),demo1,how='inner' , on ='ID')
rating_4.index = rating_4['ID']
del rating_4['ID']

rating_5 = pd.merge(pd.DataFrame(date_else_train5['ID']),demo1,how='inner' , on ='ID')
rating_5.index = rating_5['ID']
del rating_5['ID']

#train1 X train2~5 tanimoto 유사도
#tanimoto 유사도 계산
tanimotorating_1 = 1 - pairwise_distances(rating_1, rating_2345, metric="rogerstanimoto")
tanimotorating1 = pd.DataFrame(tanimotorating_1)
tanimotorating1.columns = list(rating_2345.index)
tanimotorating1['ID'] = rating_1.index
tanimotorating1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/tanimoto_else_rating1.csv' ,index=False)




#train2 X train1345 tanimoto 유사도
#tanimoto 유사도 계산
tanimotorating_2 = 1 - pairwise_distances(rating_2, rating_1345, metric="rogerstanimoto")
tanimotorating2 = pd.DataFrame(tanimotorating_2)
tanimotorating2.columns = list(rating_1345.index)
tanimotorating2['ID'] = rating_2.index
tanimotorating2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/tanimoto_else_rating2.csv',index=False )

#train3 X train1245 tanimoto 유사도
#tanimoto 유사도 계산
tanimotorating_3 = 1 - pairwise_distances(rating_3, rating_1245, metric="rogerstanimoto")
tanimotorating3 = pd.DataFrame(tanimotorating_3)
tanimotorating3.columns = list(rating_1245.index)
tanimotorating3['ID'] = rating_3.index
tanimotorating3.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/tanimoto_else_rating3.csv',index=False )

#train4 X train1235 tanimoto 유사도
#tanimoto 유사도 계산
tanimotorating_4 = 1 - pairwise_distances(rating_4, rating_1235, metric="rogerstanimoto")
tanimotorating4 = pd.DataFrame(tanimotorating_4)
tanimotorating4.columns = list(rating_1235.index)
tanimotorating4['ID'] = rating_4.index
tanimotorating4.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/tanimoto_else_rating4.csv',index=False )

#train5 X train1234 tanimoto 유사도
#tanimoto 유사도 계산
tanimotorating_5 = 1 - pairwise_distances(rating_5, rating_1234, metric="rogerstanimoto")
tanimotorating5 = pd.DataFrame(tanimotorating_5)
tanimotorating5.columns = list(rating_1234.index)
tanimotorating5['ID'] = rating_5.index
tanimotorating5.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/tanimoto_else_rating5.csv',index=False )

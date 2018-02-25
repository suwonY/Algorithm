import pandas as pd
import numpy as np
from sklearn.metrics import pairwise_distances
from sklearn.preprocessing import robust_scale
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

else_habit = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})

else_habit[['bam_sum','bct_sum']] = robust_scale(else_habit[['bam_sum','bct_sum']])

#데이터 합치기
rating_1234 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train3['ID'],date_else_train4['ID'])))
rating_1234 = pd.merge(rating_1234, else_habit,how='inner' , on='ID')
rating_1234.index = rating_1234['ID']
del rating_1234['ID']

rating_1235 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train3['ID'],date_else_train5['ID'])))
rating_1235 =pd.merge(rating_1235, else_habit,how='inner' , on='ID')
rating_1235.index = rating_1235['ID']
del rating_1235['ID']

rating_1245 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train4['ID'],date_else_train5['ID'])))
rating_1245 =pd.merge(rating_1245, else_habit,how='inner' , on='ID')
rating_1245.index = rating_1245['ID']
del rating_1245['ID']

rating_1345 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train3['ID'],date_else_train4['ID'],date_else_train5['ID'])))
rating_1345 =pd.merge(rating_1345, else_habit,how='inner' , on='ID')
rating_1345.index = rating_1345['ID']
del rating_1345['ID']

rating_2345 = pd.DataFrame(pd.concat((date_else_train2['ID'],date_else_train3['ID'],date_else_train4['ID'],date_else_train5['ID'])))
rating_2345 =pd.merge(rating_2345, else_habit,how='inner' , on='ID')
rating_2345.index = rating_2345['ID']
del rating_2345['ID']

rating_1 = pd.merge(pd.DataFrame(date_else_train1['ID']),else_habit,how='inner' , on ='ID')
rating_1.index = rating_1['ID']
del rating_1['ID']

rating_2 = pd.merge(pd.DataFrame(date_else_train2['ID']),else_habit,how='inner' , on ='ID')
rating_2.index = rating_2['ID']
del rating_2['ID']

rating_3 = pd.merge(pd.DataFrame(date_else_train3['ID']),else_habit,how='inner' , on ='ID')
rating_3.index = rating_3['ID']
del rating_3['ID']

rating_4 = pd.merge(pd.DataFrame(date_else_train4['ID']),else_habit,how='inner' , on ='ID')
rating_4.index = rating_4['ID']
del rating_4['ID']

rating_5 = pd.merge(pd.DataFrame(date_else_train5['ID']),else_habit,how='inner' , on ='ID')
rating_5.index = rating_5['ID']
del rating_5['ID']

#train1 X train2~5 코사인 유사도
#코사인 유사도 계산
else_habitrating_1 = 1 - pairwise_distances(rating_1, rating_2345, metric="cosine")
else_habitrating1 = pd.DataFrame(else_habitrating_1)
else_habitrating1.columns = list(rating_2345.index)
else_habitrating1['ID'] = rating_1.index
else_habitrating1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit_rating1.csv' ,index=False)

#train2 X train1345 코사인 유사도
#코사인 유사도 계산
else_habitrating_2 = 1 - pairwise_distances(rating_2, rating_1345, metric="cosine")
else_habitrating2 = pd.DataFrame(else_habitrating_2)
else_habitrating2.columns = list(rating_1345.index)
else_habitrating2['ID'] = rating_2.index
else_habitrating2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit_rating2.csv',index=False )

#train3 X train1245 코사인 유사도
#코사인 유사도 계산
else_habitrating_3 = 1 - pairwise_distances(rating_3, rating_1245, metric="cosine")
else_habitrating3 = pd.DataFrame(else_habitrating_3)
else_habitrating3.columns = list(rating_1245.index)
else_habitrating3['ID'] = rating_3.index
else_habitrating3.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit_rating3.csv',index=False )

#train4 X train1235 코사인 유사도
#코사인 유사도 계산
else_habitrating_4 = 1 - pairwise_distances(rating_4, rating_1235, metric="cosine")
else_habitrating4 = pd.DataFrame(else_habitrating_4)
else_habitrating4.columns = list(rating_1235.index)
else_habitrating4['ID'] = rating_4.index
else_habitrating4.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit_rating4.csv',index=False )

#train5 X train1234 코사인 유사도
#코사인 유사도 계산
else_habitrating_5 = 1 - pairwise_distances(rating_5, rating_1234, metric="cosine")
else_habitrating5 = pd.DataFrame(else_habitrating_5)
else_habitrating5.columns = list(rating_1234.index)
else_habitrating5['ID'] = rating_5.index
else_habitrating5.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit_rating5.csv',index=False )

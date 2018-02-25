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
#데이터 합치기
rating_1234 = pd.concat((date_else_train1,date_else_train2,date_else_train3,date_else_train4))
rating_1234.index = rating_1234['ID']
del rating_1234['ID']
rating_1235 = pd.concat((date_else_train1,date_else_train2,date_else_train3,date_else_train5))
rating_1235.index = rating_1235['ID']
del rating_1235['ID']
rating_1245 = pd.concat((date_else_train1,date_else_train2,date_else_train4,date_else_train5))
rating_1245.index = rating_1245['ID']
del rating_1245['ID']
rating_1345 = pd.concat((date_else_train1,date_else_train3,date_else_train4,date_else_train5))
rating_1345.index = rating_1345['ID']
del rating_1345['ID']
rating_2345 = pd.concat((date_else_train2,date_else_train3,date_else_train4,date_else_train5))
rating_2345.index = rating_2345['ID']
del rating_2345['ID']

date_else_train1.index = date_else_train1['ID']
del date_else_train1['ID']
date_else_train2.index = date_else_train2['ID']
del date_else_train2['ID']
date_else_train3.index = date_else_train3['ID']
del date_else_train3['ID']
date_else_train4.index = date_else_train4['ID']
del date_else_train4['ID']
date_else_train5.index = date_else_train5['ID']
del date_else_train5['ID']

#train1 X train2~5 코사인 유사도
#코사인 유사도 계산
date_cosrating_1 = 1 - pairwise_distances(date_else_train1, rating_2345, metric="cosine")
date_cos_rating1 = pd.DataFrame(date_cosrating_1)
date_cos_rating1.columns = list(rating_2345.index)
date_cos_rating1['ID'] = date_else_train1.index
date_cos_rating1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_cos_rating1.csv' ,index=False)

#train2 X train1345 코사인 유사도
#코사인 유사도 계산
date_cosrating_2 = 1 - pairwise_distances(date_else_train2, rating_1345, metric="cosine")
date_cos_rating2 = pd.DataFrame(date_cosrating_2)
date_cos_rating2.columns = list(rating_1345.index)
date_cos_rating2['ID'] = date_else_train2.index
date_cos_rating2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_cos_rating2.csv',index=False )

#train3 X train1245 코사인 유사도
#코사인 유사도 계산
date_cosrating_3 = 1 - pairwise_distances(date_else_train3, rating_1245, metric="cosine")
date_cos_rating3 = pd.DataFrame(date_cosrating_3)
date_cos_rating3.columns = list(rating_1245.index)
date_cos_rating3['ID'] = date_else_train3.index
date_cos_rating3.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_cos_rating3.csv',index=False )

#train4 X train1235 코사인 유사도
#코사인 유사도 계산
date_cosrating_4 = 1 - pairwise_distances(date_else_train4, rating_1235, metric="cosine")
date_cos_rating4 = pd.DataFrame(date_cosrating_4)
date_cos_rating4.columns = list(rating_1235.index)
date_cos_rating4['ID'] = date_else_train4.index
date_cos_rating4.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_cos_rating4.csv',index=False )

#train5 X train1234 코사인 유사도
#코사인 유사도 계산
date_cosrating_5 = 1 - pairwise_distances(date_else_train5, rating_1234, metric="cosine")
date_cos_rating5 = pd.DataFrame(date_cosrating_5)
date_cos_rating5.columns = list(rating_1234.index)
date_cos_rating5['ID'] = date_else_train5.index
date_cos_rating5.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_cos_rating5.csv',index=False )

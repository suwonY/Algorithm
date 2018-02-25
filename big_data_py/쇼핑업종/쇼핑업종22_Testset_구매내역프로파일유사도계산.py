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

#데이터 합치기
rating_12345 = pd.concat((date_rating_train1,date_rating_train2,date_rating_train3,date_rating_train4,date_rating_train5))
rating_12345.index = rating_12345['ID']
del rating_12345['ID']

date_rating_test.index = date_rating_test['ID']
del date_rating_test['ID']
#cosine 유사도 계산
test_cosrating = 1 - pairwise_distances(date_rating_test, rating_12345, metric="cosine")
test_cosrating = pd.DataFrame(test_cosrating)
test_cosrating.columns = list(rating_12345.index)
test_cosrating['ID'] = date_rating_test.index
test_cosrating.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_cos_rating.csv' ,index=False)
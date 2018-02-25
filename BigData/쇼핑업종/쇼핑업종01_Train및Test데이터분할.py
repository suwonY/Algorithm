import pandas as pd
import numpy as np

date_train = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_train.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})

from sklearn.model_selection import train_test_split
date_rating_train , date_rating_test = train_test_split(date_train,train_size=8000 , random_state = 42) 

date_rating_train1 = date_rating_train[0:1600]
date_rating_train2 = date_rating_train[1600:3200]
date_rating_train3 = date_rating_train[3200:4800]
date_rating_train4 = date_rating_train[4800:6400]
date_rating_train5 = date_rating_train[6400:8000]

#csv로 내보내기
date_rating_train.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train.csv' , index=False )
date_rating_train1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train1.csv' , index=False )
date_rating_train2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train2.csv' , index=False )
date_rating_train3.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train3.csv' , index=False )
date_rating_train4.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train4.csv' , index=False )
date_rating_train5.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train5.csv' , index=False )
date_rating_test.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_test.csv' , index=False )

#txt로 내보내기
date_rating_train.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train.txt' , index=False )
date_rating_train1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train1.txt' , index=False )
date_rating_train2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train2.txt' , index=False )
date_rating_train3.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train3.txt' , index=False )
date_rating_train4.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train4.txt' , index=False )
date_rating_train5.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_train5.txt' , index=False )
date_rating_test.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_rating_test.txt' , index=False )
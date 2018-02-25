import pandas as pd
import numpy as np

date_train = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train_cnt.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})

from sklearn.model_selection import train_test_split
date_else_train , date_else_test = train_test_split(date_train,train_size=10000 , random_state = 42) 

date_else_train1 = date_else_train[0:2000]
date_else_train2 = date_else_train[2000:4000]
date_else_train3 = date_else_train[4000:6000]
date_else_train4 = date_else_train[6000:8000]
date_else_train5 = date_else_train[8000:10000]

#csv로 내보내기
date_else_train.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_trainset.csv' , index=False )
date_else_train1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train1.csv' , index=False )
date_else_train2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train2.csv' , index=False )
date_else_train3.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train3.csv' , index=False )
date_else_train4.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train4.csv' , index=False )
date_else_train5.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train5.csv' , index=False )
date_else_test.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_testset.csv' , index=False )

#txt로 내보내기
date_else_train.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train.txt' , index=False )
date_else_train1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train1.txt' , index=False )
date_else_train2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train2.txt' , index=False )
date_else_train3.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train3.txt' , index=False )
date_else_train4.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train4.txt' , index=False )
date_else_train5.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train5.txt' , index=False )
date_else_test.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_test.txt' , index=False )
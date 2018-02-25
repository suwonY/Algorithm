import pandas as pd
import numpy as np
from sklearn.metrics import pairwise_distances
from sklearn.preprocessing import robust_scale
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

#코사인 유사도
rating_12345 = pd.concat((date_else_train1,date_else_train2,date_else_train3,date_else_train4,date_else_train5))
rating_12345.index = rating_12345['ID']
date_else_test.index = date_else_test['ID']

del rating_12345['ID']
del date_else_test['ID']

test_cosrating = 1 - pairwise_distances(date_else_test, rating_12345, metric="cosine")
test_cosrating = pd.DataFrame(test_cosrating)
test_cosrating.columns = list(rating_12345.index)
test_cosrating['ID'] = date_else_test.index
test_cosrating.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_test_cos_rating.csv' ,index=False)

#인구프로파일 유사도
#[n][0] : ID, [n][1] : 성별 , [n][2] : 나이 , [n][3] : 거주지 , [n][4],[n][5],[n][6] : 주소1,2,3
rating_12345['ID'] = rating_12345.index
date_else_test['ID'] = date_else_test.index
date_else_test_arr = np.array(date_else_test)
rating_12345_arr = np.array(rating_12345)

#peoplerating 인덱스에 date_else_test ID 추가
peoplerating=pd.DataFrame(date_else_test,columns=['ID'])
peoplerating.index = date_else_test['ID']
del peoplerating['ID']

##peoplerating 컬럼에 rating_12345 ID 추가
for i in list(rating_12345['ID']):
    peoplerating[str(i)]=0

peoplerating_arr = np.array(peoplerating,np.float64)                  

#성별: 같을때 0, 다를때 1
#나이: 20,30,40,50,60
#주소: REGION -> CITY -> ST_PR
gender_value=0.0
age_value=0.0
post_value=0.0

left_col_list=list(peoplerating.index)
right_col_list = list(rating_12345['ID'])

for i in range(len(left_col_list)):
    for j in range(0,len(right_col_list)):
        
        if(date_else_test_arr[i][1]==rating_12345_arr[j][1]):
            gender_value=1.0
        else:
            gender_value=0.0
            
        age_value = 1 - abs((float)(date_else_test_arr[i][2])-(float)(rating_12345_arr[j][2]))/50
        
        if(date_else_test_arr[i][6]==rating_12345_arr[j][6]):
            post_value=6.0
        elif(date_else_test_arr[i][5]==rating_12345_arr[j][5]):
            post_value=4.0
        elif(date_else_test_arr[i][4]==rating_12345_arr[j][4]):
            post_value=2.0
        else:
            post_value=0.0
            
        post_value=post_value/6.0
        peoplerating_arr[i][j]=(gender_value+age_value+post_value)/3.0

peoplerating=pd.DataFrame(peoplerating_arr)
peoplerating.index=left_col_list
peoplerating.columns=right_col_list

peoplerating['ID']=left_col_list
peoplerating.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_test_peoplerating.csv', index=False)

#구매행태 유사도
else_habit = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})


else_habit[['bam_sum','bct_sum']] = robust_scale(else_habit[['bam_sum','bct_sum']])


rating_12345 = pd.merge(pd.DataFrame(rating_12345['ID']), else_habit, how = 'inner' , on='ID')
date_else_test = pd.merge(pd.DataFrame(date_else_test['ID']), else_habit, how = 'inner' , on='ID')
rating_12345.index =rating_12345['ID']
date_else_test.index = date_else_test['ID']

del rating_12345['ID']
del date_else_test['ID']
else_habitrating = 1 - pairwise_distances(date_else_test, rating_12345, metric="cosine")
else_habitrating1 = pd.DataFrame(else_habitrating)
else_habitrating1.columns = list(rating_12345.index)
else_habitrating1['ID'] = date_else_test.index
else_habitrating1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_else_habit_rating.csv' ,index=False)

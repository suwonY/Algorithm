import pandas as pd
import numpy as np
#데이터 불러오기
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

demo1 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/people_profile_total.csv', sep=',', engine ='python' , dtype=str)

#test X train1~5 인구통계학적 유사도
id_12345 = pd.DataFrame(pd.concat((date_rating_train1['ID'],date_rating_train2['ID'],date_rating_train3['ID'],date_rating_train4['ID'],date_rating_train5['ID'])))
id_12345['a'] = 1

id_1 = pd.DataFrame(date_rating_test['ID'])
id_1['a'] = 1

id1_demo = pd.merge(demo1, id_1, how='inner' , on='ID')
id12345_demo = pd.merge(demo1, id_12345, how='inner' , on='ID')

del id1_demo['a']
del id12345_demo['a']

#[n][0] : ID, [n][1] : 성별 , [n][2] : 나이 , [n][3] : 거주지 , [n][4],[n][5],[n][6] : 주소1,2,3
id1_demo_arr = np.array(id1_demo)
id12345_demo_arr = np.array(id12345_demo)

#test_peoplerating 인덱스에 id1_demo ID 추가
test_peoplerating=pd.DataFrame(id1_demo,columns=['ID'])
test_peoplerating.index = id1_demo['ID']
del test_peoplerating['ID']

##test_peoplerating 컬럼에 id12345_demo ID 추가
for i in list(id12345_demo['ID']):
    test_peoplerating[str(i)]=0

test_peoplerating_arr = np.array(test_peoplerating,np.float64)                  

#성별: 같을때 0, 다를때 1
#나이: 20,30,40,50,60
#주소: REGION -> CITY -> ST_PR
gender_value=0.0
age_value=0.0
post_value=0.0

left_col_list=list(test_peoplerating.index)
right_col_list = list(id12345_demo['ID'])

for i in range(len(left_col_list)):
    for j in range(0,len(right_col_list)):
        
        if(id1_demo_arr[i][1]==id12345_demo_arr[j][1]):
            gender_value=1.0
        else:
            gender_value=0.0
            
        age_value = 1 - abs((float)(id1_demo_arr[i][2])-(float)(id12345_demo_arr[j][2]))/50
        
        if(id1_demo_arr[i][6]==id12345_demo_arr[j][6]):
            post_value=6.0
        elif(id1_demo_arr[i][5]==id12345_demo_arr[j][5]):
            post_value=4.0
        elif(id1_demo_arr[i][4]==id12345_demo_arr[j][4]):
            post_value=2.0
        else:
            post_value=0.0
            
        post_value=post_value/6.0
        test_peoplerating_arr[i][j]=(gender_value+age_value+post_value)/3.0

test_peoplerating=pd.DataFrame(test_peoplerating_arr)
test_peoplerating.index=left_col_list
test_peoplerating.columns=right_col_list

test_peoplerating['ID']=left_col_list
test_peoplerating.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_peoplerating.csv', index=False)

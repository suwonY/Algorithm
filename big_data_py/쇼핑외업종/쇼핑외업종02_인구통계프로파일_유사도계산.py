import pandas as pd
import numpy as np
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

demo1 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/people_profile_total.csv', sep=',', engine ='python' , dtype=str)

#train1 X train2~5 인구통계학적 유사도
id_2345 = pd.DataFrame(pd.concat((date_else_train2['ID'],date_else_train3['ID'],date_else_train4['ID'],date_else_train5['ID'])))
id_2345['a'] = 1

id_1 = pd.DataFrame(date_else_train1['ID'])
id_1['a'] = 1

id1_demo = pd.merge(id_1, demo1,how='inner' , on='ID')
id2345_demo = pd.merge(id_2345, demo1, how='inner' , on='ID')

del id1_demo['a']
del id2345_demo['a']

#[n][0] : ID, [n][1] : 성별 , [n][2] : 나이 , [n][3] : 거주지 , [n][4],[n][5],[n][6] : 주소1,2,3
id1_demo_arr = np.array(id1_demo)
id2345_demo_arr = np.array(id2345_demo)

#peoplerating_1 인덱스에 id1_demo ID 추가
peoplerating_1=pd.DataFrame(id1_demo,columns=['ID'])
peoplerating_1.index = id1_demo['ID']
del peoplerating_1['ID']

##peoplerating_1 컬럼에 id2345_demo ID 추가
for i in list(id2345_demo['ID']):
    peoplerating_1[str(i)]=0

peoplerating_1_arr = np.array(peoplerating_1,np.float64)                  

#성별: 같을때 0, 다를때 1
#나이: 20,30,40,50,60
#주소: REGION -> CITY -> ST_PR
gender_value=0.0
age_value=0.0
post_value=0.0

left_col_list=list(peoplerating_1.index)
right_col_list = list(id2345_demo['ID'])

for i in range(len(left_col_list)):
    for j in range(0,len(right_col_list)):
        
        if(id1_demo_arr[i][1]==id2345_demo_arr[j][1]):
            gender_value=1.0
        else:
            gender_value=0.0
            
        age_value = 1 - abs((float)(id1_demo_arr[i][2])-(float)(id2345_demo_arr[j][2]))/50
        
        if(id1_demo_arr[i][6]==id2345_demo_arr[j][6]):
            post_value=6.0
        elif(id1_demo_arr[i][5]==id2345_demo_arr[j][5]):
            post_value=4.0
        elif(id1_demo_arr[i][4]==id2345_demo_arr[j][4]):
            post_value=2.0
        else:
            post_value=0.0
            
        post_value=post_value/6.0
        peoplerating_1_arr[i][j]=(gender_value+age_value+post_value)/3.0

peoplerating_1=pd.DataFrame(peoplerating_1_arr)
peoplerating_1.index=left_col_list
peoplerating_1.columns=right_col_list

peoplerating_1['ID']=left_col_list
peoplerating_1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/elsepeoplerating_1.csv', index=False)

#---------------------------------------------------------------------------------------------------------------

#train2 X train1345 인구통계학적 유사도
id_1345 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train3['ID'],date_else_train4['ID'],date_else_train5['ID'])))
id_1345['a'] = 1

id_2 = pd.DataFrame(date_else_train2['ID'])
id_2['a'] = 1

id2_demo = pd.merge(id_2, demo1, how='inner' , on='ID')
id1345_demo = pd.merge(id_1345, demo1, how='inner' , on='ID')

del id2_demo['a']
del id1345_demo['a']

#[n][0] : ID, [n][1] : 성별 , [n][2] : 나이 , [n][3] : 거주지 , [n][4],[n][5],[n][6] : 주소1,2,3
id2_demo_arr = np.array(id2_demo)
id1345_demo_arr = np.array(id1345_demo)

#peoplerating_2 인덱스에 id2_demo ID 추가
peoplerating_2=pd.DataFrame(id2_demo,columns=['ID'])
peoplerating_2.index = id2_demo['ID']
del peoplerating_2['ID']

##peoplerating_2 컬럼에 id1345_demo ID 추가
for i in list(id1345_demo['ID']):
    peoplerating_2[str(i)]=0

peoplerating_2_arr = np.array(peoplerating_2,np.float64)                  

#성별: 같을때 0, 다를때 1
#나이: 20,30,40,50,60
#주소: REGION -> CITY -> ST_PR
gender_value=0.0
age_value=0.0
post_value=0.0

left_col_list=list(peoplerating_2.index)
right_col_list = list(id1345_demo['ID'])

for i in range(len(left_col_list)):
    for j in range(0,len(right_col_list)):
        
        if(id2_demo_arr[i][1]==id1345_demo_arr[j][1]):
            gender_value=1.0
        else:
            gender_value=0.0
            
        age_value = 1 - abs((float)(id2_demo_arr[i][2])-(float)(id1345_demo_arr[j][2]))/50
        
        if(id2_demo_arr[i][6]==id1345_demo_arr[j][6]):
            post_value=6.0
        elif(id2_demo_arr[i][5]==id1345_demo_arr[j][5]):
            post_value=4.0
        elif(id2_demo_arr[i][4]==id1345_demo_arr[j][4]):
            post_value=2.0
        else:
            post_value=0.0
            
        post_value=post_value/6.0
        peoplerating_2_arr[i][j]=(gender_value+age_value+post_value)/3.0

peoplerating_2=pd.DataFrame(peoplerating_2_arr)
peoplerating_2.index=left_col_list
peoplerating_2.columns=right_col_list

peoplerating_2['ID']=left_col_list
peoplerating_2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/elsepeoplerating_2.csv', index=False)

#---------------------------------------------------------------------------------------------------------------

#train3 X train1245 인구통계학적 유사도
id_1245 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train4['ID'],date_else_train5['ID'])))
id_1245['a'] = 1

id_3 = pd.DataFrame(date_else_train3['ID'])
id_3['a'] = 1

id3_demo = pd.merge( id_3,demo1, how='inner' , on='ID')
id1245_demo = pd.merge( id_1245,demo1, how='inner' , on='ID')

del id3_demo['a']
del id1245_demo['a']

#[n][0] : ID, [n][1] : 성별 , [n][2] : 나이 , [n][3] : 거주지 , [n][4],[n][5],[n][6] : 주소1,2,3
id3_demo_arr = np.array(id3_demo)
id1245_demo_arr = np.array(id1245_demo)

#peoplerating_3 인덱스에 id3_demo ID 추가
peoplerating_3=pd.DataFrame(id3_demo,columns=['ID'])
peoplerating_3.index = id3_demo['ID']
del peoplerating_3['ID']

##peoplerating_3 컬럼에 id1245_demo ID 추가
for i in list(id1245_demo['ID']):
    peoplerating_3[str(i)]=0

peoplerating_3_arr = np.array(peoplerating_3,np.float64)                  

#성별: 같을때 0, 다를때 1
#나이: 20,30,40,50,60
#주소: REGION -> CITY -> ST_PR
gender_value=0.0
age_value=0.0
post_value=0.0

left_col_list=list(peoplerating_3.index)
right_col_list = list(id1245_demo['ID'])

for i in range(0,len(left_col_list)):
    for j in range(0,len(right_col_list)):
        
        if(id3_demo_arr[i][1]==id1245_demo_arr[j][1]):
            gender_value=1.0
        else:
            gender_value=0.0
            
        age_value = 1 - abs((float)(id3_demo_arr[i][2])-(float)(id1245_demo_arr[j][2]))/50
        
        if(id3_demo_arr[i][6]==id1245_demo_arr[j][6]):
            post_value=6.0
        elif(id3_demo_arr[i][5]==id1245_demo_arr[j][5]):
            post_value=4.0
        elif(id3_demo_arr[i][4]==id1245_demo_arr[j][4]):
            post_value=2.0
        else:
            post_value=0.0
            
        post_value=post_value/6.0
        peoplerating_3_arr[i][j]=(gender_value+age_value+post_value)/3.0

peoplerating_3=pd.DataFrame(peoplerating_3_arr)
peoplerating_3.index=left_col_list
peoplerating_3.columns=right_col_list

peoplerating_3['ID']=left_col_list
peoplerating_3.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/elsepeoplerating_3.csv', index=False)

#---------------------------------------------------------------------------------------------------------------

#train4 X train1235 인구통계학적 유사도
id_1235 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train3['ID'],date_else_train5['ID'])))
id_1235['a'] = 1

id_4 = pd.DataFrame(date_else_train4['ID'])
id_4['a'] = 1

id4_demo = pd.merge( id_4,demo1, how='inner' , on='ID')
id1235_demo = pd.merge(id_1235, demo1, how='inner' , on='ID')

del id4_demo['a']
del id1235_demo['a']

#[n][0] : ID, [n][1] : 성별 , [n][2] : 나이 , [n][3] : 거주지 , [n][4],[n][5],[n][6] : 주소1,2,3
id4_demo_arr = np.array(id4_demo)
id1235_demo_arr = np.array(id1235_demo)

#peoplerating_4 인덱스에 id4_demo ID 추가
peoplerating_4=pd.DataFrame(id4_demo,columns=['ID'])
peoplerating_4.index = id4_demo['ID']
del peoplerating_4['ID']

##peoplerating_4 컬럼에 id1235_demo ID 추가
for i in list(id1235_demo['ID']):
    peoplerating_4[str(i)]=0

peoplerating_4_arr = np.array(peoplerating_4,np.float64)                  

#성별: 같을때 0, 다를때 1
#나이: 20,30,40,50,60
#주소: REGION -> CITY -> ST_PR
gender_value=0.0
age_value=0.0
post_value=0.0

left_col_list=list(peoplerating_4.index)
right_col_list = list(id1235_demo['ID'])

for i in range(0,len(left_col_list)):
    for j in range(0,len(right_col_list)):
        
        if(id4_demo_arr[i][1]==id1235_demo_arr[j][1]):
            gender_value=1.0
        else:
            gender_value=0.0
            
        age_value = 1 - abs((float)(id4_demo_arr[i][2])-(float)(id1235_demo_arr[j][2]))/50
        
        if(id4_demo_arr[i][6]==id1235_demo_arr[j][6]):
            post_value=6.0
        elif(id4_demo_arr[i][5]==id1235_demo_arr[j][5]):
            post_value=4.0
        elif(id4_demo_arr[i][4]==id1235_demo_arr[j][4]):
            post_value=2.0
        else:
            post_value=0.0
            
        post_value=post_value/6.0
        peoplerating_4_arr[i][j]=(gender_value+age_value+post_value)/3.0

peoplerating_4=pd.DataFrame(peoplerating_4_arr)
peoplerating_4.index=left_col_list
peoplerating_4.columns=right_col_list

peoplerating_4['ID']=left_col_list
peoplerating_4.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/elsepeoplerating_4.csv', index=False)

#---------------------------------------------------------------------------------------------------------------

#train5 X train1234 인구통계학적 유사도
id_1234 = pd.DataFrame(pd.concat((date_else_train1['ID'],date_else_train2['ID'],date_else_train3['ID'],date_else_train4['ID'])))
id_1234['a'] = 1

id_5 = pd.DataFrame(date_else_train5['ID'])
id_5['a'] = 1

id5_demo = pd.merge( id_5, demo1, how='inner' , on='ID')
id1234_demo = pd.merge( id_1234, demo1, how='inner' , on='ID')

del id5_demo['a']
del id1234_demo['a']

#[n][0] : ID, [n][1] : 성별 , [n][2] : 나이 , [n][3] : 거주지 , [n][4],[n][5],[n][6] : 주소1,2,3
id5_demo_arr = np.array(id5_demo)
id1234_demo_arr = np.array(id1234_demo)

#peoplerating_5 인덱스에 id5_demo ID 추가
peoplerating_5=pd.DataFrame(id5_demo,columns=['ID'])
peoplerating_5.index = id5_demo['ID']
del peoplerating_5['ID']

##peoplerating_5 컬럼에 id1234_demo ID 추가
for i in list(id1234_demo['ID']):
    peoplerating_5[str(i)]=0

peoplerating_5_arr = np.array(peoplerating_5,np.float64)                  

#성별: 같을때 0, 다를때 1
#나이: 20,30,40,50,60
#주소: REGION -> CITY -> ST_PR
gender_value=0.0
age_value=0.0
post_value=0.0
q
left_col_list=list(peoplerating_5.index)
right_col_list = list(id1234_demo['ID'])

for i in range(0,len(left_col_list)):
    for j in range(0,len(right_col_list)):
        
        if(id5_demo_arr[i][1]==id1234_demo_arr[j][1]):
            gender_value=1.0
        else:
            gender_value=0.0
            
        age_value = 1 - abs((float)(id5_demo_arr[i][2])-(float)(id1234_demo_arr[j][2]))/50
        
        if(id5_demo_arr[i][6]==id1234_demo_arr[j][6]):
            post_value=6.0
        elif(id5_demo_arr[i][5]==id1234_demo_arr[j][5]):
            post_value=4.0
        elif(id5_demo_arr[i][4]==id1234_demo_arr[j][4]):
            post_value=2.0
        else:
            post_value=0.0
            
        post_value=post_value/6.0
        peoplerating_5_arr[i][j]=(gender_value+age_value+post_value)/3.0

peoplerating_5=pd.DataFrame(peoplerating_5_arr)
peoplerating_5.index=left_col_list
peoplerating_5.columns=right_col_list

peoplerating_5['ID']=left_col_list
peoplerating_5.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/elsepeoplerating_5.csv', index=False)
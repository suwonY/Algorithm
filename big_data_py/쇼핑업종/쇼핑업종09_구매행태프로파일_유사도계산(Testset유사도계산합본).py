import numpy as np
import pandas as pd
from operator import eq

date1 = pd.read_csv('C:/Users/dongp/Desktop/LPoint_python/date_user_base/date_rating_train1.csv', encoding='CP949')
date2 = pd.read_csv('C:/Users/dongp/Desktop/LPoint_python/date_user_base/date_rating_train2.csv', encoding='CP949')
date3 = pd.read_csv('C:/Users/dongp/Desktop/LPoint_python/date_user_base/date_rating_train3.csv', encoding='CP949')
date4 = pd.read_csv('C:/Users/dongp/Desktop/LPoint_python/date_user_base/date_rating_train4.csv', encoding='CP949')
date5 = pd.read_csv('C:/Users/dongp/Desktop/LPoint_python/date_user_base/date_rating_train5.csv', encoding='CP949')

train = pd.read_csv('C:/Users/dongp/Desktop/LPoint_python/date_user_base/habit/habit_train.csv', encoding='CP949')
test = pd.read_csv('C:/Users/dongp/Desktop/LPoint_python/date_user_base/habit/habit_test.csv', encoding='CP949')

cols = test.columns.tolist()
cols = cols[-1:]
test = test[cols]

test = pd.merge(test, train, how='inner', on='ID')

cols = date1.columns.tolist()
cols = cols[-1:]
date1 = date1[cols]
date2 = date2[cols]
date3 = date3[cols]
date4 = date4[cols]
date5 = date5[cols]

habit1 = pd.merge(date1, train, how='inner', on='ID')
habit2 = pd.merge(date2, train, how='inner', on='ID')
habit3 = pd.merge(date3, train, how='inner', on='ID')
habit4 = pd.merge(date4, train, how='inner', on='ID')
habit5 = pd.merge(date5, train, how='inner', on='ID')
train = pd.concat([habit1, habit2, habit3, habit4, habit5])

train_ID = train.ID
test_ID = test.ID

habit_base = habit5
habit_4 = pd.concat([habit1,habit2,habit3,habit4])

def getMaxMin(f):
    max = []
    min = []
    parent = []
    max.append(f.max(axis=0))
    min.append(f.min(axis=0))
    for i in range(0, 4): #len(max)
        parent.append(max[0][i] - min[0][i])
    return parent
    

def gower(f1, f2, parent):
    arr1 = np.array(f1) #1234파일 배열
    arr2 = np.array(f2) #5파일 배열
    length_record1 = len(arr1)
    length_record2 = len(arr2)
    length_data = len(arr1[0])
    
    similarity = [[0]*length_record1 for i in range(length_record2)] #2차원 배열 생성
        
    for i in range(length_record2):    #고객 개수만큼
        sim_data1 = arr2[i]              #기준 데이터
        for j in range(length_record1): #비교한것을 
            sum_data = 0
            sim_data2 = arr1[j];         #비교 데이터
            for k in range(0, 4):       # 0 ~ 3번까지는 숫자계산
                sum_data += (abs(sim_data1[k] - sim_data2[k])/parent[k]) #확률을 모두 더하기
            for k in range(4, length_data):
                if(eq(sim_data1[k], sim_data2[k])): #같으면 1 다르면 0
                    ++sum_data
            similarity[i][j] = sum_data/length_data
            

    result = pd.DataFrame(similarity)
    return result


min_max = train
del min_max['ID']
parent = getMaxMin(min_max) #최대 최소 값 구하기

file1 = train
file2 = test

index = list(file2["ID"])
column = list(file1["ID"])
del file1['ID'] #ID제외하고 계산
del file2['ID']
result = gower(file1, file2, parent)

result.columns = column
result['ID'] = cols


result.to_csv('C:/Users/dongp/Desktop/LPoint_python/date_user_base/habit/habit_train5.csv')
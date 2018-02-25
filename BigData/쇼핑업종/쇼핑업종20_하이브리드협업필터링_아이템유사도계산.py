import pandas as pd
import numpy as np
import math
from numpy import asarray
from sklearn.metrics import pairwise_distances

class Node: #ID와 유사도를 담을 노드 클래스 생성
    ID = 0
    sim = 0
    def __init__(self, ID, sim):
        self.ID = ID
        self.sim = sim

def top_N(file, n):
    col = file.columns
    arr = np.array(file) #각 행을 list형태로 만들고
    
    top_n_ID = [[0]*n for i in range(len(arr))] #2차원 배열 생성
    top_n_sim = [[0]*n for i in range(len(arr))] #2차원 배열 생성
    top_n_index = [[0]*n for i in range(len(arr))]
    for i in range(len(arr)):
        arr_temp = []
        arr_temp_index = []
        for j in range(len(arr[i])):
            arr_temp.append(Node(col[j], arr[i][j])) #
            arr_temp_index.append(Node(j, arr[i][j])) 
        arr_temp = sorted(arr_temp, key=lambda Node: Node.sim, reverse=True)
        arr_temp_index = sorted(arr_temp_index, key=lambda Node: Node.sim, reverse=True)
        for j in range(n):
            top_n_ID[i][j] = arr_temp[j].ID
            top_n_sim[i][j] = arr_temp[j].sim
            top_n_index[i][j] = arr_temp_index[j].ID
    
    return top_n_ID, top_n_sim, top_n_index

def prefer_I(p_id, file, n):
    col = file.columns
    arr = np.array(file) #각 행을 list형태로 만들고
    
    top_n_item = [[0]*n for i in range(len(arr))] #2차원 배열 생성
    prefer_sim = [[0]*n for i in range(len(arr))] #2차원 배열 생성
    prefer_item = {}
    for i in range(len(arr)):
        arr_temp = []
        list_p = []
        for j in range(len(arr[i])):
            arr_temp.append(Node(col[j], arr[i][j])) #
        arr_temp = sorted(arr_temp, key=lambda Node: Node.sim, reverse=True)
        for j in range(n):
            top_n_item[i][j] = arr_temp[j].ID
            list_p.append(top_n_item[i][j])
            prefer_sim[i][j] = arr_temp[j].sim
        prefer_item[str(p_id[i])] = list_p
    
    return prefer_item, prefer_sim

#0930이전데이터
cust_p = pd.read_csv('C:\\Users\Byun\Documents\Test\date_train_set\date_rating_train.csv', encoding='CP949').fillna(0)
test_data = pd.read_csv('C:\\Users\Byun\Documents\Test\date_train_set\date_rating_test.csv', encoding='CP949')

cust_name = []
for i in range(0,len(cust_p)):
    cust_name.append(cust_p['ID'].iloc[i])  

test_data_name = []
for i in range(0,len(test_data)):
    test_data_name.append(test_data['ID'].iloc[i]) 

item_name = []
for i in cust_p.columns[0:439]:
    item_name.append(i)


del cust_p['ID']
del test_data['ID']

cust_p = pd.DataFrame.transpose(cust_p)
cust_p.columns = cust_name
cust_p['Item'] = item_name

test_data = pd.DataFrame.transpose(test_data)
test_data.columns = test_data_name
test_data['Item'] = item_name

cust_p = pd.merge(cust_p, test_data, how='inner', on='Item')
cust_p.index = cust_p['Item']
del cust_p['Item']

cust_p = pd.DataFrame.transpose(cust_p)

recommend = pd.DataFrame()
recommend['Item'] = item_name
for r in cust_p.index:
    recommend[str(r)] = 0
recommend.index = item_name

del recommend['Item']

cust_name = []
for i in cust_p.index:
    cust_name.append(i)  


#아이템간 유사도 구할 준비
item = pd.DataFrame.transpose(cust_p)
item_copy = pd.DataFrame.transpose(cust_p)
item.columns = cust_name
item_copy.columns = cust_name


rating = 1-pairwise_distances(item, item, metric="cosine")
item['Item'] = item.index
item_base = pd.DataFrame(rating)
item_base.index = item_name
item_base.columns = item_name


#c_top_item: 피벗 아이템과 이웃한 아이템들, c_top_sim = 피벗 아이템과 이웃한 아이템들의 유사도
top = 31
c_top_item, c_top_sim, c_top_index = top_N(item_base, top)


c_top_item = pd.DataFrame(c_top_item)
c_top_item.index = item_name

c_top_sim = pd.DataFrame(c_top_sim)
c_top_sim.index = item_name

c_top_index = pd.DataFrame(c_top_index)
c_top_index.index = item_name

del c_top_item[0]
del c_top_sim[0]
del c_top_index[0]

c_top_item = np.array(c_top_item)
c_top_sim = np.array(c_top_sim)
c_top_index = np.array(c_top_index)

c_top_item = pd.DataFrame(c_top_item)
c_top_sim = pd.DataFrame(c_top_sim)
c_top_index = pd.DataFrame(c_top_index)
c_top_item.index = item_name
c_top_sim.index = item_name
c_top_index.index = item_name

arr_top_item = np.array(c_top_item)

#추천 선호도 데이터 입력할 2차원 배열 생성
target_matrix = [[0 for col in range(len(c_top_item))] for row in range(len(c_top_item))]

#0930이전구매테이블(piv_merge)에서 pivot고객의 구매빈도평균
Pu = [] 
arr_piv = np.array(item_copy) 

for i in range(0,len(item_copy)): #pivot고객의 구매빈도평균값에 접근
    cnt = 0
    for j in range(0,len(item_copy.columns)):
        if(arr_piv[i][j] > 0):
            cnt = cnt + 1           
    Pu.append(sum(arr_piv[i])/cnt)

#Top-n으로 추출한 고객의 이웃customer고객들 간의 유사도 합
Ruv1 = []
Ruv2 = [[0 for col in range(len(c_top_item.columns))] for row in range(len(c_top_item))]
arr_piv_neigh = np.array(c_top_sim)
Ruv2 = arr_piv_neigh #Ruv2[pivot고객index][이웃고객index]
for i in range(0,439):
    Ruv1.append(sum(arr_piv_neigh[i]))


# 439 x 1600
#0930이후데이터테이블(test,copy(id제거))에서 pivot고객의 이웃고객들에 대한 상품i의 빈도, Pvi
new_neighbor = pd.DataFrame()
neighbor_id = []
P_cal = [0] * 9829
result = [0] * 9829
cnt = 0
Pv_test = []
for i in range(0,439):
    new_neighbor['Item'] = arr_top_item[i]
    new_nei_t = pd.merge(new_neighbor, item, how='inner', on='Item') #5행(고객u에 대한 이웃5명)
    del new_nei_t['Item']
    arr_nei_t = np.array(new_nei_t) #이웃고객5명에 대한 상품(i~439) 테이블을 배열로
    
    cnt = 0
    for j in range(0,len(item_copy.columns)):
        if(arr_piv[i][j] > 0):
            cnt = cnt + 1           
    Pu.append(sum(arr_piv[i])/cnt)
    

    #5행을 배열로 만들어줘서 한행의 모든 데이터를 한번에 연산
    #Pv각 아이템(1~439) 값에 Pv평균 빼주기
    for m in range(0,len(new_nei_t)):
        for n in range(0,len(new_nei_t.columns)):
            if(arr_nei_t[m][n] > 0):
                cnt = cnt + 1
        Pvi = arr_nei_t[m] # 1~439 아이템
        neighbor = c_top_index[m][i]
        P_cal = P_cal + (Ruv2[i][m] * (Pvi - Pu[neighbor])) #1~30이웃고객 누적값 Pu[neighbor]
        cnt = 0
    
    result = Pu[i] + (P_cal / Ruv1[i])
    target_matrix[i] = result  #최종결과값 저장!
    # 초기화
    P_cal = [0] * 9829
    result = [0] * 9829

#Dataframe에 결과값 저장
recom = pd.DataFrame(target_matrix)
recom = pd.DataFrame.transpose(recom)

recom.index = cust_name
recom.columns = item_name

recom['ID'] = recom.index

#recom.to_csv('C:\\Users\\Byun\\Documents\\Data\\csv\\item_base.csv')
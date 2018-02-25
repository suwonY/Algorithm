import pandas as pd 
import numpy as np  # 수치 연산 라이브러리
import time
#9월 30일 이전
start = time.time()

id_train1_current = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train1.csv', sep=',', dtype={'ID':str} ,engine='python')
id_train2_current = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train2.csv', sep=',', dtype={'ID':str} ,engine='python')
id_train3_current = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train3.csv', sep=',', dtype={'ID':str} ,engine='python')
id_train4_current = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train4.csv', sep=',', dtype={'ID':str} ,engine='python')
id_train5_current = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train5.csv', sep=',', dtype={'ID':str} ,engine='python')
id_test_current = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_testset.csv', sep=',', dtype={'ID':str} ,engine='python')
simil= pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_test_cos_rating.csv' , sep=',', engine='python',
                                   dtype= {'ID' : str})

#9월 30일 이후
id_train_future = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_after.csv', sep=',', dtype={'ID':str} ,engine='python')

P_u1_current=id_test_current
P_u4_current=pd.concat((id_train1_current,id_train2_current,id_train3_current, id_train4_current, id_train5_current))


#원래 P_u1의 미래 데이터
P_u1_original_future=pd.merge(id_train_future,pd.DataFrame(P_u1_current['ID']),on='ID', how='inner')
P_u1_original_future.index=P_u1_current['ID']

P_u1_current.index=P_u1_current['ID']
P_u4_current.index=P_u4_current['ID']

del P_u1_current['ID']
del P_u4_current['ID']
del P_u1_original_future['ID']

P_u1_current_arr=np.array(P_u1_current,dtype=object)
P_u4_current_arr=np.array(P_u4_current,dtype=object)


#P_u1의 현재와 P_u4의 현재를 통해 구한 P_u1의 미래 원본 데이터
P_u1_calculate_future=P_u1_current_arr



#top선정 개수
top_cnt=30

#추천하려는 개수
recommend=3;
'''
사람-사람
'''
# Top N 만들기
class Node: #ID와 유사도를 담을 노드 클래스 생성
    ID = 0
    sim = 0
    def __init__(self, ID, sim):
        self.ID = ID
        self.sim = sim


def top_N(file, n):
    index = file.index
    col = file.columns
    arr = np.array(file) #각 행을 list형태로 만들고
    
    top_n = [[0]*n for i in range(len(arr))] #2차원 배열 생성
    top_n2 = [[0]*n for i in range(len(arr))] #2차원 배열 생성
    for i in range(len(arr)):
        arr_temp = []
        for j in range(len(arr[0])):
            arr_temp.append(Node(col[j], arr[i][j])) #
        arr_temp = sorted(arr_temp, key=lambda Node: Node.sim, reverse=True)
        for j in range(n):
            top_n[i][j] = arr_temp[j].ID
            top_n2[i][j] = arr_temp[j].sim
    
    
    return top_n,top_n2

Pu1idx = pd.DataFrame(P_u1_current.index)
Ruv = pd.merge(Pu1idx, simil, how='inner' , on = 'ID')

simil['ID']
del Ruv['ID']
P_u1_current.index=Ruv.index
P_u4_current.index=Ruv.columns


(top_Ruv_ID,top_Ruv_Similarity)=top_N(Ruv,top_cnt)


#ID별로 pv값 배열로 바꿔서 구하기
P_u4_current_arr2=np.zeros((20000,len(P_u4_current.columns)))
for i in range(0,len(P_u4_current.index)):
    for j in range(0,len(P_u4_current.columns)):
        P_u4_current_arr2[int(P_u4_current.index.values[i])][j]=P_u4_current_arr[i][j]


#ID별로 pv값의 합의 평균 배열로 바꿔서 구하기       
P_u4_current_avg=np.zeros(20000)
for i in range(0,len(P_u4_current.index)):
    k=0
    cnt=0
    for j in range(0,len(P_u4_current.columns)): #ID와 'avg' column 빼준다
        if P_u4_current_arr[i][j]!=0:
            k+=int(P_u4_current_arr[i][j])
            cnt+=1
    
    k/=cnt
    P_u4_current_avg[int(P_u4_current.index.values[i])]=k #ID에 해당하는 평균 값



#PU (공식 왼쪽)   
#이웃들의 유사도값의 합
sigma_Pu=np.zeros(len(P_u1_current.index))
sigma_Ruv=np.zeros(len(P_u1_current.index))
for i in range(0,len(P_u1_current.index)):
    k=0
    cnt=0
    for j in range(0,len(P_u1_current.columns)): #마지막 id 빼준다
       if int(P_u1_current_arr[i][j])!=0:
           k+=int(P_u1_current_arr[i][j])
           cnt+=1
    
    k/=cnt
    sigma_Pu[i]=k
    
    k=0
    for j in range(0,top_cnt):
       k+=top_Ruv_Similarity[i][j]
    
    sigma_Ruv[i]=k




#train1의 인덱스 만큼
for i in range(0,len(P_u1_current.index)):
    
    
    #col별로(마지막은 ID값 있어서 제외)
    for col in range(0,len(P_u1_current.columns)):
        
        ans=0.0
        
        #해당 인덱스의 이웃들의 col에 대한 유사도 값 별로
        for j in range(0,top_cnt):
            
            
            #이웃의 ID
            neighbor=int(top_Ruv_ID[i][j])
            ans+=top_Ruv_Similarity[i][j]*(P_u4_current_arr2[neighbor][col]-P_u4_current_avg[neighbor])
        
        
        ans/=sigma_Ruv[i]
        ans+=sigma_Pu[i]
        P_u1_calculate_future[i][col]=ans


P_u1_calculate_future = pd.DataFrame(P_u1_calculate_future)
P_u1_calculate_future.index = P_u1_original_future.index     
P_u1_calculate_future.columns = P_u1_original_future.columns  


def recommend_N(file, n):
    index = file.index
    col = file.columns
    arr = np.array(file) #각 행을 list형태로 만들고
    
    top_n = [[0]*n for i in range(len(arr))] #2차원 배열 생성
    top_n2 = [[0]*n for i in range(len(arr))] #2차원 배열 생성
    for i in range(len(arr)):
        arr_temp = []
        for j in range(len(arr[0])):
            arr_temp.append(Node(col[j], arr[i][j])) #
        arr_temp = sorted(arr_temp, key=lambda Node: Node.sim, reverse=True)
        for j in range(n):
            top_n[i][j] = arr_temp[j].ID
            top_n2[i][j] = arr_temp[j].sim
    
    
    return top_n,top_n2


(result_item,result_value)=recommend_N(P_u1_calculate_future,recommend)
result_item=pd.DataFrame(result_item)
result_item.index=P_u1_calculate_future.index

#사전 생성하는 함수 정의
def makeDic(col_list, a1):
    a = list(a1)
    dic=[]
    for i in range(len(a)):
        dic.append((col_list[i], a[i]))
    
    return dict(dic)
#top-N 추출하는 함수 정의   
def topN(dic, n):
    topn = sorted(dic, key=dic.get, reverse=True)[:n]    
    return topn

def topN2(dic, n):
    topn = sorted(dic.items(), key=lambda x: -x[1])[:n]    
    return topn

id_train_future.index = id_train_future['ID']
del id_train_future['ID']
col_list_test = list(id_train_future.columns)

#Eval 정의
eval = []

#Lift-ratio
lift_ratio=[]

for i in range(len(result_item)):
    r1 = list(result_item.ix[i])   
    rindex = result_item.index[i]
    rdic = makeDic(col_list_test, id_train_future.ix[rindex])
    rtop = topN(rdic,1)
    rectop = r1[0]
    if rtop[0] == rectop:
        lift_ratio.append(1)
    else:
        lift_ratio.append(0)
    
eval.append(np.sum(lift_ratio)/2171)    

#coverage
coverage1 = []
for i in range(3):
    coverage1.append(list(result_item[i].unique()))
    
coverage2 = []
for i in range(3):
    for j in range(len(coverage1[i])):
        coverage2.append(coverage1[i][j])
        
coverage= list(set(coverage2))        
eval.append(len(coverage)/9)

#Recall
def T(dic):
    real =[name for name, score in dic.items() if score > 0] 
    return real

rcnt=0
rsum1=0
rsum2=0
recall1=[]
for i in range(len(result_item)):
    r1 = list(result_item.ix[i])   
    rindex = result_item.index[i]
    rdic = makeDic(col_list_test, id_train_future.ix[rindex])
    rreal = T(rdic)
    for j in rreal:
          if j in r1:
                rcnt+=1
    rsum1 = rcnt
    rsum2 = len(rreal)
    try : ravg = rsum1/rsum2
    except : ravg = 0
    recall1.append(ravg)
    ravg=0
    rsum1=0
    rsum2=0
    rcnt = 0

recall = np.mean(recall1)   
eval.append(recall)

#Precision == accuracy
rcnt=0
rsum1=0
rsum2=0
precision1=[]
for i in range(len(result_item)):
    r1 = list(result_item.ix[i])
    rindex = result_item.index[i]  
    rdic = makeDic(col_list_test, id_train_future.ix[rindex])
    rreal = T(rdic)
    for j in r1:
          if j in rreal:
                rcnt+=1
    rsum1 = rcnt
    rsum2 = 3
    ravg = rsum1/rsum2
    precision1.append(ravg)
    ravg=0
    rsum1=0
    rsum2=0
    rcnt = 0
    
precision= np.mean(precision1) 
eval.append(precision)

#F1-measeure
f1 =2*recall*precision/(recall+precision)
eval.append(f1)

#Diversity
id_train_current = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train.csv', sep=',', dtype={'ID':str} ,engine='python')
id_train_current.index = id_train_current['ID']
del id_train_current['ID']

diversity = []
for i in result_item.index: 
    testdic = makeDic(col_list_test, id_train_current.ix[i] )  
    testdic2 = [ID for ID, rating  in testdic.items() if rating > 0]    
    testrec = list(result_item.ix[i])    
    sum = len(testrec)
    cnt = 0    
    for i in testrec:
        if i not in testdic2:
            cnt += 1
    div = cnt/sum
    diversity.append(div)
    cnt = 0
    sum = 0
    
np.mean(diversity)
eval.append(np.mean(diversity))

#Personality
#개인화 정도 ()

result_item_arr = np.array(result_item)

individual_ans=0
for i in range(0,len(result_item.index)):
    
    set1 = set()
    for j in range(0,len(result_item.columns)):
        set1.add(result_item_arr[i][j])
        
    cnt=0
    for j in range(0,len(result_item.index)):
        if i==j:
            continue
                
        set2 = set()
        for k in range(0,len(result_item.columns)):
            set2.add(result_item_arr[j][k])
            
        cnt+=len(set1-set2)/len(result_item.columns)
    
    individual_ans+=cnt/(len(result_item.index)-1)
    
individual_ans/=len(result_item.index)

eval.append(individual_ans)
 
print(eval)
end = time.time() - start 
print(end)
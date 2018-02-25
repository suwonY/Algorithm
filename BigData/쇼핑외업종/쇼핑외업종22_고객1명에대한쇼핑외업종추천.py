import pandas as pd 
import numpy as np  # 수치 연산 라이브러리
import time
from sklearn.metrics import pairwise_distances
from operator import eq
from sklearn.preprocessing import robust_scale
#9월 30일 이전


#고객 Data 불러오기
customer_data = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_else_train.csv', sep=',', dtype={'ID':str} ,engine='python')
customer_data.index = customer_data['ID']


demo = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/people_profile_total.csv', sep=',', engine ='python' , dtype=str)
demo = pd.merge(pd.DataFrame(customer_data['ID']), demo , how='inner', on ='ID')
demo.index = demo['ID']

habit = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit.csv', sep=',', engine ='python' ,  
                    dtype={'ID':str, 'bam_sum' : float , 'bam_cv': float, 'bct_sum' : float , 'bct_cv' :float})
habit.index = habit['ID']

habit[['bam_sum','bct_sum']] = robust_scale(habit[['bam_sum','bct_sum']])

add_else = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_else.csv', sep=',', engine ='python',)
add_else = add_else.drop_duplicates()

demo_add = pd.merge(demo, add_else, how='left' , on=('CITY','ST_PR')).fillna(0)
add_col = list(add_else.columns)
place =pd.concat((demo_add['ID'] , demo_add[add_col]),axis=1)
place = pd.merge(pd.DataFrame(customer_data['ID']), place, how='inner' , on='ID')
del place['CITY']
del place['ST_PR']

place.index = place['ID']

start = time.time()

s = input()
index = s

#input받은 ID와 나머지로 데이터 분리
customer = pd.DataFrame(customer_data.loc[index]).T
train_cus = customer_data.drop(index, 0)

demo_customer = pd.DataFrame(demo.loc[index]).T
demo_train_cus = demo.drop(index,0)

habit_customer = pd.DataFrame(habit.loc[index]).T
habit_train_cus = habit.drop(index,0)

place_customer = pd.DataFrame(place.loc[index]).T
place_train_cus = place.drop(index,0)
#9월 30일 이후

P_u1_current=customer
P_u4_current=train_cus

#원래 P_u1의 미래 데이터
P_u1_current.index=P_u1_current['ID']
P_u4_current.index=P_u4_current['ID']

del P_u1_current['ID']
del P_u4_current['ID']

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

#유사도계산
#cosine 유사도 계산
cos_rating = 1 - pairwise_distances(customer, train_cus, metric="cosine")
cos = pd.DataFrame(cos_rating)
cos.index = customer.index
cos.columns = list(train_cus.index)
#인구통계 유사도 계산
#[n][0] : ID, [n][1] : 성별 , [n][2] : 나이 , [n][3] : 거주지 , [n][4],[n][5],[n][6] : 주소1,2,3
id1_demo_arr = np.array(demo_customer)
id12345_demo_arr = np.array(demo_train_cus)

#people 인덱스에 id1_demo ID 추가
people=pd.DataFrame(demo_customer,columns=['ID'])
people.index = demo_customer['ID']
del people['ID']

##people 컬럼에 id12345_demo ID 추가
for i in list(demo_train_cus['ID']):
    people[str(i)]=0

people_arr = np.array(people,np.float64)                  

#성별: 같을때 0, 다를때 1
#나이: 20,30,40,50,60
#주소: REGION -> CITY -> ST_PR
gender_value=0.0
age_value=0.0
post_value=0.0

left_col_list=list(people.index)
right_col_list = list(demo_train_cus['ID'])

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
        people_arr[i][j]=(gender_value+age_value+post_value)/3.0

people=pd.DataFrame(people_arr)
people.index=left_col_list
people.columns=right_col_list
#구매행태 유사도 계산

habit_rating = 1 - pairwise_distances(habit_customer, habit_train_cus, metric="cosine")
habit_rating = pd.DataFrame(habit_rating)
habit_rating.index= habit_customer.index
habit_rating.columns = list(habit_train_cus.index)

#거주지유사도계산
place_rating = 1 - pairwise_distances(place_customer, place_train_cus, metric="rogerstanimoto")
place = pd.DataFrame(place_rating)
place.index = place_customer.index
place.columns = list(place_train_cus.index)

#유사도 합치기
simil =0.251822771*people + 0.249338995*habit_rating + 0.25358545*cos+0.245252784*place

P_u1_current.index=simil.index
P_u4_current.index=simil.columns

(top_Ruv_ID,top_Ruv_Similarity)=top_N(simil,top_cnt)

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
P_u1_calculate_future.index = customer.index     
P_u1_calculate_future.columns = customer.columns  


#사전 생성하는 함수 정의
def makeDic(col_list, a1):
    a = list(a1)
    dic=[]
    for i in range(len(a)):
        dic.append((col_list[i], a[i]))
    
    return dict(dic)

def topN(dic, n):
    topn = sorted(dic, key=dic.get, reverse=True)[:n]    
    return topn
P_u1_calculate_future.columns = list(('호텔','여행사','면세점','영화관','테마파크','야구관람','패스트푸드','패밀리레스토랑','카페'))

dic= makeDic(P_u1_calculate_future.columns, P_u1_calculate_future.loc[index])
top_3 = topN(dic,3)

for i in top_3:                
    print("당신의 추천 서비스는" ,end=' ')
    print(i , end='')
    print("입니다.")

end = time.time() - start 
print(end)

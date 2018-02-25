import pandas as pd
import numpy as np

#데이터 셋 불러오기
habit_bam_bct = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_bam_bct.csv', sep=',', engine ='python' , dtype={'ID' : np.object_})

dayname_result = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/dayname_result.csv', sep=',', engine ='python' , dtype={'ID' : np.object_ , 'pred' : np.object_}  )

time_result = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/before0930_timehabit.csv', sep=',', engine ='python' , dtype= np.str )

time_result2 = time_result[['ID', 'Habit']]

#데이터 셋 컬럼명 변경
time_result2.columns = ('ID', 'time_habit')
dayname_result.columns=('ID', 'day_habit')

#요일 컬럼 범주명 변경 함수 정의
#(0 = 'weekday' , 1='mix' , 2='weekend')
def dayhabit(row):
    if row['day_habit'] == '0':
        dayname = 'weekday'
    elif row['day_habit'] == '1':
        dayname = 'mix'
    else:
        dayname = 'weekend'
    return dayname

#범주명 변경 함수 적용
dayname_result['dayhabit'] = dayname_result.apply(dayhabit,axis=1)

del dayname_result['day_habit']

#데이터 셋 조인
demo_habit = pd.merge(habit_bam_bct,dayname_result, how='inner' , on='ID')

demo_habit2 = pd.merge(demo_habit, time_result2,how='inner' , on='ID')

demo_habit2.index = demo_habit2['ID']


#내보내기
demo_habit2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/demo_habit.csv' , sep=',' , index=False)
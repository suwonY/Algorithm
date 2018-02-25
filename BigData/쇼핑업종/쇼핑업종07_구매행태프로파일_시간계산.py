import pandas as pd
import numpy as np

#고객 & 쇼핑 데이터 가져오기
target = pd.read_csv('C:\\Users\Byun\Documents\Data\Test\date_train.csv', encoding='CP949')
shoping = pd.read_csv('C:\\Users\Byun\Documents\Data\ShopingPBUY.txt')

customer = pd.DataFrame()
saveId = []
for i in target['ID']:
    saveId.append(i)
    
customer['ID'] = saveId

df = pd.merge(customer, shoping, how='inner', on='ID')
df = pd.DataFrame(df, columns=['ID', 'RCT_NO', 'DE_DT', 'DE_HR', 'BIZ_UNIT']).sort_values(by=['ID', 'DE_HR'], ascending=True)
df = df[(df['DE_DT'] <= 20150930)]

#5자리 아이디 빈공간 0채우기
s = []
for i in range(0, len(df)):
    idx = df['ID'].iloc[i]
    idx = '%05d' % (int(idx))
    str(idx)
    s.append(idx)
df['ID'] = s


df.index = df['ID']

dfM = pd.DataFrame()
dfA = pd.DataFrame()
dfJ = pd.DataFrame()

#시간 필터링
dfM = df[df.DE_HR < 13]
dfA = df[df.DE_HR >= 13]
dfJ = df[(df['DE_HR'] >= 9) & (df['DE_HR'] <= 18)]

countM = []
for i in range(0, len(dfM)):
    countM.append(1)
dfM['Morning'] = countM

countA = []
for i in range(0, len(dfA)):
    countA.append(1)
dfA['Afternoon'] = countA

countJ = []
for i in range(0, len(dfJ)):
    countJ.append(1)
dfJ['Worktime'] = countJ

dfM = pd.DataFrame(dfM, columns=['ID', 'Morning']).sort_values(by='ID', ascending=True)
dfM = dfM.groupby(['ID']).sum()

dfA = pd.DataFrame(dfA, columns=['ID', 'Afternoon']).sort_values(by='ID', ascending=True)
dfA = dfA.groupby(['ID']).sum()

dfJ = pd.DataFrame(dfJ, columns=['ID', 'Worktime']).sort_values(by='ID', ascending=True)
dfJ = dfJ.groupby(['ID']).sum()


del df['RCT_NO']
del df['DE_HR']
df = df.drop_duplicates(['ID'], keep = 'last')

result = pd.DataFrame()
result = df

df = pd.concat([df, dfM], axis=1)
df = pd.concat([df, dfA], axis=1)
df = pd.concat([df, dfJ], axis=1)
df = df.fillna(0)

# 구매 습관 구하기
habit = []
for i in range(0, len(df)):
    if((df['Morning'].iloc[i]) < (df['Afternoon'].iloc[i]) and (df['Worktime'].iloc[i]) < (df['Afternoon'].iloc[i])):
        habit.append("Afternoon")
    elif((df['Morning'].iloc[i]) < (df['Afternoon'].iloc[i]) and (df['Worktime'].iloc[i]) > (df['Afternoon'].iloc[i])):
        habit.append("Worktime")
    elif((df['Morning'].iloc[i]) > (df['Afternoon'].iloc[i]) and (df['Morning'].iloc[i]) > (df['Worktime'].iloc[i])):
        habit.append("Morning")
    elif((df['Morning'].iloc[i]) > (df['Afternoon'].iloc[i]) and (df['Morning'].iloc[i]) < (df['Worktime'].iloc[i])):
        habit.append("Worktime")
    else:
        if((df['Morning'].iloc[i]) == (df['Afternoon'].iloc[i])):
            habit.append("Balance")
        elif((df['Worktime'].iloc[i]) == (df['Afternoon'].iloc[i])):
            habit.append("Balance")
        elif((df['Worktime'].iloc[i]) == (df['Morning'].iloc[i])):
            habit.append("Balance")
            
df['Habit'] = habit

del df['Morning']
del df['Afternoon']
del df['Worktime']
del df['BIZ_UNIT']

#df.to_csv('C:\\Users\\Byun\\Documents\\Data\\timehabit.csv')

import pandas as pd

add_롯데리아 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_롯데리아.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_엔제리너스 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_엔제리너스.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_세븐일레븐 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_세븐일레븐.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_롯데슈퍼 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_롯데슈퍼.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_롯데마트 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_롯데마트.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_프라이데이스 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_T.G.I.프라이데이스.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_롯데백화점 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_롯데백화점.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_롯데시네마 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/롯데시네마.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_롯데호텔 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/롯데호텔.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_롯데면세점 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/롯데면세점.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})
add_롭스 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_롭스.csv' , sep=',', engine ='python' , dtype={'CITY': str, 'ST_PR' : str})

add = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/Add2.csv', sep=',', engine ='python' , dtype=str)

add.ix[60] = '세종 0'
city = add['Address'].str.split(' ')

city1 = pd.DataFrame([city[x][0] for x in range(len(city))] )
st_pr = pd.DataFrame([city[x][1] for x in range(len(city))] )

Add = pd.concat((city1, st_pr),axis=1)
Add.columns = ('CITY', 'ST_PR')
sejong = ['세종',0,1]
sejong2 = pd.DataFrame(sejong )
sejong2 =sejong2.T
sejong2.columns = ('CITY', 'ST_PR', '세븐일레븐')
add_세븐일레븐 = add_세븐일레븐.append(sejong2)

sejong2.columns = ('CITY', 'ST_PR', '엔제리너스')
add_엔제리너스 = add_엔제리너스.append(sejong2)

sejong2.columns = ('CITY', 'ST_PR', '롯데슈퍼')
add_롯데슈퍼 = add_롯데슈퍼.append(sejong2)

sejong2.columns = ('CITY', 'ST_PR', '롯데백화점')
add_롯데백화점 = add_롯데백화점.append(sejong2)

add_buy1 = pd.merge(Add, add_롯데백화점, how='left', on=('CITY','ST_PR')  )
add_buy2 = pd.merge(add_buy1, add_롯데마트, how='left', on=('CITY','ST_PR')  )
add_buy3 = pd.merge(add_buy2,add_롯데슈퍼, how='left', on=('CITY','ST_PR'))
add_buy5 = pd.merge(add_buy3, add_롭스, how='left' ,  on=('CITY','ST_PR'))
add_buy6 = add_buy5.fillna(0)

def Inttoone(col):
    newcol = []
    for i in col:
        if i > 0 :
            newcol.append(1)
        else:
            newcol.append(0)
    return newcol
add_buy6.dtypes    


add_buy = pd.concat((add_buy6[['CITY','ST_PR']],add_buy6[['롯데백화점', '롯데마트','롯데슈퍼', '롭스']].apply(Inttoone,0)),axis=1)

add_else1 = pd.merge(Add,add_롯데리아,how='left', on=('CITY','ST_PR')  )
add_else2 = pd.merge(add_else1,add_엔제리너스,how='left', on=('CITY','ST_PR')  )
add_else3 = pd.merge(add_else2,add_프라이데이스,how='left', on=('CITY','ST_PR')  )
add_else4 = pd.merge(add_else3,add_롯데시네마,how='left', on=('CITY','ST_PR')  )
add_else5 = pd.merge(add_else4,add_롯데호텔,how='left', on=('CITY','ST_PR')  )
add_else6 = pd.merge(add_else5,add_롯데면세점,how='left', on=('CITY','ST_PR')  )

add_else7 = add_else6.fillna(0)

add_else = pd.concat((add_else7[['CITY','ST_PR']],add_else7[['롯데리아', '엔제리너스','T.G.I.프라이데이스','롯데시네마', '롯데시티호텔','롯데면세점']].apply(Inttoone,0)),axis=1)

add_buy.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_buy.csv' , index=False)  

add_else.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/add_else.csv' , index=False)  

from bs4 import BeautifulSoup
import numpy as np
from selenium import webdriver
import pandas as pd
import time

demo1 = pd.read_csv('C:/Users/dongp/Desktop/LPoint_python/Add.csv', sep=',', dtype=str, encoding="CP949")
addresult=[]
city = list(demo1['Address'])

driver = webdriver.Chrome('C:/chrome driver/chromedriver_win32/chromedriver')
driver.implicitly_wait(3)

#검색어와 검색 카테고리를 설정해서 예외처리
search_name = '롯데백화점'
except_name = '백화점'     

for z in city:
    try : addtemp=z.split(' ')[1]
    except: addtemp = z.split(' ')[0]   
    address = []
    
    driver.get('http://map.daum.net//')
    driver.find_element_by_css_selector('input[type="text"]').send_keys(z+" "+search_name)
    where = z.split(' ')
    driver.find_element_by_css_selector('button[type="button"]').click()
    
    address=[]
    while True:
        time.sleep(1.5)
        html = driver.page_source
        soup = BeautifulSoup(html, 'html.parser')
        
        lotte_name = soup.select('.placelist .PlaceItem a[data-id="name"]') #이름
        lotte_add = soup.select('.placelist .PlaceItem p[data-id="newaddr"]') #주소
        lotte_bnd = soup.select('.infowrap .subcategory')
        
        #---------------------------검색어랑 다른 결과 배제-----------------------------
        check = np.zeros(len(lotte_add))
        i=-1
        for n in lotte_name:
            i+=1
            try : add = n.text.strip().split(' ')
            except : continue
            if add[0].find(search_name)!=0: continue
            check[i] = 1
        i=-1
        for n in lotte_bnd:
            i+=1
            bnd = n.text
            if except_name.find(bnd)>=0: continue
            check[i] = 0
        
        i = -1
        for n in lotte_add:
            i+=1
            if check[i]==0: continue
            if len(n.text)==0: continue
            compare = ""
            try:
                add = n.text.strip().split(' ')
                compare = add[1]
            except :
                try: compare = add[0]
                except: continue
            
            flag = False
            for k in range(len(where)):
                if where[k].find(compare)==0:
                    flag = True
                    break
            if flag: 
                address.append(add[0] + " " + add[1])
            
        try : 
            driver.find_element_by_css_selector('.places .more').click()
        except: 
            try : 
                driver.find_element_by_css_selector('.pages .ACTIVE+a').click() #다음 페이지 누르기
            except:
                break
            
    addresult.append(address)
    print(z)

addsum=[]
for k in range(len(addresult)):
    addcnt = len(addresult[k])
    if addcnt != 0:
        city ,st_pr = addresult[k][0].split(' ')
        addsum.append((city,st_pr,addcnt)) 

add_sum = pd.DataFrame(addsum, columns=('CITY','ST_PR',search_name))
add_sum.to_csv('C:/Users/dongp/Desktop/LPoint_python/롯데백화점.csv', index=False)

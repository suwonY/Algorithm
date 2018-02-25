import pandas as pd
import numpy as np

for z in range(1,6):
    habit = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_train'+str(z)+'.csv', sep=',' , engine='python',
                               dtype={'ID':str})
    people = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/peoplerating_'+str(z)+'.csv', sep=',' , engine='python',
                               dtype={'ID':str})
    cos=pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/date_cos_rating'+str(z)+'.txt', sep=',' , engine='python',
                               dtype={'ID':str})
    place = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/tanimoto_rating'+str(z)+'.csv', sep=',' , engine='python',
                               dtype={'ID':str})
    
    del habit['Unnamed: 0']
    
    s = []
    for i in range(0, len(habit)):
        idx = habit['ID'].iloc[i]
        idx = '%05d' % (int(idx))
        str(idx)
        s.append(idx)
    habit['ID'] = s
    
    id_habit = habit['ID']
    del habit['ID']
    
    col_habit = list(habit.columns)
    col_habit2 = []
    for i in col_habit:
        col_habit2.append(i.zfill(5))
        
    habit.columns = col_habit2
    
    habit['ID'] = id_habit
    
    habit.index = habit['ID']
    people.index = people['ID']
    cos.index = cos['ID']
    place.index =place['ID']
    del habit['ID']
    del people['ID']
    del cos['ID']
    del place['ID']
    
    mean_rating_1 = (people + habit + cos+place)/4
    
    weight_rating_1 = 0.211761885*people + 0.129378119*habit + 0.469501415*cos + 0.189358581*place
    
    
    mean_rating_1['ID'] = mean_rating_1.index
    weight_rating_1['ID'] = weight_rating_1.index
    
    
    mean_rating_1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/mean_rating_'+str(z)+'.csv', index=False)
    weight_rating_1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/weight_rating_'+str(z)+'.csv', index=False)

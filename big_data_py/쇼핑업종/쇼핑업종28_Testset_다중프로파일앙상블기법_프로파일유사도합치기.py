import pandas as pd
import numpy as np

habit = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_test.csv', sep=',' , engine='python',
                           dtype={'ID':str})
people = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_peoplerating.csv', sep=',' , engine='python',
                           dtype={'ID':str})
cos=pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_cos_rating.csv', sep=',' , engine='python',
                           dtype={'ID':str})
place = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_tanimoto_rating.csv', sep=',' , engine='python',
                               dtype={'ID':str})
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
place.index = place['ID']
del habit['ID']
del people['ID']
del cos['ID']
del place['ID']

weight_rating_1 =  0.211987118*people + 0.12103169*habit + 0.472582027*cos + 0.194399166*place
weight_rating_1['ID'] = weight_rating_1.index
weight_rating_1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_weight_rating.csv', index=False)

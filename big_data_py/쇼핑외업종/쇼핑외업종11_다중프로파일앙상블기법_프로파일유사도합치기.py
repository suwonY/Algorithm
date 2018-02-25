import pandas as pd
import numpy as np
for i in range(1,6):    
    habit = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_habit_rating'+ str(i) + '.csv', sep=',' , engine='python',
                               dtype={'ID':str})
    people = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/elsepeoplerating_'+ str(i) +'.csv', sep=',' , engine='python',
                               dtype={'ID':str})
    cos=pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_cos_rating'+ str(i) +'.csv', sep=',' , engine='python',
                               dtype={'ID':str})
    place = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/tanimoto_else_rating'+ str(i) +'.csv', sep=',' , engine='python',
                               dtype={'ID':str})   
    habit.index = habit['ID']
    people.index = people['ID']
    cos.index = cos['ID']
    place.index = place['ID']
    del habit['ID']
    del people['ID']
    del cos['ID']
    del place['ID']
    mean_rating = (people+habit+cos+place)*0.25
    weight_rating = 0.248724374*people + 0.250941294*habit + 0.253041132*cos +0.2472932*place
    
    mean_rating['ID'] = mean_rating.index
    weight_rating['ID'] = weight_rating.index
    
    weight_rating.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/elseweight_rating_'+str(i)+'.csv', index=False)
    
    mean_rating.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/elsemean_rating_'+str(i)+'.csv', index=False)

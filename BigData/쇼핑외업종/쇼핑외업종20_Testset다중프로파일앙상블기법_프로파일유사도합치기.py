import pandas as pd
import numpy as np

habit = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_else_habit_rating.csv', sep=',' , engine='python',
                           dtype={'ID':str})
people = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_test_peoplerating.csv', sep=',' , engine='python',
                           dtype={'ID':str})
cos=pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/else_test_cos_rating.csv', sep=',' , engine='python',
                           dtype={'ID':str})
place = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_tanimoto_else_rating.csv', sep=',' , engine='python',
                               dtype={'ID':str})
habit.index = habit['ID']
people.index = people['ID']
cos.index = cos['ID']
place.index =place['ID']
del habit['ID']
del people['ID']
del cos['ID']
del place['ID']

weight_rating = 0.251822771*people + 0.249338995*habit + 0.25358545*cos+0.245252784*place

weight_rating['ID'] = weight_rating.index

weight_rating.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/test_else_weight_rating.csv', index=False)

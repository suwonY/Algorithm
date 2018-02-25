import pandas as pd


habit_rating_1 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_1.csv', sep=',' , engine='python',
                           dtype={'ID':str})

del habit_rating_1['Unnamed: 0']
habit_rating_1.index = habit_rating_1['ID']
del habit_rating_1['ID']
collist = list(habit_rating_1.columns)
colist2 = [x.zfill(5) for x in collist]
habit_rating_1.columns = colist2
habit_rating_1['ID'] = habit_rating_1.index

habit_rating_1.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_1.csv', sep=',' ,index=False)

habit_rating_2 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_2.csv', sep=',' , engine='python',
                           dtype={'ID':str})

habit_rating_2.index = habit_rating_2['ID']
del habit_rating_2['ID']
collist = list(habit_rating_2.columns)
colist2 = [x.zfill(5) for x in collist]
habit_rating_2.columns = colist2
habit_rating_2['ID'] = habit_rating_2.index

habit_rating_2.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_2.csv', sep=',' ,index=False)


habit_rating_3 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_3.csv', sep=',' , engine='python',
                           dtype={'ID':str})

habit_rating_3.index = habit_rating_3['ID']
del habit_rating_3['ID']
collist = list(habit_rating_3.columns)
colist2 = [x.zfill(5) for x in collist]
habit_rating_3.columns = colist2
habit_rating_3['ID'] = habit_rating_3.index

habit_rating_3.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_3.csv', sep=',' ,index=False)


habit_rating_4 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_4.csv', sep=',' , engine='python',
                           dtype={'ID':str})

habit_rating_4.index = habit_rating_4['ID']
del habit_rating_4['ID']
collist = list(habit_rating_4.columns)
colist2 = [x.zfill(5) for x in collist]
habit_rating_4.columns = colist2
habit_rating_4['ID'] = habit_rating_4.index

habit_rating_4.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_4.csv', sep=',' ,index=False)


habit_rating_5 = pd.read_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_5.csv', sep=',' , engine='python',
                           dtype={'ID':str})

habit_rating_5.index = habit_rating_5['ID']
del habit_rating_5['ID']
collist = list(habit_rating_5.columns)
colist2 = [x.zfill(5) for x in collist]
habit_rating_5.columns = colist2
habit_rating_5['ID'] = habit_rating_5.index

habit_rating_5.to_csv('C:/Users/조만재/Desktop/lpoint/lpoint/habit_rating_5.csv', sep=',' ,index=False)

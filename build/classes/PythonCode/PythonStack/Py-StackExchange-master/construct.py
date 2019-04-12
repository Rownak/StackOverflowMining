from __future__ import print_function
##from six.moves import input

##Object Explorer

##user> dir
##['about_me', 'age', 'answers', 'association_id', 'badge_counts', 'badge_counts_t', 'badge_total', 'badges', 'bronze_badges', 'comments', 'creation_date', 'display_name', 'down_vote_count', 'email_hash', 'favorites', 'fetch', 'gold_badges', 'id', 'json_ob', 'last_access_date', 'location', 'mentioned', 'partial', 'questions', 'reputation', 'reputation_detail', 'silver_badges', 'site', 'tags', 'timeline', 'transfer', 'up_vote_count', 'url', 'user_type', 'view_count', 'vote_counts', 'website_url']

##answers_item> dir
##['accepted', 'comments', 'community_owned', 'creation_date', 'down_vote_count', 'fetch', 'id', 'json_ob', 'last_activity_date', 'owner', 'owner_id', 'owner_info', 'partial', 'question', 'question_id', 'score', 'site', 'title', 'transfer', 'up_vote_count', 'url', 'view_count', 'votes']

##import stackexchange
##site = stackexchange.Site("api.stackoverflow.com")
##user = site.user(1)

##answers = user.answers.fetch()
##answers_item = answers[1]

##reputation = user.reputation
##format = reputation.format()


import sys
import json
from operator import itemgetter
sys.path.append('.')
sys.path.append('..')

user_api_key = 'y1QM52XEvdFxjK65JCkzpQ(('
if not user_api_key: user_api_key = None

import stackexchange, thread
so = stackexchange.Site(stackexchange.StackOverflow, app_key=user_api_key, impose_throttling=True)

totalList = []
totalListDict = {}

userList =[]
for userNum in range(1,50):
    try:
        user = so.user(userNum)
        userList.append(user)
    except ValueError :
        continue


for eachUser in userList:
    tags = eachUser.tags.fetch()
    tags = tags[0:10]

    mf ={}
    
    userData = {}
    userData["id"] = str(eachUser.id)
    userData["reputation"] = str(eachUser.reputation)
    userData["name"] = str(eachUser.display_name)
    userData["membersince"] = str(eachUser.creation_date)

    alltagDict = []
    for eachTag in tags:
        eachTagDict ={}
        eachTagDict["name"] = str(eachTag.name)
        eachTagDict["numOfQues"] = str(eachTag.count)
        alltagDict.append(eachTagDict)

    mf["info"] = userData
    mf["tags"] = alltagDict
    totalList.append(mf)

totalListDict["users"] = totalList
print (json.dumps(totalListDict, ensure_ascii=False))

##    allData = [userData,taglist]
##    totalList[str(eachUser.id)]=allData
##    sortedTag = sorted(taglist.items(),key=itemgetter(1),reverse = True)    

##print (json.dumps(totalList, ensure_ascii=False))



##taglist ={}
##for eachUser in userList:
##    tags =[]
##    tags = eachUser.tags.fetch()
##    for eachTag in tags:
        
##for eachtag in tagg:
##   print(eachtag.name+ " "+str(eachtag.count) )


##print(tagg[0].count)


##qs = user.questions.fetch()
##print (qs[1])

##so.be_inclusive()
##
##sys.stdout.write('Loading...')
##sys.stdout.flush()
##
##questions = so.recent_questions(pagesize=10, filter='_b')
##cur = 1
##for question in questions:
##    print("###################################")
##    print("\n")
##    print("Url:"+question.url)
##    print("Title:"+question.title)
##    print('Tagged: ' + ', '.join(question.tags))
##    print("Owner ID:"+str(question.owner_id))
##    print("View Count:"+str(question.view_count))
    
    
    

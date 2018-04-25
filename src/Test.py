import urllib, http.cookiejar
import re,time
from bs4 import BeautifulSoup
def getForumSql(url,mainid):
    cookie = http.cookiejar.CookieJar()
    handler = urllib.request.HTTPCookieProcessor(cookie)
    opener = urllib.request.build_opener(handler)
    request = urllib.request.Request(url)
    response = opener.open(request)
    result = response.read().decode('utf-8', 'ignore')
    maintitle = (re.search(r'<meta name="keywords" content="(.*?)"',result)).group(1)
    maininfo = (re.search(r'<meta name="description" content="(.*?)"',result)).group(1)
    maintime = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))
    file = open(maintitle+'.sql', 'w')
    file.write('insert into main_forum(name,info,create_time) values("'+maintitle+'","'+maininfo+'","'+maintime+'");\n')

    soup = BeautifulSoup(result, 'html.parser')
    li=soup.select('.bbsPaList ul li a')
    for tag in li:
        urlList=re.findall(r'href="(.*?)"',str(tag))
        for urlitem in urlList:
            url1='https://bbs.hupu.com' + str(urlitem)
            try:
                request1 = urllib.request.Request(url1)
                response1 = opener.open(request1)
                result1 = response1.read().decode('utf-8', 'ignore')
                info=(re.search(r'<span id="des_forum">(.*?)</span>',result1)).group(1)
                title=(re.search(r'<span class="infoname" id="forumname".*?>(.*?)</span>',result1)).group(1)
                time1=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
                file.write('insert into sub_forum(name,info,main_forum_id,create_time) values ("'+title+'","'+info+'","'+mainid+'","'+time1+'");\n')
            except:
                continue
    file.close()

getForumSql('https://bbs.hupu.com/all-nba','1')
getForumSql('https://bbs.hupu.com/all-gambia','2')
getForumSql('https://bbs.hupu.com/all-gear','3')
print('输出完成')
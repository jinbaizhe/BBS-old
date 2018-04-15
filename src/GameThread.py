import urllib.request
import re
import time
from datetime import datetime
import http.cookiejar
import threading
import mysql.connector


def openURL(url):
    cookie = http.cookiejar.CookieJar()
    handler = urllib.request.HTTPCookieProcessor(cookie)
    opener = urllib.request.build_opener(handler)
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36',
    }
    request = urllib.request.Request(url, headers=headers)
    response = opener.open(request)
    result = response.read().decode()
    return result


class CurrentAllGame:

    def __init__(self):
        self.teamList = ["Celtics", "Lakers", "Bulls", "Warriors", "Spurs", "76ers",
                    "Pistons", "Heat", "Knicks", "Rockets", "Hawks", "Wizards",
                    "Thunder", "Cavaliers", "Trail Blazers", "Bucks", "Mavericks",
                    "Kings", "Suns", "Jazz", "Nets ", "Magic", "Hornets", "Nuggets",
                    "Clippers", "Grizzlies", "Timberwolves", "Pelicans", "Raptors",
                    "Pacers"]

    def getCurrentAllGame(self):
        result = openURL('https://www.reddit.com/r/nbastreams/',)
        result_list = []
        for item in re.findall(r'<p\s*class="title">\s*<a[^>]*?href="([^"]*)"[^>]*>([^<]*)</a>', result, re.DOTALL):
            temp_title = item[1]
            find_team = []
            home_team = None
            away_team = None
            for team in self.teamList:
                if team.lower() in temp_title.lower():
                    find_team.append((team, temp_title.lower().find(team.lower())))
            if len(find_team) == 2:
                if find_team[0][1] > find_team[1][1]:
                    home_team=find_team[0][0]
                    away_team=find_team[1][0]
                else:
                    home_team = find_team[1][0]
                    away_team = find_team[0][0]
                result_list.append((home_team ,away_team, 'https://www.reddit.com' + item[0]))
        return result_list

    def getGameInfo(self, gameUrl):
        result = openURL(gameUrl)
        streamList = []
        for item in re.findall(r'<form action="#" class="usertext warn-on-unload"[^>]+>.*?<p>(.*?)</p>.*?</form>', result, re.DOTALL):
            if re.search(r'(?:HD|hd|SD|sd)', item) and item.lower().find("<a") != -1:
                streamList.append(item)
        return streamList


def updateDB():
    currentAllGame = CurrentAllGame()
    while True:
        try:
            conn = mysql.connector.connect(user='parker', password='parker123456', database='bbs')
            cursor = conn.cursor()
            game_list = currentAllGame.getCurrentAllGame()
            #game_list=[("hometeam", "awayteam", "https://www.reddit.com/r/nbastreams/comments/8cb76k/game_thread_miami_heat_philadelphia_76ers_200000/")]
            for home_team, away_team, url in game_list:
                cursor.execute('select * from game where home=%s and away=%s and date=%s',
                               (home_team, away_team, datetime.date(datetime.now())))
                item = cursor.fetchone()
                if item:
                    gameid = item[0]
                else:
                    cursor.execute("insert into game(home,away,date) values(%s,%s,%s)",
                                   (home_team, away_team, datetime.date(datetime.now())))
                    gameid = cursor.lastrowid
                cursor.execute(
                    'update game_link set order_num=%s where game_id=%s',
                    (-1, gameid))
                for index, stream in enumerate(currentAllGame.getGameInfo(url), 1):
                    try:
                        cursor.execute('select * from game_link where info=%s and game_id=%s', (stream, gameid))
                        temp = cursor.fetchone()
                        if temp:
                            game_info_id = temp[0]
                            cursor.execute('update game_link set order_num=%s where id=%s',
                                           (index, game_info_id))
                        else:
                            cursor.execute('insert into game_link(info,game_id,order_num) values(%s,%s,%s)',
                                           (stream, gameid, index))
                    except Exception as e:
                        print(str(e))
        except Exception as e:
            print(str(e))
            time.sleep(60)
            continue
        else:
            print('success')
        cursor.close()
        conn.commit()
        conn.close()
        time.sleep(60*5)


t = threading.Thread(target=updateDB, daemon=True)
t.start()
t.join()

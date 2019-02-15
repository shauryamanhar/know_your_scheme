import requests
import pg8000
import json
conn = pg8000.connect(user="postgres", password="MA@786ma")
cursor = conn.cursor()

def stockCode(r):
    """url = "https://api.iextrading.com/1.0/stock/" + code + "/news"
    cafile = "E:\My_Projects\workspace1\Ensuraa\Lib\certifi\cacert.pem"
    r = requests.get(url, verify = cafile)
    print(r.text)"""
    """try:
        conn = psycopg2.connect("dbname='EnggCell' user='postgres' host='localhost' password='MA@786ma'")
    except:
        print ("I am unable to connect to the database")"""
    print(str(r))
    #cursor.execute("INSERT INTO newsdata VALUES("", 'blablabla')")
    #insert into newsdata(id, datetime, headline, url, related, source, summary) values((select COALESCE(max(id), 0) from newsdata) , 'a', 'a', 'a', 'a', 'a', 'a')
    parsed_json = json.loads(str(r))
    for x in parsed_json:
        print ("Headline - " + x['headline'])
        print ("Source - " + x['source'])
        print ("Datetime - " + x['datetime'])
        print ("Url - " + x['url'])
        print ("Summary - " + x['summary'])
        print ("Related - " + x['related'])
        sqlQu = "insert into newsdata(id, datetime, headline, url, related, source, summary) values((select COALESCE(max(id), 0) from newsdata), '" +  x['datetime'] + "','" + x['headline'] + "','" + x['url'] + "','" + x['related'] + "','" + x['source'] + "','" + x['summary'] + "')"
        #cursor.execute(sqlQu)
        print(sqlQu)
        print("")

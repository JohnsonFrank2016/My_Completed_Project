#Edit:JohnsonFrank2016
def icook_wc():
    import urllib.request as req
    url=input("網址為:")
    if url=="END" or url=="end":
        sys.exit(0)
    else:
        request=req.Request(url, headers={
            "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36"
        })
        with req.urlopen(request) as response:
            data=response.read().decode("utf-8")
        #print(data)
        import bs4
        root=bs4.BeautifulSoup(data, "html.parser")
        titles=root.find("div",class_="recipe-details-header-title")
        print(titles.h1.string)
        try:
            titles=root.find("a",class_="vip-entry-calories")
            print("時間、熱量、份量為",titles.title.text,"\n")
        except:
            titles=root.find("div",class_="info-content")
            print("時間",titles.span.string,"分鐘")
            titles=root.find("div",class_="servings")
            print("份量",titles.span.string,"人份")
            print("")
        titles=root.find_all("div",class_="ingredient-name")
        print("食材:")
        for title in titles:
            print("\t",title.a.string)
        print("")
        titles=root.find_all("div",class_="ingredient-unit")
        print("上述食材數量:")
        for title in titles:
            print("\t",title.string)
        todo=root.find_all("p",class_="recipe-step-description-content")
        step = 1
        print("\n作法:")
        for tod in todo:
            print("Setp",step,":")
            print("\t",tod.text)
            step+=1
        print("=======================================================")
        
import sys
print("=======================================================")
print("https://icook.tw愛料理爬蟲程式，輸入END結束程式")
print("=======================================================")
while True:
    try:
        icook_wc()
    except SystemExit:
        break
    except:
        print("\n\n\t\t【網址有誤!請檢查並重新輸入!】\n\n")
        print("=======================================================")
        print("https://icook.tw愛料理爬蟲程式，輸入END結束程式")
        print("=======================================================")

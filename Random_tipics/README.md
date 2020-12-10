# 隨機抽題程式
  	1. 為CMD介面
  	2. 這個是我當時在考學校TQC的證照時，自己寫的一個隨機抽題檔案
  	3. 檔案說明
    	Step 1:
      		Category.txt 是類別選擇，可以輸入你要選擇的題目類別(注意:不可多換行)
		Step 2:
			Category資料夾中裡的TXT檔，是你選擇的題目細項，也就是所有題目，也是抽題所用(注意:不可多換行)
	4. 範例
			● 假如我有「Java」和「Python」兩種考試，各有不同題目。
				Java有10題、Python有15題
			● Category.txt內容就是
				Java
				Python
				(到最後一項就不要再換行)
			● 然後，在Category資料夾中，就可以新增兩個TXT檔(注意:只能TXT檔)
				Java.txt
				Python.txt
			● 在兩個.txt檔中寫的是你的題目或題目編號，例如:
				Java.txt
					第1題
					第2題
					第3題
					第4題
					...
					第10題
					(到最後一項就不要再換行)
				Python.txt以此類推
	5. 最後注意:
		1. 題目只有一行的空間
		2. Category.txt與Category資料夾檔名，不可更改。
			(如果要改請至程式碼一請更改)


Edit: 吳振凱(JohnsonFrand2016)
下午 01:10 2020/12/10

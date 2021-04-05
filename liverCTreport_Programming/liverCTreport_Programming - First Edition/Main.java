import java.io.*;
import java.util.Scanner;
public class Main{
	public static void main(String[] args)throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("outputfileName : ");
		String outputfileName = sc.next();
		System.out.print("File Start : ");
		int filestart = sc.nextInt();
		System.out.print("File End : ");
		int fileend = sc.nextInt();
		outputfileName = outputfileName+".csv";
		System.out.println("Generating "+outputfileName+" file, please wait...");
		FileWriter fw = new FileWriter(outputfileName);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("FileName"+","+"Rows"+","+"Original"+","+"Location"+","+"Position"+","+"Definiteterms"+","+"Progress"+","+"Enhancement"+ "\r\n");
		for(int i=filestart;i<=fileend;i++){
			String openfileName="data/liverCTreport/"+String.valueOf(i)+".txt";
			String sentence="";
			int rows=0;
			String original="";
			boolean start=false;
			String ln="",pn="",ds="",ps="",et="";
			FileReader read=new FileReader(openfileName);
			BufferedReader bu=new BufferedReader(read);
			while((sentence=bu.readLine())!=null){
				if (sentence.indexOf("IMP")!=-1){start=false;}
				if (sentence.indexOf("imp")!=-1){start=false;}
				if (start&&(sentence.equals(" ")==false)){
					rows++;
					original = "\""+sentence+"\"";
					sentence=stemming(sentence);
					sentence=synonymoption(sentence);
					sentence=Punctuation(sentence);
					ln = location(Onegram(sentence),ln);
					ln = location(Twogram(sentence),ln);
					ln = location(Threegram(sentence),ln);
					ln = "\""+Remove_duplicates(ln)+"\"";
					pn = position(Onegram(sentence),pn);
					pn = position(Twogram(sentence),pn);
					pn = position(Threegram(sentence),pn);
					pn = "\""+Remove_duplicates(pn)+"\"";
					ds = definiteterms(Onegram(sentence),ds);
					ds = definiteterms(Twogram(sentence),ds);
					ds = definiteterms(Threegram(sentence),ds);
					ds = "\""+Remove_duplicates(ds)+"\"";
					ps = progress(Onegram(sentence),ps);
					ps = progress(Twogram(sentence),ps);
					ps = progress(Threegram(sentence),ps);
					ps = "\""+Remove_duplicates(ps)+"\"";
					et = ehancement(Onegram(sentence),et);
					et = ehancement(Twogram(sentence),et);
					et = ehancement(Threegram(sentence),et);
					et = "\""+Remove_duplicates(et)+"\"";
					bw.write(i+","+rows+","+original+","+ln+","+pn+","+ds+","+ps+","+et+"\r\n");
					ln="";pn="";ds="";ps="";et="";
				}
				if (sentence.indexOf("Findings")!=-1){start=true;}
				if (sentence.indexOf("findings")!=-1){start=true;}
			}
			read.close();
		}
		bw.close();
		System.out.println("The "+outputfileName+" file was created successfully.");
	}
	public static String Onegram(String sentence)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		for (String i:array) {
			if (stopword.indexOf(i)==-1){back+=i+" ";}
		}
		return back;
	}
	public static String Twogram(String sentence)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==2) {i-=2;count=0;
				}else{
					back+=array[i]+" ";
					count++;
				}
			}else{count=0;}
		}
		return back;
	}
	public static String Threegram(String sentence)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==3) {i-=3;count=0;
				}else{
					back+=array[i]+" ";
					count++;
				}
			}else{count=0;}
		}
		return back;
	}
	public static String StopWord()throws IOException{
		String openfileName="data/Stop_Words.txt";
		FileReader read = new FileReader(openfileName);
		BufferedReader bu = new BufferedReader(read);
		String stopword = bu.readLine();
		read.close();
		return stopword;
	}
	public static String Punctuation(String sentence)throws IOException{
		String[] array = sentence.split(" ");
		sentence="";
		for (int i=0;i<array.length;i++) {
			if (array[i].indexOf(",")!=-1) {
				char[] cword = array[i].toCharArray();
				String word="";
				for (int j=0;j<cword.length;j++) {
					if(cword[j]!=','){word+=String.valueOf(cword[j]);}
				}
				array[i]=word;
			}
			if (array[i].indexOf("?")!=-1) {
				char[] cword = array[i].toCharArray();
				String word="";
				for (int j=0;j<(cword.length-1);j++) {
					word+=String.valueOf(cword[j]);
				}
				array[i]=word;
			}
			if (array[i].indexOf(".")!=-1) {
				char[] cword = array[i].toCharArray();
				String word="";
				for (int j=0;j<cword.length;j++) {
					if(cword[j]=='.'){
						if (j-1>=0) {
							int asc = cword[j-1];
							if (asc>47) {if (asc<58) {word+=String.valueOf(cword[j]);}}
						}
					}else{
						word+=String.valueOf(cword[j]);
					}
				}
				array[i]=word;
			}
			if (array[i].indexOf("-")!=-1) {
				char[] cword = array[i].toCharArray();
				String word="";
				for (int j=0;j<cword.length;j++) {
					if(cword[j]=='-'){
						if(j-1>=0){
							int asc = cword[j-1];
							if (asc<48||asc>57){cword[j]=' ';}
						}
					}
					word+=String.valueOf(cword[j]);
				}
				array[i]=word;
			}
			if (i==(array.length-1)){
				sentence+=array[i];
			}else{
				sentence+=array[i]+" ";
			}
		}
		return sentence;
	}
	public static String Remove_duplicates(String item)throws IOException{
		String[] array = item.split(",");
		item = "";
		for (int i=0;i<array.length;i++) {
			for (int j=i+1;j<array.length;j++) {
				if (array[j].equals(array[i])) {
					array[j]=",";
				}
			}
		}
		for (String i:array) {
			if (i.equals(",")==false) {
				item += i+",";
			}
		}
		if (item.equals(",")) {
			item = "";//NULL
		}else{
			char[] carray = item.toCharArray();
			item = "";
			for (int i=0;i<(carray.length-1);i++) {
				item+=String.valueOf(carray[i]);
			}
		}
		return item;
	}
	public static String definiteterms(String sentence,String ds)throws IOException{
		sentence = sentence.toLowerCase();
		String file;
		String original;
		FileReader read=new FileReader("data/definite_terms.csv");
		BufferedReader bu=new BufferedReader(read);
		while((file=bu.readLine())!=null){
			original = file;
			if (file.indexOf("-")!=-1) {
				char[] cword = file.toCharArray();
				String word="";
				for (int j=0;j<cword.length;j++) {
					if(cword[j]=='-'){cword[j]=' ';}
					word+=String.valueOf(cword[j]);
				}
				file=word;
			}
			file = file.toLowerCase();
			if (sentence.indexOf(file)!=-1) {
				ds += original + ",";
			}
		}
		read.close();
		return ds;
	}
	public static String location(String sentence,String ln)throws IOException{
		sentence = sentence.toLowerCase();
		String file;
		String original;
		FileReader read=new FileReader("data/location.csv");
		BufferedReader bu=new BufferedReader(read);
		while((file=bu.readLine())!=null){
			original = file;
			if (file.indexOf("-")!=-1) {
				char[] cword = file.toCharArray();
				String word="";
				for (int j=0;j<cword.length;j++) {
					if(cword[j]=='-'){cword[j]=' ';}
					word+=String.valueOf(cword[j]);
				}
				file=word;
			}
			file = file.toLowerCase();
			if (sentence.indexOf(file)!=-1) {
				ln += original + ",";
			}
		}
		read.close();
		return ln;
	}
	public static String position(String sentence,String pn)throws IOException{
		sentence = sentence.toLowerCase();
		String file;
		String original;
		FileReader read=new FileReader("data/position.csv");
		BufferedReader bu=new BufferedReader(read);
		while((file=bu.readLine())!=null){
			original = file;
			if (file.indexOf("-")!=-1) {
				char[] cword = file.toCharArray();
				String word="";
				for (int j=0;j<cword.length;j++) {
					if(cword[j]=='-'){
						int asc = cword[j-1];
						if (asc<48||asc>57){cword[j]=' ';}
					}
					word+=String.valueOf(cword[j]);
				}
				file=word;
			}
			file = file.toLowerCase();
			if (sentence.indexOf(file)!=-1) {
				pn += original + ",";
			}
		}
		read.close();
		return pn;
	}
	public static String progress(String sentence,String ps)throws IOException{
		sentence = sentence.toLowerCase();
		String file;
		String original;
		FileReader read=new FileReader("data/progress.csv");
		BufferedReader bu=new BufferedReader(read);
		while((file=bu.readLine())!=null){
			original = file;
			if (file.indexOf("-")!=-1) {
				char[] cword = file.toCharArray();
				String word="";
				for (int j=0;j<cword.length;j++) {
					if(cword[j]=='-'){cword[j]=' ';}
					word+=String.valueOf(cword[j]);
				}
				file=word;
			}
			file = file.toLowerCase();
			if (sentence.indexOf(file)!=-1) {
				ps += original + ",";
			}
		}
		read.close();
		return ps;
	}
	public static String ehancement(String sentence,String et)throws IOException{
		sentence = sentence.toLowerCase();
		String file;
		String original;
		FileReader read=new FileReader("data/enhancement.csv");
		BufferedReader bu=new BufferedReader(read);
		while((file=bu.readLine())!=null){
			original = file;
			if (file.indexOf("-")!=-1) {
				char[] cword = file.toCharArray();
				String word="";
				for (int j=0;j<cword.length;j++) {
					if(cword[j]=='-'){cword[j]=' ';}
					word+=String.valueOf(cword[j]);
				}
				file=word;
			}
			file = file.toLowerCase();
			if (sentence.indexOf(file)!=-1) {
				et += original + ",";
			}
		}
		read.close();
		return et;
	}
	public static String stemming(String sentence)throws IOException{
		String rd = "";
		String word = "";
		int count=0;
		int orgArrayCount = 0;
		int parArrayCount = 0;
		FileReader read=new FileReader("data/stemming.csv");
		BufferedReader bu=new BufferedReader(read);
		while((rd=bu.readLine())!=null){word+=rd+",";}
		String[] arrayAll = word.split(",");
		int arraySize = arrayAll.length/2;
		String[] orgArray = new String[arraySize];
		String[] parArray = new String[arraySize];
		for (String i:arrayAll) {
			count++;
			if (count%2==0) {
				if (i.indexOf("-")!=-1) {
					char[] cword = i.toCharArray();
					String iword="";
					for (int j=0;j<cword.length;j++) {
						if(cword[j]=='-'){cword[j]=' ';}
						iword+=String.valueOf(cword[j]);
					}
					i=iword;
				}
				orgArray[orgArrayCount] = i;
				orgArrayCount++;
			}else{
				if (i.indexOf("-")!=-1) {
					char[] cword = i.toCharArray();
					String iword="";
					for (int j=0;j<cword.length;j++) {
						if(cword[j]=='-'){cword[j]=' ';}
						iword+=String.valueOf(cword[j]);
					}
					i=iword;
				}
				parArray[parArrayCount] = i;
				parArrayCount++;
			}
		}
		for (int i=0;i<arraySize;i++) {
			if (sentence.indexOf(parArray[i])!=-1) {
				if (sentence.indexOf(orgArray[i])==-1) {
					sentence+=" "+orgArray[i];
				}
			}
			if (sentence.indexOf(orgArray[i])!=-1) {
				if (sentence.indexOf(parArray[i])==-1) {
					sentence+=" "+parArray[i];
				}
			}
		}
		return sentence;
	}
	public static String synonymoption(String sentence)throws IOException{
		String rd = "";
		String word = "";
		int count=0;
		int orgArrayCount = 0;
		int parArrayCount = 0;
		FileReader read=new FileReader("data/synonymoption.csv");
		BufferedReader bu=new BufferedReader(read);
		while((rd=bu.readLine())!=null){word+=rd+",";}
		String[] arrayAll = word.split(",");
		int arraySize = arrayAll.length/2;
		String[] orgArray = new String[arraySize];
		String[] parArray = new String[arraySize];
		for (String i:arrayAll) {
			count++;
			if (count%2==0) {
				if (i.indexOf("-")!=-1) {
					char[] cword = i.toCharArray();
					String iword="";
					for (int j=0;j<cword.length;j++) {
						if(cword[j]=='-'){
							int asc = cword[j-1];
							if (asc<48||asc>57){cword[j]=' ';}
						}
						iword+=String.valueOf(cword[j]);
					}
					i=iword;
				}
				parArray[parArrayCount] = i;
				parArrayCount++;
			}else{
				if (i.indexOf("-")!=-1) {
					char[] cword = i.toCharArray();
					String iword="";
					for (int j=0;j<cword.length;j++) {
						if(cword[j]=='-'){
							int asc = cword[j-1];
							if (asc<48||asc>57){cword[j]=' ';}
						}
						iword+=String.valueOf(cword[j]);
					}
					i=iword;
				}
				orgArray[orgArrayCount] = i;
				orgArrayCount++;
			}
		}
		for (int i=0;i<arraySize;i++) {
			if (sentence.indexOf(parArray[i])!=-1) {
				if (sentence.indexOf(orgArray[i])==-1) {
					sentence+=" "+orgArray[i];
				}
			}
			if (sentence.indexOf(orgArray[i])!=-1) {
				if (sentence.indexOf(parArray[i])==-1) {
					sentence+=" "+parArray[i];
				}
			}
		}
		return sentence;
	}
}
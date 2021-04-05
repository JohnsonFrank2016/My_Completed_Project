import java.io.*;
import java.util.Scanner;
public class Main{
	//PM 06:53 2020/8/19
	public static void main(String[] args)throws IOException {
//TEXT
		Scanner sc = new Scanner(System.in);
		System.out.print("outputfileName :");
		String outputfileName = sc.next();
		System.out.print("File Start : ");
		int filestart = sc.nextInt();
		System.out.print("File End : ");
		int fileend = sc.nextInt();
		sc.close();
		outputfileName = outputfileName+".csv";
		System.out.println("Generating "+outputfileName+" file, please wait...");
//Main
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
				if ((sentence.indexOf("IMP")!=-1)||(sentence.indexOf("imp")!=-1)){start=false;}
				if (start&&(sentence.equals(" ")==false)){
					rows++;
					original = "\""+sentence+"\"";
					sentence=Punctuation(sentence);
					//-------------------------------------
					ln = Onegram_ln(sentence,ln);
					ln = Twogram_ln(sentence,ln);
					ln = Threegram_ln(sentence,ln);
					ln = "\""+Remove_duplicates(ln)+"\"";
					//-------------------------------------
					pn = Onegram_pn(sentence,pn);
					pn = Twogram_pn(sentence,pn);
					pn = Threegram_pn(sentence,pn);
					pn = "\""+Remove_duplicates(pn)+"\"";
					//-------------------------------------
					ds = Onegram_ds(sentence,ds);
					ds = Twogram_ds(sentence,ds);
					ds = Threegram_ds(sentence,ds);
					ds = "\""+Remove_duplicates(ds)+"\"";
					//-------------------------------------
					ps = Onegram_ps(sentence,ps);
					ps = Twogram_ps(sentence,ps);
					ps = Threegram_ps(sentence,ps);
					ps = "\""+Remove_duplicates(ps)+"\"";
					//-------------------------------------
					et = Onegram_et(sentence,et);
					et = Twogram_et(sentence,et);
					et = Threegram_et(sentence,et);
					et = "\""+Remove_duplicates(et)+"\"";
					//-------------------------------------
					bw.write(i+","+rows+","+original+","+ln+","+pn+","+ds+","+ps+","+et+"\r\n");
					ln="";pn="";ds="";ps="";et="";
				}
				if ((sentence.indexOf("Findings")!=-1)||(sentence.indexOf("findings")!=-1)){start=true;}
			}
			read.close();
		}
		bw.close();
		System.out.println("The "+outputfileName+" file was created successfully.");
	}
//Loction_123Gram
	public static String Onegram_ln(String sentence,String ln)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		for (String i:array) {
			if (stopword.indexOf(i)==-1){
				ln = location(i,ln);
			}
		}
		return ln;
	}
	public static String Twogram_ln(String sentence,String ln)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==2) {
					ln = location(back,ln);
					back = "";
					i-=2;
					count=0;
				}else{
					if(count==1){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==2) {ln = location(back,ln);}
				back = "";
				count=0;
			}
		}
		if (count==2) {ln = location(back,ln);}
		return ln;
	}
	public static String Threegram_ln(String sentence,String ln)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==3) {
					ln = location(back,ln);
					back = "";
					i-=3;
					count=0;
				}else{
					if(count==2){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==3) {ln = location(back,ln);}
				back = "";
				count=0;
			}
		}
		if (count==3) {ln = location(back,ln);}
		return ln;
	}
//Position_123Gram
	public static String Onegram_pn(String sentence,String pn)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		for (String i:array) {
			if (stopword.indexOf(i)==-1){
				pn = position(i,pn);
			}
		}
		return pn;
	}
	public static String Twogram_pn(String sentence,String pn)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==2) {
					pn = position(back,pn);
					back = "";
					i-=2;
					count=0;
				}else{
					if(count==1){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==2) {pn = position(back,pn);}
				back = "";
				count=0;
			}
		}
		if (count==2) {pn = position(back,pn);}
		return pn;
	}
	public static String Threegram_pn(String sentence,String pn)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==3) {
					pn = position(back,pn);
					back = "";
					i-=3;
					count=0;
				}else{
					if(count==2){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==3) {pn = position(back,pn);}
				back = "";
				count=0;
			}
		}
		if (count==3) {pn = position(back,pn);}
		return pn;
	}
//Definiteterms_123Gram
	public static String Onegram_ds(String sentence,String ds)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		for (String i:array) {
			if (stopword.indexOf(i)==-1){
				ds = definiteterms(i,ds);
			}
		}
		return ds;
	}
	public static String Twogram_ds(String sentence,String ds)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==2) {
					ds = definiteterms(back,ds);
					back = "";
					i-=2;
					count=0;
				}else{
					if(count==1){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==2) {ds = definiteterms(back,ds);}
				back = "";
				count=0;
			}
		}
		if (count==2) {ds = definiteterms(back,ds);}
		return ds;
	}
	public static String Threegram_ds(String sentence,String ds)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==3) {
					ds = definiteterms(back,ds);
					back = "";
					i-=3;
					count=0;
				}else{
					if(count==2){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==3) {ds = definiteterms(back,ds);}
				back = "";
				count=0;
			}
		}
		if (count==3) {ds = definiteterms(back,ds);}
		return ds;
	}
//Progress_123Gram
	public static String Onegram_ps(String sentence,String ps)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		for (String i:array) {
			if (stopword.indexOf(i)==-1){
				ps = progress(i,ps);
			}
		}
		return ps;
	}
	public static String Twogram_ps(String sentence,String ps)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==2) {
					ps = progress(back,ps);
					back = "";
					i-=2;
					count=0;
				}else{
					if(count==1){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==2) {ps = progress(back,ps);}
				back = "";
				count=0;
			}
		}
		if (count==2) {ps = progress(back,ps);}
		return ps;
	}
	public static String Threegram_ps(String sentence,String ps)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==3) {
					ps = progress(back,ps);
					back = "";
					i-=3;
					count=0;
				}else{
					if(count==2){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==3) {ps = progress(back,ps);}
				back = "";
				count=0;
			}
		}
		if (count==3) {ps = progress(back,ps);}
		return ps;
	}
//Ehancement_123Gram
	public static String Onegram_et(String sentence,String et)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		for (String i:array) {
			if (stopword.indexOf(i)==-1){
				et = ehancement(i,et);
			}
		}
		return et;
	}
	public static String Twogram_et(String sentence,String et)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==2) {
					et = ehancement(back,et);
					back = "";
					i-=2;
					count=0;
				}else{
					if(count==1){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==2) {et = ehancement(back,et);}
				back = "";
				count=0;
			}
		}
		if (count==2) {et = ehancement(back,et);}
		return et;
	}
	public static String Threegram_et(String sentence,String et)throws IOException{
		String[] array = sentence.split(" ");
		String stopword = StopWord();
		String back = "";
		int count = 0;
		for (int i=0;i<array.length;i++) {
			if (stopword.indexOf(array[i])==-1) {
				if (count==3) {
					et = ehancement(back,et);
					back = "";
					i-=3;
					count=0;
				}else{
					if(count==2){
						back+=array[i];
					}else{
						back+=array[i]+" ";	
					}
					count++;
				}
			}else{
				if (count==3) {et = ehancement(back,et);}
				back = "";
				count=0;
			}
		}
		if (count==3) {et = ehancement(back,et);}
		return et;
	}
//StopWord,Punctuation,Remove_duplicates
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
//Loction,Position,Definiteterms,Progress,Ehancement.
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
			if (sentence.equals(file)) {
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
			if (sentence.equals(file)) {
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
			if (sentence.equals(file)) {
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
			if (sentence.equals(file)) {
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
			if (sentence.equals(file)) {
				et += original + ",";
			}
		}
		read.close();
		return et;
	}
}
import java.io.*;
import java.lang.System;
public class Deduplication{
	public static void main(String[] args) throws IOException {
		for (int i=1;i<=5;i++) {
			allfile(i);
		}
		end();
		// 1. definiteterms
		// 2. location
		// 3. position
		// 4. progress
		// 5. ehancement
	}
	public static void allfile(int fileNameNum) throws IOException {
		String fileName="";
		String outfileName;
		String content;
		int saveWord = 0;
		switch (fileNameNum) {
			case 1:
				fileName = "definite_terms";
			break;
			case 2:
				fileName = "location";
			break;
			case 3:
				fileName = "position";
			break;
			case 4:
				fileName = "progress";
			break;
			case 5:
				fileName = "enhancement";
			break;
			default:
				System.out.println("ERROR!ERROR!ERROR!");
				end();
		}
		outfileName = "data/"+fileName+".csv";
		fileName="data/repeat/"+fileName+".csv";
		System.out.println("=============================================");
		int size = arraySize(fileName);
		String[] word = new String[size];
		FileReader read = new FileReader(fileName);
		BufferedReader bu = new BufferedReader(read);
		while((content=bu.readLine())!=null){
			boolean find = false;
			for (int i=0;i<size;i++) {
				if (content.equals(word[i])) {
					find=true;
					break;
				}
			}
			if (find==false) {
				word[saveWord]=content;
				saveWord++;
			}
		}
		FileWriter fw = new FileWriter(outfileName);
		for (int i=0;i<saveWord;i++) {
			fw.write(word[i]+"\r\n");
			System.out.println(word[i]);
		}
		fw.close();
		read.close();
		System.out.println("------------------------------------------------");
	}
	public static int arraySize(String fileName) throws IOException {
		int count=0;
		String i;
		FileReader read = new FileReader(fileName);
		BufferedReader bu = new BufferedReader(read);
		while((i=bu.readLine())!=null){
			count++;
		}
		read.close();
		return count;
	}
	public static void end() throws IOException{
		System.out.print("Press enter to exit....");
		System.in.read();
		System.exit(0);
	}
}
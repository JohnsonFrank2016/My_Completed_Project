package Random_tipics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Random_topics {
    public static void main(String[] args) {
        boolean endornot_return = true;
        while (endornot_return) {
            System.out.println("======================================");
            System.out.println("歡迎來到TQC抽題程式，請選擇你要的類別:");
            System.out.println("======================================");
            // Category設定=============================================
            String category_return = "";
            try {
                category_return = Category("Category.txt");
            } catch (IOException e) {
                FE();
                System.out.println("ERROR:1 Category_Error!");
                System.out.println("錯誤:1 請檢查您的Category.txt檔案是否存在");
                try {
                    System.in.read();
                    System.exit(0);
                } catch (Exception stop) {
                }
            }
            // =========================================================
            System.out.println("您選擇的類別為: " + category_return);
            System.out.println("======================================");
            // Topic設定================================================
            String Topic_return = "";
            try {
                Topic_return = Topic(category_return);
            } catch (IOException e) {
                FE();
                System.out.println("ERROR:2 Category_File_Error!");
                System.out.println("錯誤:2 請檢查您的Category資料夾是否存在");
                System.out.println("或請檢查您的Category資料夾內部檔案是否完整");
                try {
                    System.in.read();
                    System.exit(0);
                } catch (Exception stop) {
                }
            }
            // 陣列設定==================================================
            String[] array = Topic_return.split(",");//題目
            int size = array.length;
            boolean[] Repeat = new boolean[size];//檢查重複
            Arrays.fill(Repeat,false);//檢查重複所有預設值設為[False]
            // =========================================================
            boolean need = true;
            while (need) {
                int number = Random_number(size,Repeat);// 輸入所需隨機數
            // 選題=====================================================
                int count = 0;
                int pos = 0;
                while(count<number){
                    int randomNum=(int)(Math.random()*123456789)%size; //0~100
                    if(!Repeat[randomNum]){
                        Repeat[randomNum] = true;
                        System.out.println(++pos+". "+array[randomNum]);
                        count++;
                    }
                }
                need = Remaining(Repeat);
            }
            System.out.println("======================================");
            System.out.println("題目已全部使用完畢");
            System.out.println("======================================");
            endornot_return = EndOrNot();
        }
        try {System.in.close();} catch (IOException e) {}
    }

    static boolean EndOrNot() {
        while (true) {
            System.out.println("1. 回到主畫面\n2. 退出");
            System.out.print("請問要退出還是要回到主畫面?(輸入編號):");
            String input = new Scanner(System.in).nextLine();
            if(input.equals("1")){
                return true;
            }else if(input.equals("2")){
                break;
            }else{
                System.out.println("輸入不符合!");
                System.out.println("======================================");
            }
        }
        return false;
    }

    public static String Category(String file) throws IOException {
        String str;
        FileReader read = new FileReader(file);
        BufferedReader br = new BufferedReader(read);
        String temp = "";
        while ((str = br.readLine()) != null)
            temp += str + "@#@";
        br.close();
        read.close();
        String[] array = temp.split("@#@");
        int number = 0;
        for (String pl : array)
            System.out.println(++number + ". " + pl);
        while (true) {
            System.out.print("請輸入類別代號:");
            try {
                number = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                number = 0;
            }
            if (number > 0 && number <= array.length)
                break;
            System.out.println("\t您選擇的類別不存在!");
        }
        return array[number - 1];
    }

    public static String Topic(String file) throws IOException {
        String str,temp="";
        int count = 0;
        FileReader read = new FileReader("Category\\"+file+".txt");
        BufferedReader bu = new BufferedReader(read);
        while((str=bu.readLine())!=null){
            System.out.println(++count+". "+str);
            temp+=str+",";
        }
        bu.close();
        read.close();
        return temp;
    }

    public static int Random_number(int size,boolean[] array){
        int number=0;size=0;
        for (boolean b : array) {
            if(b==false)size++;
        }
        while (true) {
            System.out.print("剩餘"+size+"題，您想要一次隨機出現幾題?:");
            try {
                number = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                number = 0;
            }
            if (number > 0 && number <= size)
                break;
            System.out.println("\t您選擇的範圍不合理!");
        }
        return number;
    }

    public static boolean Remaining(boolean array[]){
        for (boolean b : array) {
            if(b==false)return true;
        }
        return false;
    }

    public static void FE() {
        for (int i = 1; i <= 100; i++)
            System.out.println();
    }
}
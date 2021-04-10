import java.util.Scanner;
public class JPA502 {
    public static Scanner keyboard = new Scanner(System.in);
    
    public static void main(String args[]) {
        System.out.print("請輸入學生人數：");
        float score = 0;
        int stud = keyboard.nextInt();
        float[] array = new float[stud];
        for(int i=0;i<stud;i++){
            System.out.print("第"+(i+1)+"個學生的成績：");
            array[i] = keyboard.nextFloat();
            score+=array[i];
        }
        System.out.println("人數："+stud);
        System.out.println("總分："+score);
        System.out.println("平均："+(score/stud));
    }
}
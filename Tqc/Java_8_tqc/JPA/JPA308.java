import java.util.Scanner;
public class JPA308 {
    static Scanner keyboard = new Scanner(System.in);
    static int i = -1;
    public static void main(String[] args) {
        int total = 0, s = 0;
        
        do {
            System.out.print("請輸入消費金額，或輸入-1 結束：");
            s = keyboard.nextInt();
            total += s;
        } while (s != i);
        System.out.print("電腦週邊總消費："+(total+1));
        

    }
}
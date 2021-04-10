import java.util.Scanner;
public class JPA402 {
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String args[]) {
        while (true) {
            System.out.print("Input n (0 <= n <= 16):");
            int num = keyboard.nextInt();
            if (num == 999)
                break;
            else if (num < 0 || num > 16)
                continue;
            System.out.println(num + " 的階乘(尾端遞迴) = " + RE(num));
            System.out.println(num + " 的階乘(迴圈) = " + REF(num));
        }
    }
    public static int RE(int a) {
        int sum = a;
        if (a == 1)
            return 1;
        else if (a == 0)
            return 0;
        return sum *= RE(a - 1);
    }
    public static int REF(int a) {
        if (a == 0)
            return 0;
        int sum = 1;
        for (int i = a; i >= 1; i--)
            sum *= i;
        return sum;
    }
}
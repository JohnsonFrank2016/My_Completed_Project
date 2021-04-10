import java.util.*;
public class JPA306 {
    public static void main (String argv[]) {
        int num1, num2;

        Scanner input = new Scanner(System.in);
        System.out.println("Input:"); 
        num1 = input.nextInt();
        while (num1 != 999) {
            num2 = input.nextInt();
            System.out.println(powerOf(num1, num2));
            System.out.println("Input:"); 
            num1 = input.nextInt();
        }
    }  

    static int powerOf (int m, int n) {
        int sum = m;
        while (n!=1) {
            sum *= m;
            n--;
        }
        return sum;
    }
}

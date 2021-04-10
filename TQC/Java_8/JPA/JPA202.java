import java.util.*;
class JPA202 {
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        test();
        test();
    }
    
    public static void test() {
        System.out.println("Input:");
        int a = keyboard.nextInt();
        int b = keyboard.nextInt();
        if(a>b)
            System.out.println(a+" is larger than "+b);
        else
            System.out.println(b+" is larger than "+a);
    }
}

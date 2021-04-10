import java.util.*;
class JPA208 {
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        test();
        test();
        test();
        test();
        test();
    }
    public static void test() {
        System.out.println("Input:");
        int score = keyboard.nextInt();
        if (score >= 90)
            System.out.println("Your grade is A");
        else if (score < 90 && score >= 80)
            System.out.println("Your grade is B");
        else if (score < 80 && score >= 70)
            System.out.println("Your grade is C");
        else if (score < 70 && score >= 60)
            System.out.println("Your grade is D");
        else
            System.out.println("Your grade is F");
    }
}

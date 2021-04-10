import java.util.Scanner;
public class JPA102 {
    public static void main(String[] args) {
        System.out.print("Please input:");
        double kg = new Scanner(System.in).nextInt();
        System.out.printf("%6f kg = %6f ponds",kg,kg*2.20462);
    }
}

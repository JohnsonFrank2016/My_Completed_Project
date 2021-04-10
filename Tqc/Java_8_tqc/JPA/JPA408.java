import java.util.Scanner;
public class JPA408 {
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String args[]) {
        String s, c; 
        System.out.print("Input a string: ");
        s = keyboard.nextLine();
        System.out.printf("%s\n", reverse(s));
        System.out.print("Input a string: ");
        s = keyboard.nextLine();
        System.out.printf("%s\n", reverse(s));
    }
    
    public static String reverse(String s) {
        if (s.equals("")) return "";
        return reverse(s.substring(1))+s.substring(0,1);
    }
}
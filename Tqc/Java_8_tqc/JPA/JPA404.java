import java.util.Scanner;
public class JPA404 {
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String args[]) {
      while (true) {
        System.out.print("Input m:");
        int m = keyboard.nextInt();
        if(m==999)break;
        System.out.print("Input n:");
        int n = keyboard.nextInt();
        System.out.println("最大公因數為:"+GCF(m,n));  
      }
    }
    public static int GCF(int a,int b) {
      if (b == 0) return a;
      return GCF(b,a%b);
    }
}
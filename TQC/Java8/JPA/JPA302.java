import java.util.Scanner;
public class JPA302 {
    public static void main(String[] args) {
        int i = 1, j = 1, count = 0;
        for (i = 1; i <= 3; i++)
            for (j = 1; j <= 9; j++)
                count++;
        System.out.printf("count = %d\n", count);
    }
}

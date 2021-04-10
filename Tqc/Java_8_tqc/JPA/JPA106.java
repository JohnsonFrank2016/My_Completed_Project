public class JPA106 {
    public static void main(String[] args) {
        System.out.printf("f(-3.2) = %.4f\n",fx(-3.2));
        System.out.printf("f(-2.1) = %.4f\n",fx(-2.1));
        System.out.printf("f(0) = %.4f\n",fx(0));
        System.out.printf("f(2.1) = %.4f\n",fx(2.1));
    }
    public static double fx(double num) {
        return ((3*(Math.pow(num,3)))+(2*num))-1;
    }
}

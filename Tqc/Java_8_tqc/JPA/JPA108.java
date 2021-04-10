class JPA108 {
	public static int add(int a, int b) {
		int sum = a+b;
		System.out.println("Adding two integers:"+a+","+b);
		return sum;
	}
	public static double add(double a, double b) {
		double sum = a+b;
		System.out.println("Adding two doubles:"+a+","+b);
		return sum;
	}
	public static String add(String a, String b) {
		String sum = a+b;
		System.out.println("Adding two strings:"+a+","+b);
		return sum;
	}
	public static void main(String[] args) {
		int i = add(2, 3);
		double d = add(5.2, 4.3);
		String s = add("I love ", "Java!!");
		System.out.printf("%d %f %s %n", i, d, s);
	}
}

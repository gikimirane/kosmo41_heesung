

public class methodReturns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a;
		a = b(4, 5);
		System.out.println("4 + 5 = " + a);
		System.out.println("3.5 x 3.5 = " + c(3.5));

	}

	public static int b(int d, int e) {
		int f = d + e;
		return f;
	}

	public static double c(double g) {
		return g * g;
	}

}

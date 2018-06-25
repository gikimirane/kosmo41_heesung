
public class ReculFactorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("3 factorial " + a(3));
		System.out.println("12 factorial " + a(12));

	}

	public static int a(int n) {
		if (n == 1)
			return 1;
		else
			return n * a(n - 1);
	}

}

class Addd{
	public int add(int a, int b) {
		return a+b;
	}
}

class Chad extends Addd{
	//@Override;
	public double add2(double a, double b) {
		return a+b;
	}
}
public class Override2 {

	public static void main(String[] args) {
		Addd c = new Chad();
		System.out.println(c.add(3, 4));

	}

}

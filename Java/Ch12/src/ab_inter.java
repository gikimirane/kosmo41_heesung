abstract class Coz {
	int a;
	int b;

	abstract int result();

	void pre() {
		System.out.println(result());
	}

	void setre(int m, int n) {
		a = m;
		b = n;

	}
}

class Plus3 extends Coz {
	int result() {
		return a + b;
	}
}

class Minus extends Coz {
	int result() {
		return a - b;
	}
}

public class ab_inter {

	public static void main(String[] args) {
		int x = 54, y = 12;
		Coz c = new Plus3();
		Coz d = new Minus();

		c.setre(x, y);
		d.setre(x, y);

		System.out.println(x + "+" + y + "=");
		c.pre();
		System.out.println(x + "-" + y + "=");
		d.pre();

	}

}

class A {
	int b(int c, int d) {
		return c + d;

	}
}

class Calculation {

	static void as() {
		// TODO Auto-generated method stub
		int nr;
		A ad = new A();
		nr = ad.b(3, 9);
		
		System.out.println("3+9= " + nr);
	}

	public static void main(String[] args) {
		as();
	}

}

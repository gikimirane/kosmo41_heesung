class Calc{
	int add(int a, int b) {
		return a+b;
	}
	int add(int a) {
		return a+1;
	}
	double add(double a, double b) {
		return a+b;
	}
}
class Calculation_ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calc calc = new Calc();
		int mr = calc.add(3,9);
		int mr2 = calc.add(3);
		double mr3 = calc.add(3.0, 9.0);
		
		System.out.println("mr= " + mr);
		System.out.println("mr2= " + mr2);
		System.out.println("mr3= " + mr3);

	}

}

abstract class Calc{
	int a;
	int b;
	abstract void answer();
	
	void setData(int m, int n) {
		a=m;
		b=n;
	}
}

class Plu2s extends Calc{
	void answer() {
		System.out.println(a+"+"+b+"="+(a+b));
	}
}
public class Calculation {

	public static void main(String[] args) {
		Plu2s a = new Plus2();
		a.setData(27, 32);
		a.answer();

	}

}

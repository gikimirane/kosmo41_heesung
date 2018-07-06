interface Printable4{
	void print(String s);
}

class Printer4 implements Printable4{
	public void print(String s) {
		System.out.println(s);
	}
}


public class D1_Lambda {

	public static void main(String[] args) {
		Printable4 a = new Printer4();
		a.print("What is Lambda?");

	}

}

interface Printable6 {
	void print(String s);
}

public class D3_Lambda3 {

	public static void main(String[] args) {
	Printable6 a = (s) -> {System.out.println(s);};
		a.print("What is Lambda?");

	}

}

interface Printable5{
	void print(String s);
}
public class D2_Lambda {

	public static void main(String[] args) {
		Printable5 a = new Printable5() {
			public void print(String s) {
				System.out.println(s);
			}
		};
		
		a.print("What is Lambda");
	}

}

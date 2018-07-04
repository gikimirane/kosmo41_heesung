interface a{
	void print(String doc);
}

interface b extends a{
	void printCMYK(String doc);
}
class c implements b{
	@Override
	public void print(String doc) {
		System.out.println("From MD-909 Black & white ver");
		System.out.println(doc);
	}
	
	@Override
	public void printCMYK(String doc) {
		System.out.println("From MD-909 CMYK ver");
		System.out.println();
	}
}
public class B3_PrinterDriver3 {

	public static void main(String[] args) {
		String d = "this is a report about....";
		
		b e = new c();
		e.print(d);

		System.out.println();
		e.printCMYK(d);
	}

}

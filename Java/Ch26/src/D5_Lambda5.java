interface PrintableE{
	void print(String s, int m);
}
public class D5_Lambda5 {

	public static void main(String[] args) {
		PrintableE a = (s,m)->{System.out.println(s+":"+m);};
		a.print("What is Lambda?", 5);

	}

}
//피라미터가 여러개일때
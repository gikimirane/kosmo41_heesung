interface PrintableE{
	void print(String s, int m);
}
public class D5_Lambda5 {

	public static void main(String[] args) {
		PrintableE a = (s,m)->{System.out.println(s+":"+m);};
		a.print("What is Lambda?", 5);

	}

}
//�Ƕ���Ͱ� �������϶�
interface Printable{//MS가 정의하고 제공한 인터풰위스
	public void print(String doc);
}

class SprinterDriver implements Printable{
	@Override
	public void print(String doc) {
		System.out.println("From Samsung printer");
		System.out.println(doc);
	}
}
class LprinterDriver implements Printable{
	@Override
	public void print(String doc) {
		System.out.println("From LG printer");
		System.out.println(doc);
	}
}
public class PrintDriver {

	public static void main(String[] args) {
		String myDoc="This is a report about....";
		Printable prn = new SprinterDriver();
		//prn.print(myDoc);
		//System.out.println();
		
		prn = new LprinterDriver();
		prn.print(myDoc);
	}

}

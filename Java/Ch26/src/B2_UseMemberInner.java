interface Printable{
	void print();
}

class Papers{
	private String con;
	public Papers(String s) { con = s;}
	
	public Printable getPrinter() {
		return new Printer();
	}
	
	private class Printer implements Printable{
		public void print() {
			System.out.println(con);
		}
	}
}
public class B2_UseMemberInner {

	public static void main(String[] args) {
		
		Papers p = new Papers("서류내용 : 행복합니다.");
		Printable prn = p.getPrinter();
		prn.print();

	}

}

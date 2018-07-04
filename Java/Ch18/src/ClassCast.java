class Board{}
class PBoard extends Board{}
public class ClassCast {

	public static void main(String[] args) {
		Board a=new PBoard();
		PBoard b=(PBoard)a;
		
		System.out.println("..intermediate location..");
		Board c=new Board();
		PBoard d=(PBoard)c;

	}

}

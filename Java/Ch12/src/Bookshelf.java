class Book{
	String title;
	String genre;
	
	void printBook() {
		System.out.println("��  �� : " +title);
		System.out.println("��  �� : "+genre);
	}
}

class Novel extends Book{
	String writer;
	
	void printNov() {
		printBook();
		System.out.println("��  �� : "+writer);
	}
}

class Magazine extends Book{
	 private int day = 20;
	
	public void printMag() {
		printBook();
		System.out.println("�߸��� : "+day+"��");
	}
}
public class Bookshelf {

	public static void main(String[] args) {
		Novel nov = new Novel();
		nov.title ="�����";
		nov.genre="��������";
		nov.writer="�踸��";
		
		Magazine mag = new Magazine();
		mag.title="���� �ڹ� �׸�å";
		mag.genre="��ǻ��";

		
		nov.printNov();
		System.out.println();
		mag.printMag();
		

	}

}

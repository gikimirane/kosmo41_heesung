class MyBook{
	int price;
	String title;
	
	MyBook(String t, int p){
		title = t;
		price = p;
	}
	MyBook(MyBook copy){
		title = copy.title;
		price = copy.price;
	}
	void print() {
		System.out.println("��     �� : "+title);
		System.out.println("��     �� : "+price);
	}
}
public class Bookss {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyBook book1 = new MyBook("���ӽ���", 10000);
		book1.print();
		
		MyBook book2 = new MyBook(book1);
		book2.title = "����ϰ���";
		book2.print();
		
	}

}

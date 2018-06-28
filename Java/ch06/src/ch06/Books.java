class MyBook {
	int price;
	int num = 0;
	String title;

	MyBook() {
		title = "모바일게임교육";
		price = 5000;
	}

	MyBook(String t, int p) {
		title = t;
		price = p;
	}

	void print() {
		System.out.println("제     목 : " + title);
		System.out.println("가     격 : " + price);
		System.out.println("주문수량 : " + num);
		System.out.println("합계금액 : " + price * num);
	}
}

public class Books {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyBook book = new MyBook("게임스쿨", 10000);
		book.num = 5;
		book.print();

	}

}

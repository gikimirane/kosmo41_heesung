class MyBook {
	int price;
	int num = 0;
	String title;

	MyBook() {
		title = "����ϰ��ӱ���";
		price = 5000;
	}

	MyBook(String t, int p) {
		title = t;
		price = p;
	}

	void print() {
		System.out.println("��     �� : " + title);
		System.out.println("��     �� : " + price);
		System.out.println("�ֹ����� : " + num);
		System.out.println("�հ�ݾ� : " + price * num);
	}
}

public class Books {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyBook book = new MyBook("���ӽ���", 10000);
		book.num = 5;
		book.print();

	}

}

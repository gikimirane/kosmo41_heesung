class wdate {
	int a;
	int b;
	String c;
}

class wdata {

	public static void main(String[] args) {
		wdate today = new wdate();
		today.a = 10;
		today.b = 5;
		today.c = "����";

		System.out.println(today.a + "�� " + today.b + "�� " + today.c);

	}

}

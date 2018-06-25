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
		today.c = "¸¼À½";

		System.out.println(today.a + "¿ù " + today.b + "ÀÏ " + today.c);

	}

}

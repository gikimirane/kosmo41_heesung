class Box1 {
	private String contents;
	private int boxNum;

	Box1(int num, String cont) {
		boxNum = num;
		contents = cont;
	}

	public int getBoxNum() {
		return boxNum;
	}

	public String toString() {
		return contents;
	}
}

public class EnhancedForlnst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box1[] ar = new Box1[5];
		ar[0] = new Box1(101, "Coffee");
		ar[1] = new Box1(202, "Computer");
		ar[2] = new Box1(303, "Apple");
		ar[3] = new Box1(404, "Dress");
		ar[4] = new Box1(505, "Fairy-tale book");

		for (Box1 e : ar) {
			if (e.getBoxNum() == 505)
				System.out.println(e);
		}

	}

}

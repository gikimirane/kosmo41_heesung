package src;

public class Quiz5_15 {

	public static void main(String[] args) {
		for (;;) {
			int a = (int) (Math.random() * 898) + 102;
			int b, c, d;
			b = a / 100;
			c = (a - (b * 100)) / 10;
			d = (a - (b * 100)) - (c * 10);
			if ((b != c) && (c != d) && (d != b))
				System.out.println(a);
			break;


		}

	}
}

import java.util.Scanner;

public class Quiz13_12 {

	public static void main(String[] args) {
		int b, c, d, e, f, g, h;
		Scanner s = new Scanner(System.in);
		int c = a / 100;
		int d = (a - (c * 100)) / 10;
		int e = ((a - (c * 100)) - (d * 10));
		while (true) {
			if ((c != d) && (c != e) && (d != e)) {
				break;
			}
			int a = (int) (Math.random() * 897) + 102;		
		}

		System.out.println("���ھ߱� ����~");
		while (true) {
			int i = 0, j = 0;
			System.out.println("���ڸ� ���ڸ� �Է��ϼ���~" + a);
			b = s.nextInt();
			f = b / 100;
			g = ((b - (f * 100)) / 10);
			h = ((b - (f * 100)) - (g * 10));
			if ((c == f) && (d == g) && (e == h)) {
				System.out.println(f + ":" + g + ":" + h);
				System.out.println("3 Strike ������ϴ�.");
				break;
			}

			if (c == f)
				i++;
			if ((c == g) || (c == h))
				j++;
			if (d == g)
				i++;
			if ((d == f) || (d == h))
				j++;
			if (e == h)
				i++;
			if ((e == f) || (e == g))
				j++;
			System.out.println(f + ":" + g + ":" + h);
			System.out.println(i + " Strike  " + j + " Ball");

		}

	}

}

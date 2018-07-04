import java.util.Arrays;
import java.util.Scanner;

public class Puzzle {

	public static void main(String[] args) {
		String x[][] = { { "1", "2", "3" }, { "4", "x", "5" }, { "6", "7", "8" } };
		String z[][] = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" } };
		String[][] y = new String[3][3];
		String num;
		Scanner s = new Scanner(System.in);
		String sc;
		int a;
		int b = 0;

		do {
			int q = (int) (Math.random() * 4);
			a = q;
			if (a == 0) {
				for (int i = 0; i < x.length; i++) {
					for (int j = 0; j < x.length; j++) {
						if (x[i][j] == "x") {
							if (j == 0)
								continue;
							num = x[i][j];
							x[i][j] = x[i][j - 1];
							x[i][j - 1] = num;

						}
					}
				}
			}

			if (a == 1) {
				for (int i = 0; i < x.length; i++) {
					for (int j = x.length - 1; j >= 0; j--) {
						if (x[i][j] == "x") {
							if (j == 2)
								continue;
							num = x[i][j];
							x[i][j] = x[i][j + 1];
							x[i][j + 1] = num;
						}
					}
				}
			}

			if (a == 2) {
				for (int i = 0; i < x.length; i++) {
					for (int j = 0; j < x.length; j++) {
						if (x[i][j] == "x") {
							if (i == 0)
								continue;
							num = x[i][j];
							x[i][j] = x[i - 1][j];
							x[i - 1][j] = num;
						}
					}
				}
			}

			if (a == 3) {
				for (int i = x.length - 1; i >= 0; i--) {
					for (int j = 0; j < x.length; j++) {
						if (x[i][j] == "x") {
							if (i == 2)
								continue;
							num = x[i][j];
							x[i][j] = x[i + 1][j];
							x[i + 1][j] = num;
						}
					}
				}
			}
			b++;

		} while (b < 100);
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				System.out.print(x[i][j] + "\t");
			}
			System.out.println("");
		}

		do {
			System.out.println("[Move] a:좌 d:우 w:위 s:밑");
			System.out.println("[Exit] k:나가기");
			System.out.println("이동키를 입력하세요");

			sc = s.nextLine();
			if (sc.equals("a")) {
				for (int i = 0; i < x.length; i++) {
					for (int j = 0; j < x.length; j++) {
						if (x[i][j] == "x") {
							if (j == 0) {
								System.out.println("이동할수없습니다.");
								continue;
							}
							num = x[i][j];
							x[i][j] = x[i][j - 1];
							x[i][j - 1] = num;
						}
					}
				}
			}

			if (sc.equals("d")) {
				for (int i = 0; i < x.length; i++) {
					for (int j = x.length - 1; j >= 0; j--) {
						if (x[i][j] == "x") {
							if (j == 2) {
								System.out.println("이동할수없습니다.");
								continue;
							}
							num = x[i][j];
							x[i][j] = x[i][j + 1];
							x[i][j + 1] = num;
						}
					}
				}
			}

			if (sc.equals("w")) {
				for (int i = 0; i < x.length; i++) {
					for (int j = 0; j < x.length; j++) {
						if (x[i][j] == "x") {
							if (i == 0) {
								System.out.println("이동할수없습니다.");
								continue;
							}
							num = x[i][j];
							x[i][j] = x[i - 1][j];
							x[i - 1][j] = num;
						}
					}
				}
			}

			if (sc.equals("s")) {
				for (int i = x.length - 1; i >= 0; i--) {
					for (int j = 0; j < x.length; j++) {
						if (x[i][j] == "x") {
							if (i == 2) {
								System.out.println("이동할수없습니다.");
								continue;
							}
							num = x[i][j];
							x[i][j] = x[i + 1][j];
							x[i + 1][j] = num;
						}
					}
				}
			}
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x.length; j++) {
					System.out.print(x[i][j] + "\t");
				}
				System.out.println("");
			}
			if (sc.equals("k")) {
				System.out.println("게임을 종료합니다");
				break;
			}
			if (Arrays.deepEquals(x, z)) {
				System.out.println("정답입니다. 게임을종료합니다/");
				System.out.println("Good Bye~~");
				break;
			}
		} while (true);

	}

}

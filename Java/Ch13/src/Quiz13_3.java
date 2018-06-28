import java.util.Scanner;

public class Quiz13_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[10];
		int c, d = 0;
		Scanner s = new Scanner(System.in);
		for (int i = 0; i < a.length; i++) {
			int b = s.nextInt();
			a[i] = b;

		}
		System.out.print("짝수출력 : ");
		for (int e : a) {
			if (e % 2 == 0) {
				System.out.print(e+",");
			}
		}
		System.out.println();
		System.out.print("홀수출력 : ");
		for (int r : a) {
			if (r % 2 != 0) {
				System.out.print(r+",");
			}
	}
	}
}

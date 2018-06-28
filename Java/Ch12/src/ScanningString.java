import java.util.Scanner;

public class ScanningString {

	public static void main(String[] args) {
		String source = "135 135 135";
		Scanner a = new Scanner(source);
		int b = a.nextInt();
		int c = a.nextInt();
		int d = a.nextInt();
		int sum = b + c + d;
		System.out.printf("%d + %d + %d = %d \n", b, c, d, sum);
		a.close();

	}

}

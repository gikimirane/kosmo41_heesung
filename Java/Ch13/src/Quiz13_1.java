import java.util.Scanner;

public class Quiz13_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[5];
		int max = 0;
		int min = 0;
		int num = 0;
		Scanner s = new Scanner(System.in);

		for (int i = 0; i < a.length; i++) {
			int b = s.nextInt();
			num += b;
			a[i] = b;
		}
			max = a[0];
			min = a[0];
			for (int i = 0; i < a.length; i++) {
				if (a[i] > max) {
					max = a[i];
				}
				if (a[i] < min) {
					min = a[i];
				}
			}
		
		System.out.println("가장 큰 수는 : " + max + "입니다");
		System.out.println("가장 작은 수는 : " + min + "입니다");
		System.out.println("모든 수의 합은 : " + num + "입니다");
	}
}

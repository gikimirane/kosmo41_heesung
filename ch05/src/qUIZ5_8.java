import java.util.Scanner;
public class qUIZ5_8 {
	public static void main(String[] args) {
		int a = 0;
		int b=0;
		int c=0;
		int d=0;
		int e=0;
		int sum;
		
		Scanner s = new Scanner(System.in);
		System.out.println("5개의 숫자를 입력해 주세요");
		a = s.nextInt();
		if (a < 1) {
			System.out.println("1미만의 수입니다 다시 입력해주세요");
			a = s.nextInt();
		}
		b = s.nextInt();
		if (b < 1) {
			System.out.println("1미만의 수입니다 다시 입력해주세요");
			b = s.nextInt();
		}
		c = s.nextInt();
		if (c < 1) {
			System.out.println("1미만의 수입니다 다시 입력해주세요");
			c = s.nextInt();
		}
		d = s.nextInt();
		if (d < 1) {
			System.out.println("1미만의 수입니다 다시 입력해주세요");
			d = s.nextInt();
		}
		e = s.nextInt();
		if (e < 1) {
			System.out.println("1미만의 수입니다 다시 입력해주세요");
			e = s.nextInt();
		}
		sum = a+b+c+d+e;
		System.out.println("다섯숫자의 합은 =" + sum + " 입니다.");
	}

}

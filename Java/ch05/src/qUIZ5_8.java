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
		System.out.println("5���� ���ڸ� �Է��� �ּ���");
		a = s.nextInt();
		if (a < 1) {
			System.out.println("1�̸��� ���Դϴ� �ٽ� �Է����ּ���");
			a = s.nextInt();
		}
		b = s.nextInt();
		if (b < 1) {
			System.out.println("1�̸��� ���Դϴ� �ٽ� �Է����ּ���");
			b = s.nextInt();
		}
		c = s.nextInt();
		if (c < 1) {
			System.out.println("1�̸��� ���Դϴ� �ٽ� �Է����ּ���");
			c = s.nextInt();
		}
		d = s.nextInt();
		if (d < 1) {
			System.out.println("1�̸��� ���Դϴ� �ٽ� �Է����ּ���");
			d = s.nextInt();
		}
		e = s.nextInt();
		if (e < 1) {
			System.out.println("1�̸��� ���Դϴ� �ٽ� �Է����ּ���");
			e = s.nextInt();
		}
		sum = a+b+c+d+e;
		System.out.println("�ټ������� ���� =" + sum + " �Դϴ�.");
	}

}

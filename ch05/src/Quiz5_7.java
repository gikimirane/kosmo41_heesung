import java.util.Scanner;
public class Quiz5_7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 0;
		int b = 0;
		int c = 0;
		int sum =0;
		int sume =0;
		Scanner s = new Scanner(System.in);
		System.out.println("���������� �Է��ϼ���");
		a = s.nextInt();
		System.out.println("���������� �Է��ϼ���");
		b = s.nextInt();
		System.out.println("���������� �Է��ϼ���");
		c = s.nextInt();
		sum = a+b+c;
		sume = sum / 3;
		if (sume >=90) {
			System.out.println("A");
		}
		else if (sume >=80) {
			System.out.println("B");
		}
		else if (sume >=70) {
			System.out.println("C");
		}
		else if (sume >=50) {
			System.out.println("D");
		}
		else if (sume < 50) {
			System.out.println("F");
		}

	}

}

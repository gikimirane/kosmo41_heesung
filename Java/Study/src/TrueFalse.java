import java.util.Scanner;

public class TrueFalse {

	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		int b;
		int i = 0;
		System.out.println("���ڸ� �Է��ϼ���");
		for(;;) {
			b=a.nextInt();
		switch(i) {
		case 0:
			System.out.println("true");
			i--;
			continue;
		case 1:
			System.out.println("false");
			i++;
			continue;
		}
		}
	}
		

}

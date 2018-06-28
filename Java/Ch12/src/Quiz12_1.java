import java.util.Scanner;

public class Quiz12_1 {

	public static void main(String[] args) {

		Scanner a = new Scanner(System.in);
		int b = (int) (Math.random() * 100);
		int c;
		String d;
		for(;;) {
			int j =6;
		System.out.println("나는 지금부터 0~100 사이 값 중 하나를 생각하겠다.");
		System.out.println("너의 멍청한머리로 6번의 기회안에 맞춰보거라");

		for (int i = 0; i < 6; i++) {
			System.out.print("몇이라고 생각하느냐? "+j+"번남았노라\t");
			c = a.nextInt();
			if (c < b) {
				System.out.println(c + " UP");
			} else if (c == b) {
				System.out.println(c + " 맞췄구나");
				break;
			} else if (c > b) {
				System.out.println(c + "Down");
			}
			j--;
		}
		System.out.println("더할게냐? (y/n)");
		d = a.next();
		if (d.equals("y")) {
			continue;
		}
		else if (d.equals("n")) {
			System.out.println("다음에 또하자");
			return;

		}
		
			
		}
	}

}

import java.util.Scanner;
public class Quiz5_10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 0;
		int a = 0;
		int b = 0;
		int c = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("2개의 정수를 입력하세요");
		a = s.nextInt();
		b = s.nextInt();
		if (a > b) {
			for (i=a;a>=b;a--) {
				if(i == b) {
					System.out.println(i);
					break;
				}
				c = c+i;
				System.out.println(i + "+");
			}
		}
		else {
			for (i=a; a<=b; i++) {
				if (i == b) {
					System.out.println(i);
					break;
				}
				c = c+i;
				System.out.println(i + "+");
			}
		}
		System.out.println("=" + c);

	}

}

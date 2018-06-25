import java.util.Scanner;
public class Quiz5_4 {
	public static void main(String[] args) {
		int a =0;
		int b=0;
		int c=0;
		
		Scanner s = new Scanner(System.in);
		System.out.println("숫자를 입력하여 주세요");
		a = s.nextInt();
		System.out.println("숫자를 입력하여 주세요");
		b = s.nextInt();
			if(a<b) {
				System.out.println("두숫자의 차이는 " + (b-a) + " 입니다");

		}
			else if (a>b) {
				System.out.println("두 숫자의 차이는 " + (a-b) + " 입니다");
			}
			else if (a==b) {
				System.out.println("두숫자의 차이가 0 입니다 다시입력해주세요");
				
			}
		
	}

}

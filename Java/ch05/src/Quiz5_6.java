import java.util.Scanner;
public class Quiz5_6 {

	public static void main(String[] args) {
		int a = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("2~9단중 원하는 구구단의 숫자를 입력해주세영");
		a = s.nextInt();
					for(int j = 9; j>=1; j--) {
						System.out.println(a + "X" + j + "=" + (a*j));
					}
				
	}

}

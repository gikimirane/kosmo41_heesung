import java.util.Scanner;
public class Quiz5 {

	public static void main(String[] args) {
		
		int a = 0;
		int num = 0;
		int sum = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("몇개의 정수를 입력하실꺼예요?");
		
		
		
		 num = s.nextInt();
		System.out.println(num + "개의 정수를 입력하세요");
		
		for(int i = 1; i <= num; i++) {
			a = s.nextInt();
			sum = sum + a;
		}
		float sume = (float)sum / num;
		System.out.println(num + "개 숫자의 평균은" + sume + "입니다");
		
		
	}

}

import java.util.Scanner;
public class Quiz5_11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=0;
		int b=1;
		
		
		Scanner s = new Scanner(System.in);
		System.out.println("정수 한개를 입력하세요");
		a = s.nextInt();
		while (a>0) {
			if(a==0) {
				System.out.println(a);
				break;
			}
			System.out.println(a + "* ");
			b = b*a;
			a--;
		}
		System.out.println("=" + b);
	}

}

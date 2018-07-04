import java.util.Scanner;

public class F3_FinallyCase3 {

	public static void main(String[] args) {
		int num;
		Scanner s = new Scanner(System.in);
		int a = s.nextInt();
		int b = s.nextInt();

		try {
			num = a / b;
		} catch (Exception e) {
			// e.printStackTrace();
			num = 0;
		} finally {
			//데이터베이스 접속 종료 등.. 무조건 해야 할 일!!
			
			//num=num+1;
/*			if (b != 0) {
			
			}
*/
		}
		System.out.println(num);

	}

}

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
			//�����ͺ��̽� ���� ���� ��.. ������ �ؾ� �� ��!!
			
			//num=num+1;
/*			if (b != 0) {
			
			}
*/
		}
		System.out.println(num);

	}

}

import java.util.Scanner;
public class Quiz5_4 {
	public static void main(String[] args) {
		int a =0;
		int b=0;
		int c=0;
		
		Scanner s = new Scanner(System.in);
		System.out.println("���ڸ� �Է��Ͽ� �ּ���");
		a = s.nextInt();
		System.out.println("���ڸ� �Է��Ͽ� �ּ���");
		b = s.nextInt();
			if(a<b) {
				System.out.println("�μ����� ���̴� " + (b-a) + " �Դϴ�");

		}
			else if (a>b) {
				System.out.println("�� ������ ���̴� " + (a-b) + " �Դϴ�");
			}
			else if (a==b) {
				System.out.println("�μ����� ���̰� 0 �Դϴ� �ٽ��Է����ּ���");
				
			}
		
	}

}

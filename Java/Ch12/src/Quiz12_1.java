import java.util.Scanner;

public class Quiz12_1 {

	public static void main(String[] args) {

		Scanner a = new Scanner(System.in);
		int b = (int) (Math.random() * 100);
		int c;
		String d;
		for(;;) {
			int j =6;
		System.out.println("���� ���ݺ��� 0~100 ���� �� �� �ϳ��� �����ϰڴ�.");
		System.out.println("���� ��û�ѸӸ��� 6���� ��ȸ�ȿ� ���纸�Ŷ�");

		for (int i = 0; i < 6; i++) {
			System.out.print("���̶�� �����ϴ���? "+j+"�����ҳ��\t");
			c = a.nextInt();
			if (c < b) {
				System.out.println(c + " UP");
			} else if (c == b) {
				System.out.println(c + " ���豸��");
				break;
			} else if (c > b) {
				System.out.println(c + "Down");
			}
			j--;
		}
		System.out.println("���ҰԳ�? (y/n)");
		d = a.next();
		if (d.equals("y")) {
			continue;
		}
		else if (d.equals("n")) {
			System.out.println("������ ������");
			return;

		}
		
			
		}
	}

}

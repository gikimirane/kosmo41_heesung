import java.util.Scanner;

public class Quiz12_2 {

	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		String d;
		int max = 100;
		int min = 0;
		String h,l,y;
		for(;;) {
			System.out.println("0~100���� ���� ���� �ϳ��� �����Ұ� �ʰ�����");
			System.out.println("������ h , ������ l , ������ y ���ٰ�");
			for(;;) {
				int sum = (min + max)/2;
				System.out.println("is it  "+sum);
				d = a.next();
				if (d.equals("h")) {
					min = sum;
					continue;
				}
				else if (d.equals("l")) {
					max = sum;
					continue;
				}
				else if (d.equals("y")) {
					break;
				}
				}
			System.out.println("�׸� �Ϸ��� n / ��� �Ϸ��� �ƹ�Ű�� ��������");
			d = a.next();
			if (d.equals("n")) {
				System.out.println("������ �� �ؿ�");
				break;
				
				
			}
		}
		
		
			
		

	}

}

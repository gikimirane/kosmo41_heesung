import java.util.Scanner;
public class Quiz5 {

	public static void main(String[] args) {
		
		int a = 0;
		int num = 0;
		int sum = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("��� ������ �Է��Ͻǲ�����?");
		
		
		
		 num = s.nextInt();
		System.out.println(num + "���� ������ �Է��ϼ���");
		
		for(int i = 1; i <= num; i++) {
			a = s.nextInt();
			sum = sum + a;
		}
		float sume = (float)sum / num;
		System.out.println(num + "�� ������ �����" + sume + "�Դϴ�");
		
		
	}

}

import java.util.Scanner;

public class Quiz13_9 {

	public static void main(String[] args) {
		int b;
		int c;
		Scanner s = new Scanner(System.in);
		
		while(true) {
		System.out.println("������������ �Է��ϼ���. 1.�� 2.�ָ� 3.���� 0.����");
		b = s.nextInt();
		int a =(int)(Math.random()*3)+1;
		c = a;
		if(b==0) {
			System.out.println("������������ �����մϴ�.");
			break;
		}
		if(b>c)
		System.out.println("�����"+ b + "com"+ c+"����ڰ� �̰���ϴ�");
		if(b==c)
			System.out.println("�����"+ b + "com"+ c+"�����ϴ�");
		if(b<c)
			System.out.println("�����"+ b + "com"+ c+"����ڰ� �����ϴ�");
			}
	}

}

import java.util.Scanner;

public class ReadString {

	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		
		System.out.print("���ڿ� �Է�:");
		String b=a.nextLine();
	
		System.out.print("���ڿ� �Է�:");
		String c=a.nextLine();
		
		String d = b.concat(c);
		System.out.printf("�Էµ� ���ڿ� : %s \n",b);
		System.out.printf("�Էµ� ���ڿ� : %s \n",c);
		System.out.printf("�Էµ� ���ڿ� : %s \n",d);
	}
	

}

import java.util.Scanner;

public class ReadString {

	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		
		System.out.print("문자열 입력:");
		String b=a.nextLine();
	
		System.out.print("문자열 입력:");
		String c=a.nextLine();
		
		String d = b.concat(c);
		System.out.printf("입력된 문자열 : %s \n",b);
		System.out.printf("입력된 문자열 : %s \n",c);
		System.out.printf("입력된 문자열 : %s \n",d);
	}
	

}

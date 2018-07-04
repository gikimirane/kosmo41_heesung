import java.util.Scanner;

public class A2_ExceptionCase {

	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		
		
		System.out.println("a/b..a?");
		int b=a.nextInt();
		
		System.out.println("a/b..b?");
		int c=a.nextInt();
		
		System.out.printf("%d / %d = %d \n",b,c,b/c);


		System.out.println("Good bye~~~!");
	}

}

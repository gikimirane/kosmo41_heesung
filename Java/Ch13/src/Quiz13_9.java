import java.util.Scanner;

public class Quiz13_9 {

	public static void main(String[] args) {
		int b;
		int c;
		Scanner s = new Scanner(System.in);
		
		while(true) {
		System.out.println("가위바위보를 입력하세요. 1.보 2.주먹 3.가위 0.종료");
		b = s.nextInt();
		int a =(int)(Math.random()*3)+1;
		c = a;
		if(b==0) {
			System.out.println("가위바위보를 종료합니다.");
			break;
		}
		if(b>c)
		System.out.println("사용자"+ b + "com"+ c+"사용자가 이겼습니다");
		if(b==c)
			System.out.println("사용자"+ b + "com"+ c+"비겼습니다");
		if(b<c)
			System.out.println("사용자"+ b + "com"+ c+"사용자가 졌습니다");
			}
	}

}

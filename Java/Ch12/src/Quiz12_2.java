import java.util.Scanner;

public class Quiz12_2 {

	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		String d;
		int max = 100;
		int min = 0;
		String h,l,y;
		for(;;) {
			System.out.println("0~100까지 수중 내가 하나를 생각할게 너가맞춰");
			System.out.println("높으면 h , 낮으면 l , 맞으면 y 해줄게");
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
			System.out.println("그만 하려면 n / 계속 하려면 아무키나 누르세요");
			d = a.next();
			if (d.equals("n")) {
				System.out.println("다음에 또 해요");
				break;
				
				
			}
		}
		
		
			
		

	}

}

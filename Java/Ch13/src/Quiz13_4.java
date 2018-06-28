import java.util.Scanner;

public class Quiz13_4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] c = new int[10];
		int t;
		int a=0, b=c.length-1;
		
		for(int i=0; i<c.length; i++) {
			System.out.println((i+1)+"째 정수 입력");
			t = sc.nextInt();
			if(t%2==1) {
				c[a]=t;
				a++;
			}
			else {
				c[b]=t;
				b--;
			}
		}
		
		for(int e : c) {
			System.out.print(e+" ");
		}
	}
}


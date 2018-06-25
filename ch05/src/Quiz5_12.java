
public class Quiz5_12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=1;
		int b=0;
		do {
			if(a==1000) {
				System.out.println(a);
				b = b+a;
				break;
			}
			System.out.println(a + "+");
			b = b+a;
			a++;
		}while( a <= 1000);
		System.out.println( " = " + b);
	}

}


public class Quiz5_13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" °¡·Î 99´Ü");
		for(int j=1; j<10; j++) {
			for(int i =2;i<10;i++) {
				System.out.print(" " +i+"x"+j+"="+(i*j));
				if(i==9) {
					System.out.print('\n');
				}
			}
		}
		for(int i=2;i<10;i++) {
			for(int j=1;j<10;j++) {
				System.out.println(i+"x"+j+"="+(i*j));
				if(i==9) {
					System.out.println('\n');
				}
			}
		}
	}

}


public class ForInFor {
	public static void main(String[] args) {
	//	for(int i = 1; i <= 3; i++) {
		//	System.out.println("----------------");
		//	for(int j = 1; j<=3; j++) {
	//			System.out.print("[" + i + "," + j + "]");
		for(int i = 2; i <=9; i++) {
			for(int j = 1; j <=9; j++) {
				System.out.println(i + "X" + j + "=" + (i*j));
			}
			System.out.print('\n');
		}
	}

}


public class Quiz5_9 {

	public static void main(String[] args) {
		int i = 1;
		int sum = 0;
		do {
			i++;
			do {
				sum = sum + 1;
				i++;
				
			}while (i % 2 == 0);
		}while (i <= 100);
		System.out.println("1~100 Â¦¼öÀÇ ÇÕ : " + sum);
}
}
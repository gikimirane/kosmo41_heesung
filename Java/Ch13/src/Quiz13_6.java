
public class Quiz13_6 {

	public static void main(String[] args) {
		int[][] a = new int[3][9];
		int c = 2;
		int d = 1;
		System.out.println("2~4´Ü");
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = c * d;
				d++;
				System.out.print("a" + "[" + i + "]" + "[" + j + "] " + a[i][j] + " ");
			}
			d = 1;
			c++;
			System.out.println("");
		}

	}

}


public class Quiz13_11 {

	public static void main(String[] args) {
		int a[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		int i = 0, j = 0;
		for(i=0;i<a.length;i++) {
			for(j=0;j<=a.length-1;j++) {
				System.out.print(a[i][j]+"\t");
			}
			System.out.println("");
	}
		System.out.println("");
		for(j=0;j<4;j++) {
			for(i=3;i>=0;i--) {
				System.out.print(a[i][j]+"\t");
			}
			System.out.println("");
	}
		System.out.println("");
		for(i=3;i>=0;i--) {
			for(j=3;j>=0;j--) {
				System.out.print(a[i][j]+"\t");
			}
			System.out.println("");
		}
		System.out.println("");
		for(j=3;j>=0;j--) {
			for(i=0;i<4;i++) {
				System.out.print(a[i][j]+"\t");
			}
			System.out.println("");
		}
		System.out.println("");
}
}

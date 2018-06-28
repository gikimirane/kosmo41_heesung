
public class Quiz13_7 {
	public static void main(String args[]) {
		int a[][] = {{1,2,3,4},{5,6,7,8}};
		int b[][] = new int[4][2];
		for (int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++) {
				System.out.print(a[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("_____________");
		for (int i=0;i<b.length;i++) {
			for(int j=0;j<b[i].length;j++) {
				b[i][j] = a[j][i];
				System.out.print(b[i][j]+"\t");
			}
			System.out.println();
		}
	}

}

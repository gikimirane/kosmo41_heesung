import java.util.Arrays;

public class ArraysSort {

	public static void main(String[] args) {
		int[] a = {1,5,3,2,4};
		double[] b = {3.3,5.5,2.2,1.1};
		Arrays.sort(a);
		Arrays.sort(b);
		
		for(int n: a)
			System.out.print(n+"\t");
		System.out.println();

		for(double d: b)
			System.out.print(d+"\t");
		System.out.println();

	}

}

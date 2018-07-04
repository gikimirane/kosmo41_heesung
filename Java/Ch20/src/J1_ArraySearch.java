import java.util.Arrays;

public class J1_ArraySearch {

	public static void main(String[] args) {
		int[] a = {33,55,11,44,22};
		Arrays.sort(a);
		
		for (int e:a)
			System.out.print(e+"\t");
		System.out.println();
		
		int idx = Arrays.binarySearch(a, 33);
		System.out.println("Index of 33 : "+ idx);

	}

}

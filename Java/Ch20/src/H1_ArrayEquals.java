import java.util.Arrays;

public class H1_ArrayEquals {
	
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5};
		int[] b = Arrays.copyOf(a, a.length);
		System.out.println(Arrays.equals(a, b));

	}

}

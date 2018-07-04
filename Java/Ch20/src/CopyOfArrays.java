import java.util.Arrays;

public class CopyOfArrays {

	public static void main(String[] args) {
		double[] a = {1.1,2.2,3.3,4.4,5.5};
		
		double[] b = Arrays.copyOf(a, a.length);
		
		double[] c = Arrays.copyOf(a, 4);
		
		for(double d:b)
			System.out.print(d+"\t");
		System.out.println();
		
		for(double d:c)
			System.out.print(d+"\t");
		System.out.println();
	}

}

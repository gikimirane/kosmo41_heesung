
public class G_CopyOfSystem {

	public static void main(String[] args) {
		double[] a = {1.1,2.2,3.3,4.4,5.5};
		double[] b = new double[3];
					//배열 a 껄 1부터 b에 두번째부터 두개 복사
		System.arraycopy(a, 0, b, 1, 2);
		
		for(double d:b)
			System.out.print(d+"\t");
		System.out.println();

	}

}

import java.util.Arrays;
import java.util.stream.IntStream;

public class A1_Stream {

	public static void main(String[] args) {
		int[] ar = {1,2,3,4,5};
		int  sum = Arrays.stream(ar) //��Ʈ������,
								.filter(n-> n%2==1)//filter ���
								.sum(); //sum ��� ��� ��ȯ
		System.out.println(sum);

	}

}

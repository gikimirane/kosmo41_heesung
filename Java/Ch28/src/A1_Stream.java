import java.util.Arrays;
import java.util.stream.IntStream;

public class A1_Stream {

	public static void main(String[] args) {
		int[] ar = {1,2,3,4,5};
		int  sum = Arrays.stream(ar) //스트림생성,
								.filter(n-> n%2==1)//filter 통과
								.sum(); //sum 통과 결과 반환
		System.out.println(sum);

	}

}

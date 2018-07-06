import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class A3_F_CopyList {

	public static void main(String[] args) {
		List<String> a = Arrays.asList("Box","Apple","Toy","Robot");
		
		//복사본을 만들어서 사용
		List<String> d = new ArrayList<>(a);
		
		//정렬하여 그 결과 출력
		Collections.sort(d);
		System.out.println(d);
		
		//사정상 정렬 이전의 상태로 되돌려야함
		Collections.copy(d, a);
		
		//되될림도ㅒㅆ나확인	
		System.out.println(d);

	}

}

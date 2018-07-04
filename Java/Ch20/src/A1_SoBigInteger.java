import java.math.BigInteger;

public class A1_SoBigInteger {

	public static void main(String[] args) {
		//long형으로 표현 가능한 값의 크기 출력
		System.out.println("최대 정수 : "+ Long.MAX_VALUE);
		System.out.println("최소 정수: "+Long.MIN_VALUE);
		System.out.println();
		
		//매우 큰 수를 인스턴스로 표현
		BigInteger a = new BigInteger("100000000000000");
		BigInteger b = new BigInteger("-99999999999999");
		
		//Big 기반 덧셈 연산
		BigInteger r1 = a.add(b);
		System.out.println(r1);
		
		//곱셉
		BigInteger r2 = a.multiply(b);
		System.out.println(r2);
		
		//인스턴스에 저장값을 int형 정수로 반환
		int c = r1.intValueExact();
		System.out.println("From BigInteger : "+c);
		

	}

}
